package com.revature.beans;

public class Employee {
	
	public Employee(int id, int reportsto, String firstname, String lastname, String email) {
		super();
		this.id = id;
		this.reportsto = reportsto;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	public Employee() {
		
	}
	private int id;
	private int reportsto;
	private String firstname;
	private String lastname;
	private String email;
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", reportsto=" + reportsto + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReportsto() {
		return reportsto;
	}
	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
