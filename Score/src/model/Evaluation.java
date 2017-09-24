package model;

public class Evaluation {

	String criteriaName;
	int score;
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Evaluation(String criteriaName, int score) {
		super();
		this.criteriaName = criteriaName;
		this.score = score;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
