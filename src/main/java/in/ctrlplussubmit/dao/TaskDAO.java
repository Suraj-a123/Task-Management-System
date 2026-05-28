package in.ctrlplussubmit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.ctrlplussubmit.model.Task;
import in.ctrlplussubmit.util.DBConnection;

public class TaskDAO {
	public static boolean create(Task task) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
						INSERT INTO task(title, description, faculty_id,
						deadline, max_marks,
						attach_file_path, attach_file_name)
						VALUES(?, ?, ?, ?, ?, ?, ?)
					""";
			ps = conn.prepareStatement(sql);
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setInt(3, task.getFacultyId());
			ps.setTimestamp(4, Timestamp.valueOf(task.getDeadline()));
			ps.setInt(5, task.getMaxMarks());
			ps.setString(6, task.getAttachFilePath());
			ps.setString(7, task.getAttachFileName());
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] create error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] create error: " + ex.getMessage());
			}
		}
	}

	public static List<Task> findByFaculty(int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> list = new ArrayList<>();
		String sql = """
					SELECT t.*, u.full_name as Faculty_name,

				COUNT(DISTINCT
				   CASE WHEN ta.assignment_type='INDIVIDUAL' THEN ta.student_id
				        ELSE bs.student_id
				   END) AS total_assigned,

				COUNT(DISTINCT
				   CASE WHEN s.status != 'PENDING' THEN s.student_id
				   END) AS total_submitted,

				COUNT(DISTINCT
				   CASE WHEN s.status in ('SUBMITTED', 'LATE') THEN s.student_id
				   END
				) AS pending_review

				FROM task t
				LEFT JOIN users u ON t.faculty_id = u.id
				LEFT JOIN task_assignments ta ON ta.task_id = t.id
				LEFT JOIN batch_students bs ON ta.batch_id = bs.batch_id
				LEFT JOIN submissions s ON s.task_id = t.id
				WHERE t.faculty_id = ?
				GROUP BY t.id, u.full_name
				ORDER BY t.deadline ASC
								""";
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs,true));
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] findByFaculty error: " + ex.getMessage());
			return list;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] findByFaculty error: " + ex.getMessage());
			}
		}
	}
	
	public static Task findById(int taskId, int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = """
					SELECT t.*, u.full_name as Faculty_name,

				COUNT(DISTINCT
				   CASE WHEN ta.assignment_type='INDIVIDUAL' THEN ta.student_id
				        ELSE bs.student_id
				   END) AS total_assigned,

				COUNT(DISTINCT
				   CASE WHEN s.status != 'PENDING' THEN s.student_id
				   END) AS total_submitted,

				COUNT(DISTINCT
				   CASE WHEN s.status in ('SUBMITTED', 'LATE') THEN s.student_id
				   END
				) AS pending_review

				FROM task t
				LEFT JOIN users u ON t.faculty_id = u.id
				LEFT JOIN task_assignments ta ON ta.task_id = t.id
				LEFT JOIN batch_students bs ON ta.batch_id = bs.batch_id
				LEFT JOIN submissions s ON s.task_id = t.id
				WHERE t.faculty_id = ? AND t.id = ?
				GROUP BY t.id, u.full_name
				ORDER BY t.deadline ASC
								""";
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facultyId);
			ps.setInt(2, taskId);
			rs = ps.executeQuery();
			if(rs.next()) {
				return mapRow(rs,true);
			}
			return null;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] findById error: " + ex.getMessage());
			return null;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] findById error: " + ex.getMessage());
			}
		}
	}
	
	public static boolean update(Task task) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
						UPDATE task SET title = ?, description = ?, deadline = ?,
						max_marks = ?, attach_file_path = COALESCE(?, attach_file_path), 
						attatch_file_name = COALESCE(?, attach_file_name)
						WHERE id = ? AND faculty_id = ?
					""";
			ps = conn.prepareStatement(sql);
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
			ps.setInt(4, task.getMaxMarks());
			ps.setString(5, task.getAttachFilePath());
			ps.setString(6, task.getAttachFileName());
			ps.setInt(7, task.getId());
			ps.setInt(8, task.getFacultyId());
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] update error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] update error: " + ex.getMessage());
			}
		}
	}

	public static boolean softDelete(int id, int facultyId) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
						UPDATE task SET is_active = 0
						WHERE id=? AND faculty_id = ?
					""";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, facultyId);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] softDelete error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] softDelete error: " + ex.getMessage());
			}
		}
	}

	public static int countByFaculty(int facultyId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT COUNT(*) FROM task WHERE faculty_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] countByFaculty error: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] countByFaculty error: " + ex.getMessage());
			}
		}
	}
	
	public static int countPendingReview(int facultyId) {
		String sql = """
					SELECT COUNT(*) FROM submissions s
					JOIN task t ON s.task_id = t.id
					WHERE t.faculty_id = ? AND s.status IN ('SUBMITTED', 'LATE')
				""";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] countPendingReview error: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] countPendingReview error: " + ex.getMessage());
			}
		}
	}
	
	public static int countReviewed(int facultyId) {
		String sql = """
					SELECT COUNT(*) FROM submissions s
					JOIN task t ON s.task_id = t.id
					WHERE t.faculty_id = ? AND s.status IN ('NEEDS_IMPROVEMENT','APPROVED','REJECTED')
				""";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facultyId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] countReviewed error: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] countReviewed error: " + ex.getMessage());
			}
		}
	}
	
	public static List<Task> findAssignedToStudent(int studentId){
		String sql = """
					SELECT t.*, u.full_name as faculty_name
					FROM task t 
					JOIN users u ON t.faculty_id = u.id
					JOIN submissions s ON s.task_id = t.id AND s.student_id = ?
					WHERE t.is_active = 1
					AND (
					    EXISTS(
					        SELECT 1 FROM task_assignments ta
					        WHERE ta.task_id = t.id
					        AND ta.assignment_type = 'INDIVIDUAL'
					        AND ta.student_id = ?
					    )
					        OR
					    EXISTS(
					        SELECT 1 FROM task_assignments ta
					        JOIN batch_students bs ON bs.batch_id = ta.batch_id
					        WHERE ta.task_id = t.id
					        AND ta.assignment_type = 'BATCH'
					        AND ta.student_id = ?
					    ) 
					  )
					ORDER BY t.deadline ASC        
				""";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, studentId);
			ps.setInt(3, studentId);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs,false));
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("[TaskDAO] findByFaculty error: " + ex.getMessage());
			return list;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("[TaskDAO] findByFaculty error: " + ex.getMessage());
			}
		}
	}
	
	private static Task mapRow(ResultSet rs, boolean statsIncluded) throws SQLException {
		Task t = new Task();
		t.setId(rs.getInt("id"));
		t.setTitle(rs.getString("title"));
		t.setDescription(rs.getString("description"));
		t.setFacultyId(rs.getInt("faculty_id"));
		t.setMaxMarks(rs.getInt("max_marks"));
		t.setActive(rs.getBoolean("is_active"));
		t.setAttachFileName(rs.getString("attach_file_name"));
		t.setAttachFilePath(rs.getString("attach_file_path"));
		t.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
		t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		t.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
		
		if(statsIncluded) {
			t.setTotalAssigned(rs.getInt("total_assigned"));
			t.setTotalSubmitted(rs.getInt("total_submitted"));
			t.setPendingReview(rs.getInt("pending_review"));
		}
		return t;
		
	}

}