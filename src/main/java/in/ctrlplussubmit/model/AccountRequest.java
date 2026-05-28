package in.ctrlplussubmit.model;

import java.time.LocalDateTime;

public class AccountRequest {
	
	private int id;
	private String fullName;
	private String email;
	private String requestedRole;
	private String message;
	private String status;
	private LocalDateTime requestedAt;
	private LocalDateTime reviewedAt;
	
	public AccountRequest() {
		
	}
	
	public AccountRequest(String fullName, String email, String requestedRole, String message) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.requestedRole = requestedRole;
		this.message = message;
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

	public String getRequestedRole() {
		return requestedRole;
	}

	public void setRequestedRole(String requestedRole) {
		this.requestedRole = requestedRole;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public LocalDateTime getReviewedAt() {
		return reviewedAt;
	}

	public void setReviewedAt(LocalDateTime reviewedAt) {
		this.reviewedAt = reviewedAt;
	}
	
}
