package com.acc.visiontovalue.site.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(getterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "bestPracticeId","topic","problemStatement","solutionString","lastUpdatedTime"})
public class BestPractice {
	private static final long serialVersionUID = 2056018561552669496L;
	
	@JsonProperty
	private Long bestPracticeId;
	
	@JsonProperty
	private String topic;
	
	@JsonProperty
	private String problemStatement;
	
	@JsonProperty
	private String solutionString;

	@JsonProperty
	private Date lastUpdatedTime;
	
	public Long getBestPracticeId() {
		return bestPracticeId;
	}

	public void setBestPracticeId(Long bestPracticeId) {
		this.bestPracticeId = bestPracticeId;
	}

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
