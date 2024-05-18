package bean;

public class Departments {
    private int department_id;
    private String department_name;

    public Departments() {
    }

    public Departments(String department_name) {
        this.department_name = department_name;
    }

    public Departments(int department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public final int getDepartment_id() {return department_id;}
    public final String getDepartment_name() {return department_name;}
    
    public void setDepartment_name(final String department_name) {this.department_name = department_name;}
}
