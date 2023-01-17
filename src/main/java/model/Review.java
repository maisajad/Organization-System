package model;

public class Review {
	private int review_id;
	private int employee_id ;
	private int review_year;
	private int manager_id;
	private String status;
	private String review_msg;
	private int rate;

	public Review() {
		super();
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getReview_year() {
		return review_year;
	}
	public void setReview_year(int review_year) {
		this.review_year = review_year;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReview_msg() {
		return review_msg;
	}
	public void setReview_msg(String review_msg) {
		this.review_msg = review_msg;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	

}
