package com.acc.visiontovalue.site.form;

import java.util.Date;

public class EditLibraryForm {

	private String topic;
	
	private String problemStatement;
	
	private String solutionString;

	private Date lastUpdatedTime;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}

	public String getSolutionString() {
		return solutionString;
	}

	public void setSolutionString(String solutionString) {
		this.solutionString = solutionString;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
}
