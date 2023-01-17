package model;

public class Goals {
	private int goalsId;
	private int employeeId;
	private int goalYear;
	private String goals;
	public Goals() {
		super();
	}
	public int getGoalsId() {
		return goalsId;
	}
	public void setGoalsId(int goalsId) {
		this.goalsId = goalsId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getGoalYear() {
		return goalYear;
	}
	public void setGoalYear(int goalYear) {
		this.goalYear = goalYear;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}


}
