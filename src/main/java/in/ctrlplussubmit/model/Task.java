package in.ctrlplussubmit.model;

import java.time.LocalDateTime;

public class Task {
	private int id;
	private String title;
	private String description;
	private int facultyId;
	private String facultyName;
	private LocalDateTime deadline;
	private String attachFilePath;
	private String attachFileName;
	private int maxMarks;
	private boolean isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private int totalAssigned;
	private int totalSubmitted;
	private int pendingReview;
	private String deadlineStatus;
	
	public Task() {
		
	}

	public Task(String title, String description, int facultyId, LocalDateTime deadline, int maxMarks) {
		super();
		this.title = title;
		this.description = description;
		this.facultyId = facultyId;
		this.deadline = deadline;
		this.maxMarks = maxMarks;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the facultyId
	 */
	public int getFacultyId() {
		return facultyId;
	}

	/**
	 * @param facultyId the facultyId to set
	 */
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	/**
	 * @return the facultyName
	 */
	public String getFacultyName() {
		return facultyName;
	}

	/**
	 * @param facultyName the facultyName to set
	 */
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	/**
	 * @return the deadline
	 */
	public LocalDateTime getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the attachFilePath
	 */
	public String getAttachFilePath() {
		return attachFilePath;
	}

	/**
	 * @param attachFilePath the attachFilePath to set
	 */
	public void setAttachFilePath(String attachFilePath) {
		this.attachFilePath = attachFilePath;
	}

	/**
	 * @return the attachFileName
	 */
	public String getAttachFileName() {
		return attachFileName;
	}

	/**
	 * @param attachFileName the attachFileName to set
	 */
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	/**
	 * @return the maxMarks
	 */
	public int getMaxMarks() {
		return maxMarks;
	}

	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the totalAssigned
	 */
	public int getTotalAssigned() {
		return totalAssigned;
	}

	/**
	 * @param totalAssigned the totalAssigned to set
	 */
	public void setTotalAssigned(int totalAssigned) {
		this.totalAssigned = totalAssigned;
	}

	/**
	 * @return the totalSubmitted
	 */
	public int getTotalSubmitted() {
		return totalSubmitted;
	}

	/**
	 * @param totalSubmitted the totalSubmitted to set
	 */
	public void setTotalSubmitted(int totalSubmitted) {
		this.totalSubmitted = totalSubmitted;
	}

	/**
	 * @return the pendingReview
	 */
	public int getPendingReview() {
		return pendingReview;
	}

	/**
	 * @param pendingReview the pendingReview to set
	 */
	public void setPendingReview(int pendingReview) {
		this.pendingReview = pendingReview;
	}

	/**
	 * @return the deadlineStatus
	 */
	public String getDeadlineStatus() {
		return deadlineStatus;
	}

	/**
	 * @param deadlineStatus the deadlineStatus to set
	 */
	public void setDeadlineStatus(String deadlineStatus) {
		this.deadlineStatus = deadlineStatus;
	}
	
}
