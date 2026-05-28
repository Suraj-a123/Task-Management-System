package in.ctrlplussubmit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ctrlplussubmit.model.AccountRequest;
import in.ctrlplussubmit.util.DBConnection;

public class AccountRequestDAO {
	
	public static boolean save(AccountRequest ar) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "INSERT INTO account_requests (full_name, email, requested_role, message) VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ar.getFullName());
			ps.setString(2,ar.getEmail());
			ps.setString(3, ar.getRequestedRole());
			ps.setString(4, ar.getMessage());
			return ps.executeUpdate()>0;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] save error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] save error: "+ex.getMessage());
			}
		}
	}
	
	public static AccountRequest findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM account_requests WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return mapRow(rs);
			}
			return null;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] findById error: "+ex.getMessage());
			return null;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] findById error: "+ex.getMessage());
			}
		}
	}
	
	public static AccountRequest findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM account_requests WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return mapRow(rs);
			}
			return null;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] findByEmail error: "+ex.getMessage());
			return null;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] findByEmail error: "+ex.getMessage());
			}
		}
	}
	
	public static List<AccountRequest> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AccountRequest> list = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM account_requests";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs));
			}
			return list;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] findAll error: "+ex.getMessage());
			return list;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] findAll error: "+ex.getMessage());
			}
		}
	}
	
	public static List<AccountRequest> getPendings() {
		return findByStatus("PENDING");
	}
	
	public static int countPending() {
		return getPendings().size();
	}
	
	private static List<AccountRequest> findByStatus(String status){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AccountRequest> list = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM account_requests WHERE status = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(mapRow(rs));
			}
			return list;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] getPendings error: "+ex.getMessage());
			return list;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] getPendings error: "+ex.getMessage());
			}
		}
	}
	
	public static boolean approv(int id) {
		return updateStatus(id,"APPROVED");
	}
	public static boolean reject(int id) {
		return updateStatus(id,"REJECTED");
	}
	private static boolean updateStatus(int id, String status) {
		PreparedStatement ps = null;
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "UPDATE account_requests SET status = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			return ps.executeUpdate()>0;
		}catch(SQLException ex) {
			System.out.println("[AccountRequestDAO] updateStatus error: "+ex.getMessage());
			return false;
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException ex) {
				System.out.println("[AccountRequestDAO] updateStatus error: "+ex.getMessage());
			}
		}
	}
	private static AccountRequest mapRow(ResultSet rs) throws SQLException {
		AccountRequest ar = new AccountRequest();
		ar.setId(rs.getInt("id"));
		ar.setFullName(rs.getString("full_name"));
		ar.setEmail(rs.getString("email"));
		ar.setMessage(rs.getString("message"));
		ar.setRequestedRole(rs.getString("requested_role"));
		ar.setStatus(rs.getString("status"));
		ar.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
		ar.setReviewedAt(rs.getTimestamp("reviewed_at").toLocalDateTime());
		return ar;
	}
}
