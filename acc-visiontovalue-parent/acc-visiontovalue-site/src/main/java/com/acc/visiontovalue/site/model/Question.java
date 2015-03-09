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
@JsonPropertyOrder({ "questionId","questionString","detailedDescription","recommendedSolution","commentCount","commentList","community","category","isScenario","status","lastModifiedDate" })
public class Question {

	private static final long serialVersionUID = 3056018561552669496L;

	@JsonProperty
	private Long questionId;
	
	@JsonProperty
	private String questionString;
	
	@JsonProperty
	private String detailedDescription;
	
	@JsonProperty
	private String recommendedSolution;
	
	
	@JsonProperty
	private Integer commentCount;
	
	@JsonProperty
	private List<Comment> commentList;
	
	@JsonProperty
	private String community;
	
	@JsonProperty
	private String category;
	
	@JsonProperty
	private Boolean isScenario;
	
	@JsonProperty
	private String status;
	
	@JsonProperty
	private Date lastModifiedDate;
	
	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
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

	public Boolean getIsScenario() {
		return isScenario;
	}

	public void setIsScenario(Boolean isScenario) {
		this.isScenario = isScenario;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getRecommendedSolution() {
		return recommendedSolution;
	}

	public void setRecommendedSolution(String recommendedSolution) {
		this.recommendedSolution = recommendedSolution;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
