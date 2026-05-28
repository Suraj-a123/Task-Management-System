package in.ctrlplussubmit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ctrlplussubmit.model.User;
import in.ctrlplussubmit.util.DBConnection;

public class UserDao {
	public static User findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from users Where id = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				User user = mapRow(rs);
				return user;
			}
			return null;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] findByid error: "+ex.getMessage());
			return null;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] findByid error: "+ex.getMessage());
			}
		}
	}
	//helper method
	private static User mapRow(ResultSet rs) throws SQLException{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFullName(rs.getString("full_name"));
		user.setEmail(rs.getString("email"));
		user.setRole(rs.getString("role"));
		user.setActive(rs.getBoolean("is_active"));
		user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
		return user;
	}
	public static User findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM users WHERE email = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return mapRow(rs);
			}
			return null;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] findByEmail error: "+ex.getMessage());
			return null;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] findByEmail error: "+ex.getMessage());
			}
		}
	}
	public static User findByRole(String role) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from users Where role = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			rs = ps.executeQuery();
			if(rs.next()) {
				User user = mapRow(rs);
				return user;
			}
			return null;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] findByid error: "+ex.getMessage());
			return null;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] findByid error: "+ex.getMessage());
			}
		}
	}
	public static List<User> findAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list= new ArrayList();
		try {
			String sql = "SELECT * FROM users ORDER BY created_at DESC";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs));
			}
			return list;
		}
		catch(SQLException ex) {
			System.out.println("[UserDAO] findAll error: "+ex.getMessage());
			return list;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] findAll error: "+ex.getMessage());
			}
	}
}
	public static boolean save(User user) {
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO users (full_name, email, password, role, is_active) values(?,?,?,?,?)";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());
			ps.setBoolean(5, user.isActive());
			int ans = ps.executeUpdate();
			return ans>0;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] save error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] save error: "+ex.getMessage());
			}
		}
	}
	public static boolean updateUser(User user) {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET full_name = ?,email = ?, profile_pic = ? WHERE id=?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getProfilePic());
			ps.setInt(4, user.getId());
			int ans = ps.executeUpdate();
			return ans>0;
		}catch(SQLException ex){
			System.out.println("[UserDAO] UpdateUser error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] UpdateUser error: "+ex.getMessage());
			}
		}
	}
	public static boolean updatePassword(String newPassword, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET password = ? WHERE id = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, id);
			return ps.executeUpdate() > 0;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] UpdatePassword error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] UpdatePassword error: "+ex.getMessage());
			}
		}
	}
	public static boolean updateRole(String role, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET role = ? WHERE id = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			ps.setInt(2, id);
			return ps.executeUpdate() > 0;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] UpdateRole error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] UpdateRole error: "+ex.getMessage());
			}
		}
	}
	public static int countByRoles(String role) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(*) FROM users WHERE role = ?";
			Connection conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}catch(SQLException ex) {
				System.out.println("[UserDAO] countByRole error: "+ex.getMessage());
				return 0;
			}finally {
				try {
					if(ps != null) {
						ps.close();
					}
					if(rs != null) {
						rs.close();
					}
				}catch(SQLException ex) {
					System.out.println("[UserDAO] countByRole error: "+ex.getMessage());
				}
			}
		}
	public static boolean setActiveStatus(int active, int id) {
		PreparedStatement ps=null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "UPDATE users SET is_active = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, active);
			ps.setInt(2, id);
			return ps.executeUpdate()>0;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] setActiveStatus error :" + ex.getMessage());
			return false;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] setActiveStatus error :" + ex.getMessage());
			}
		}
	}
	public static List<User> search(String keyword){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM users WHERE full_name LIKE ? OR email LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setString(2, "%"+keyword+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(mapRow(rs));
			}
			return users;
		}catch(SQLException ex) {
			System.out.println("[UserDAO] search error: "+ex.getMessage());
			return users;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[UserDAO] search error: "+ex.getMessage());
			}
		}
	}
}

