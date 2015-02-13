package com.acc.visiontovalue.site.model;

import java.util.Date;
import java.util.List;

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
@JsonPropertyOrder({ "questionId","questionString","detailedDescription","answerCount","answerList","community","category","lastModifiedDate" })
public class Question {

	private static final long serialVersionUID = 3056018561552669496L;

	@JsonProperty
	private Long questionId;
	
	@JsonProperty
	private String questionString;
	
	@JsonProperty
	private String detailedDescription;
	
	
	@JsonProperty
	private Integer answerCount;
	
	@JsonProperty
	private List<Answer> answerList;
	
	@JsonProperty
	private String community;
	
	@JsonProperty
	private String category;
	
	@JsonProperty
	private Date lastModifiedDate;
	
	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	
	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}
	
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
}
