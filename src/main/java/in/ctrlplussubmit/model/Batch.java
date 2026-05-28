package in.ctrlplussubmit.model;

import java.time.LocalDateTime;

public class Batch {
	
	private int id;
	private String batchName;
	private String description;
	private int facultyId;
	private String facultyName;
	private boolean isActive;
	private int studentCount;
	private int activeTasks;
	private int upcomingCount;
	private LocalDateTime createdAt;
	
	public Batch() {
		
	}

	public Batch(String batchName, String description, int facultyId) {
		super();
		this.batchName = batchName;
		this.description = description;
		this.facultyId = facultyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public int getActiveTasks() {
		return activeTasks;
	}

	public void setActiveTasks(int activeTasks) {
		this.activeTasks = activeTasks;
	}

	public int getUpcomingCount() {
		return upcomingCount;
	}

	public void setUpcomingCount(int upcomingCount) {
		this.upcomingCount = upcomingCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
