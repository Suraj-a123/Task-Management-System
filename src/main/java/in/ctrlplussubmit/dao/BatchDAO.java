package in.ctrlplussubmit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ctrlplussubmit.model.Batch;
import in.ctrlplussubmit.model.User;
import in.ctrlplussubmit.util.DBConnection;

public class BatchDAO {
	public static boolean create(Batch batch) {

		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "INSERT INTO batches(batch_name, description, faculty_id) VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, batch.getBatchName());
			ps.setString(2, batch.getDescription());
			ps.setInt(3, batch.getFacultyId());

			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] create error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[BatchDAO] create error: " + ex.getMessage());
			}
		}
	}

	public static boolean update(Batch batch) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "UPDATE batches SET batch_name = ?, description = ?, faculty_id=? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, batch.getBatchName());
			ps.setString(2, batch.getDescription());
			ps.setInt(3, batch.getFacultyId());
			ps.setInt(4, batch.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] update error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[BatchDAO] update error: " + ex.getMessage());
			}
		}
	}

	public static boolean setActiveStatus(int batchId, boolean status) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "UPDATE batches SET is_active = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, status);
			ps.setInt(2, batchId);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] setActiveStatus error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[BatchDAO] setActiveStatus error: " + ex.getMessage());
			}
		}
	}

	public static boolean addStudent(int batchId, int studentId) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "INSERT INTO batch_students (batch_id, student_id) VALUES(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, batchId);
			ps.setInt(2, studentId);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] addStudent error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[BatchDAO] addStudent error: " + ex.getMessage());
			}
		}
	}

	public static boolean removeStudent(int batchId, int studentId) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "DELETE FROM batch_students WHERE batch_id = ? AND student_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, batchId);
			ps.setInt(2, studentId);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] removeStudent error: " + ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				System.out.println("[BatchDAO] removeStudent error: " + ex.getMessage());
			}
		}
	}

	public static List<Integer> getStudentIds(int batchId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT student_id FROM batch_students WHERE batch_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, batchId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("student_id"));
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] getStudentIds error: " + ex.getMessage());
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
				System.out.println("[BatchDAO] getStudentIds error: " + ex.getMessage());
			}
		}
	}
	
	public static int countBatches() {
		return countByQuery("SELECT COUNT(*) FROM batches");
	}
	
	public static int countActiveBatches() {
		return countByQuery("SELECT COUNT(*) FROM batches WHERE is_active = 1");
	}
	
	private static int countByQuery(String query) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] countByQuery error: " + ex.getMessage());
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
				System.out.println("[BatchDAO] countByQuery error: " + ex.getMessage());
			}
		}
	}
	public static List<Batch> findAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Batch> list = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
					SELECT b.*, u.full_name as faculty_name, COUNT(bs.student_id) as total 
					FROM batches b
					LEFT JOIN users u ON b.faculty_id = u.id
					LEFT JOIN batch_students bs ON bs.batch_id = b.id
					GROUP BY b.id, faculty_name
					ORDER BY b.created_at DESC
					""";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs));
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] findAll error: " + ex.getMessage());
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
				System.out.println("[BatchDAO] findAll error: " + ex.getMessage());
			}
		}
	}
	
	public static Batch findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
					SELECT b.*, u.full_name as faculty_name, COUNT(bs.student_id) as total 
					FROM batches b
					LEFT JOIN users u ON b.faculty_id = u.id
					LEFT JOIN batch_students bs ON bs.batch_id = b.id
					WHERE b.id = ?
					GROUP BY b.id, faculty_name
					ORDER BY b.created_at DESC
					""";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return mapRow(rs);
			}
			return null;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] findById error: " + ex.getMessage());
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
				System.out.println("[BatchDAO] findById error: " + ex.getMessage());
			}
		}
	}
	
	public static List<Batch> findByFaculty(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Batch> list = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = """
					SELECT b.*, u.full_name as faculty_name, COUNT(bs.student_id) as total 
					FROM batches b
					LEFT JOIN users u ON b.faculty_id = u.id
					LEFT JOIN batch_students bs ON bs.batch_id = b.id
					WHERE b.faculty_id = ?
					GROUP BY b.id, faculty_name
					ORDER BY b.created_at DESC
					""";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs));
			}
			return list;
		} catch (SQLException ex) {
			System.out.println("[BatchDAO] findByFaculty error: " + ex.getMessage());
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
				System.out.println("[BatchDAO] findByFaculty error: " + ex.getMessage());
			}
		}
	}
	
	// helper method
		private static Batch mapRow(ResultSet rs) throws SQLException {
			Batch batch = new Batch();
			batch.setId(rs.getInt("id"));
			batch.setBatchName(rs.getString("batch_name"));
			batch.setDescription(rs.getString("description"));
			batch.setFacultyId(rs.getInt("faculty_id"));
			batch.setActive(rs.getBoolean("is_active"));
			batch.setFacultyName(rs.getString("faculty_name"));
			batch.setStudentCount(rs.getInt("total"));
			batch.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			return batch;
		}

}
