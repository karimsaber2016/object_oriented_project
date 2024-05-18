package bean;

public class Employee {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String birth_date;
	private String job_title;
	private int department_id;

	public Employee() {
	}
	
	public Employee(String first_name, String last_name, String email, String birth_date, String job_title, int department_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.birth_date = birth_date;
		this.job_title = job_title;
		this.department_id = department_id;
	}

	public Employee(int employee_id, String first_name, String last_name, String email, String birth_date, String job_title, int department_id) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.birth_date = birth_date;
		this.job_title = job_title;
		this.department_id = department_id;
	}

	public final int getEmployee_id() {return employee_id;}
	public final String getFirst_name() {return first_name;}
	public final String getLast_name() {return last_name;}
	public final String getEmail() {return email;}
	public final String getBirth_date() {return birth_date;}
	public final String getJob_title() {return job_title;}
	public final int getDepartment_id() {return department_id;}

	public void setEmployee_id(final int employee_id) {this.employee_id = employee_id;}
	public void setFirst_name(final String first_name) {this.first_name = first_name;}
	public void setLast_name(final String last_name) {this.last_name = last_name;}
	public void setEmail(final String email) {this.email = email;}
	public void setBirth_date(final String birth_date) {this.birth_date = birth_date;}
	public void setJob_title(final String job_title) {this.job_title = job_title;}
	public void setDepartment_id(final int department_id) {this.department_id = department_id;}
}
