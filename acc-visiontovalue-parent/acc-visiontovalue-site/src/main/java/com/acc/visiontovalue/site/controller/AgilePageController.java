package com.acc.visiontovalue.site.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.visiontovalue.site.model.Question;

@Controller
@RequestMapping("/agile")
public class AgilePageController {

	@Value("${acc.visiontovalue.site.basepath}")
	private String basepath;

	@RequestMapping(method=RequestMethod.GET)
	public String loadAgileCommunityPage(HttpServletRequest request,Model model){
		
		model.addAttribute("base_path",basepath);	
		return "agile";
	}
	
	@RequestMapping(value="/question" ,method=RequestMethod.GET)
	public String loadQuestionsTab(@RequestParam("page") String page,HttpServletRequest request,Model model){
		
		
		List<Question> questionList = new ArrayList<Question>();
		Question question1 = new Question ();
		question1.setQuestionId(1L);
		question1.setQuestionString("What is Agile");
		question1.setLastModifiedDate(new Date());
		
		Question question2 = new Question ();
		question2.setQuestionId(2L);
		question2.setQuestionString("Question 2");
		question2.setLastModifiedDate(new Date());
		
		Question question3 = new Question ();
		question3.setQuestionId(3L);
		question3.setQuestionString("Question 3");
		question3.setLastModifiedDate(new Date());
		
		Question question4 = new Question ();
		question4.setQuestionId(4L);
		question4.setQuestionString("Question 4");
		question4.setLastModifiedDate(new Date());

		Question question5 = new Question ();
		question5.setQuestionId(5L);
		question5.setQuestionString("Question 5");
		question5.setLastModifiedDate(new Date());
		
		Question question6 = new Question ();
		question6.setQuestionId(6L);
		question6.setQuestionString("Question 6");
		question6.setLastModifiedDate(new Date());
		
		Question question7 = new Question ();
		question7.setQuestionId(7L);
		question7.setQuestionString("Question 7");
		question7.setLastModifiedDate(new Date());
		
		questionList.add(question1);
		questionList.add(question2);
		questionList.add(question3);
		questionList.add(question4);
		questionList.add(question5);
		questionList.add(question6);
		questionList.add(question7);
		
		
		model.addAttribute("base_path",basepath);	
		model.addAttribute("questionList",questionList);	
		
		return "question";
	}
	
}
