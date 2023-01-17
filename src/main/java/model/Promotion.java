package model;

public class Promotion {
	private int promotion_id;
	private int employee_id;
	private String new_job_category;
	private int new_rank;
	private String status;
	private String employee_msg;
	private int current_rank;
	private String current_job_category;


	public int getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getNew_job_category() {
		return new_job_category;
	}
	public void setNew_job_category(String new_job_category) {
		this.new_job_category = new_job_category;
	}
	public int getNew_rank() {
		return new_rank;
	}
	public void setNew_rank(int new_rank) {
		this.new_rank = new_rank;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmployee_msg() {
		return employee_msg;
	}
	public void setEmployee_msg(String employee_msg) {
		this.employee_msg = employee_msg;
	}
	public int getCurrent_rank() {
		return current_rank;
	}
	public void setCurrent_rank(int current_rank) {
		this.current_rank = current_rank;
	}
	public String getCurrent_job_category() {
		return current_job_category;
	}
	public void setCurrent_job_category(String current_job_category) {
		this.current_job_category = current_job_category;
	}




}
