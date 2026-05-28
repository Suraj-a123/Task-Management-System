package in.ctrlplussubmit.model;

import java.time.LocalDateTime;

public class User {
	private int id;
	private String fullName;
	private String email;
	private String password;
	private String role;
	private boolean isActive;
	private String profilePic;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public User(String fullName, String email, String password, String role) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User() {
		
	}

	public User(int id, String fullName, String email, String password, String role, boolean isActive,
			String profilePic, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.isActive = isActive;
		this.profilePic = profilePic;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public boolean isAdmin() {
		return "ADMIN".equals(role);
	}
	
	public boolean isFaculty() {
		return "FACULTY".equals(role);
	}
	
	public boolean isStudent() {
		return "STUDENT".equals(role);
	}
	
	public User safeUser() {
		User safe = new User();
		safe.id = this.id;
		safe.fullName = this.fullName;
		safe.email = this.email;
		safe.role = this.role;
		safe.isActive = this.isActive;
		safe.createdAt = this.createdAt;
		safe.updatedAt = this.updatedAt;
		return safe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
