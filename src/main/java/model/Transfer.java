package model;

public class Transfer {
	private int transfer_id;
	private int employee_id;
	private int new_department_id;
	private String new_department_name;
	private String status;
	public int getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getNew_department_id() {
		return new_department_id;
	}
	public void setNew_department_id(int new_department_id) {
		this.new_department_id = new_department_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNew_department_name() {
		return new_department_name;
	}
	public void setNew_department_name(String new_department_name) {
		this.new_department_name = new_department_name;
	}
	
	
}
