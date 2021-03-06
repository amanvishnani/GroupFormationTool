package csci5408.catme.domain;

import com.opencsv.bean.CsvBindByName;

public class User {

	private Long id;

	@CsvBindByName(column = "studentId")
	private String studentId;

	@CsvBindByName(column = "firstName")
	private String firstName;

	@CsvBindByName(column = "lastName")
	private String lastName;

	private Boolean admin;

	@CsvBindByName(column = "emailId")
	private String emailId;

	private String password;

	public User() {

	}

	public User(Long id, String firstName, String lastName, String studentId, Boolean admin, String emailId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
		this.admin = admin;
		this.emailId = emailId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
