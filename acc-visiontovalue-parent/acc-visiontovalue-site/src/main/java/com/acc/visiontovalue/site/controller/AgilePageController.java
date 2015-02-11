package com.acc.visiontovalue.site.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.visiontovalue.site.model.Question;

@Controller
@RequestMapping(value="/agile")
public class AgilePageController {

	@Value("${acc.visiontovalue.site.basepath}")
	private String basepath;

	@RequestMapping(method=RequestMethod.GET)
	public String loadAgileCommunityPage(HttpServletRequest request,Model model){
		
		model.addAttribute("base_path",basepath);
		model.addAttribute("communityName","Agile");
		model.addAttribute("community","agile");
		return "home";
	}
	
	
	@RequestMapping(value="/page" ,method=RequestMethod.GET)
	public String loadPageOnButtonClick(@RequestParam("page") String page,HttpServletRequest request,Model model){
		
		model.addAttribute("base_path",basepath);
		model.addAttribute("community","agile");
		return page;
	}
	
	
	@RequestMapping(value="/question_view" ,method=RequestMethod.GET)
	public String loadQuestionsTab(@RequestParam("tab") String tab,HttpServletRequest request,Model model){
		
		List<Question> questionList = new ArrayList<Question>();
		
		for(Question question:getQuestionList()){
			
			if(tab.equals(question.getCategory())){
				
				questionList.add(question);
			}
			
		}
		
		model.addAttribute("base_path",basepath);
		model.addAttribute("community","agile");
		model.addAttribute("questionList",questionList);	
		
		return "question_view";
	}

	@RequestMapping(value="/question_detail" ,method=RequestMethod.GET)
	public String loadQuestionsDetail(@RequestParam("questionId") Long questionId,HttpServletRequest request,Model model){
		
		Question question = null;
		
		for(Question question1:getQuestionList()){
			
			if(questionId == question1.getQuestionId()){
				
				question = question1;
				break;
			}
			
		}
		
		
		model.addAttribute("question",question);
		
		return "question_detail";
	}
	
	private List<Question> getQuestionList() {
		
		List<Question> questionList = new ArrayList<Question>();
		Question question1 = new Question ();
		question1.setQuestionId(1L);
		question1.setCommunity("agile");
		question1.setAnswerCount(2);
		question1.setCategory("visiontoroadmap");
		question1.setQuestionString("Question A");
		question1.setDetailedDescription("Detailed description for "+question1.getQuestionString());
		question1.setLastModifiedDate(new Date());
		
		Question question2 = new Question ();
		question2.setQuestionId(2L);
		question2.setCommunity("agile");
		question2.setAnswerCount(3);
		question2.setCategory("visiontoroadmap");
		question2.setQuestionString("Question  B");
		question2.setDetailedDescription("Detailed description for "+question2.getQuestionString());
		question2.setLastModifiedDate(new Date());
		
		Question question3 = new Question ();
		question3.setQuestionId(3L);
		question3.setCommunity("agile");
		question3.setAnswerCount(5);
		question3.setCategory("visiontoroadmap");
		question3.setQuestionString("Question  C");
		question3.setDetailedDescription("Detailed description for "+question3.getQuestionString());
		question3.setLastModifiedDate(new Date());
		
		Question question4 = new Question ();
		question4.setQuestionId(4L);
		question4.setCommunity("agile");
		question4.setAnswerCount(0);
		question4.setCategory("visiontoroadmap");
		question4.setQuestionString("Question  D");
		question4.setDetailedDescription("Detailed description for "+question4.getQuestionString());
		question4.setLastModifiedDate(new Date());

		Question question5 = new Question ();
		question5.setQuestionId(5L);
		question5.setCommunity("agile");
		question5.setAnswerCount(1);
		question5.setCategory("visiontoroadmap");
		question5.setQuestionString("Question E");
		question5.setDetailedDescription("Detailed description for "+question5.getQuestionString());
		question5.setLastModifiedDate(new Date());
		
		Question question6 = new Question ();
		question6.setQuestionId(6L);
		question6.setCommunity("agile");
		question6.setAnswerCount(7);
		question6.setCategory("visiontoroadmap");
		question6.setQuestionString("Question F");
		question6.setDetailedDescription("Detailed description for "+question6.getQuestionString());
		question6.setLastModifiedDate(new Date());
		
		Question question7 = new Question ();
		question7.setQuestionId(7L);
		question7.setCommunity("agile");
		question7.setAnswerCount(2);
		question7.setCategory("visiontoroadmap");
		question7.setQuestionString("Question G");
		question7.setDetailedDescription("Detailed description for "+question7.getQuestionString());
		question7.setLastModifiedDate(new Date());
		
		Question question8 = new Question ();
		question8.setQuestionId(8L);
		question8.setCommunity("agile");
		question8.setAnswerCount(15);
		question8.setCategory("roadmaptodeploy");
		question8.setQuestionString("Question H");
		question8.setDetailedDescription("Detailed description for "+question8.getQuestionString());
		question8.setLastModifiedDate(new Date());
		
		Question question9 = new Question ();
		question9.setQuestionId(9L);
		question9.setCommunity("agile");
		question9.setAnswerCount(4);
		question9.setCategory("roadmaptodeploy");
		question9.setQuestionString("Question  I");
		question9.setDetailedDescription("Detailed description for "+question9.getQuestionString());
		question9.setLastModifiedDate(new Date());
		
		Question question10 = new Question ();
		question10.setQuestionId(10L);
		question10.setCommunity("agile");
		question10.setAnswerCount(0);
		question10.setCategory("roadmaptodeploy");
		question10.setQuestionString("Question  J");
		question10.setDetailedDescription("Detailed description for "+question10.getQuestionString());
		question10.setLastModifiedDate(new Date());
		
		Question question11 = new Question ();
		question11.setQuestionId(11L);
		question11.setCommunity("agile");
		question11.setAnswerCount(20);
		question11.setCategory("roadmaptodeploy");
		question11.setQuestionString("Question  K");
		question11.setDetailedDescription("Detailed description for "+question11.getQuestionString());
		question11.setLastModifiedDate(new Date());
		
		Question question12 = new Question ();
		question12.setQuestionId(12L);
		question12.setCommunity("agile");
		question12.setAnswerCount(6);
		question12.setCategory("deploytovalue");
		question12.setQuestionString("Question L");
		question12.setDetailedDescription("Detailed description for "+question12.getQuestionString());
		question12.setLastModifiedDate(new Date());
		
		Question question13 = new Question ();
		question13.setQuestionId(13L);
		question13.setCommunity("agile");
		question13.setAnswerCount(9);
		question13.setCategory("deploytovalue");
		question13.setQuestionString("Question  M");
		question13.setDetailedDescription("Detailed description for "+question13.getQuestionString());
		question13.setLastModifiedDate(new Date());
		
		Question question14 = new Question ();
		question14.setQuestionId(14L);
		question14.setCommunity("agile");
		question14.setAnswerCount(14);
		question14.setCategory("deploytovalue");
		question14.setQuestionString("Question  N");
		question14.setDetailedDescription("Detailed description for "+question14.getQuestionString());
		question14.setLastModifiedDate(new Date());
		
		questionList.add(question1);
		questionList.add(question2);
		questionList.add(question3);
		questionList.add(question4);
		questionList.add(question5);
		questionList.add(question6);
		questionList.add(question7);
		questionList.add(question8);
		questionList.add(question9);
		questionList.add(question10);
		questionList.add(question11);
		questionList.add(question12);
		questionList.add(question13);
		questionList.add(question14);
		
		return questionList;
	}
	
}
