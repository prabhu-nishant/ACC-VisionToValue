package com.acc.visiontovalue.site.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.visiontovalue.site.model.Answer;
import com.acc.visiontovalue.site.model.BestPractice;
import com.acc.visiontovalue.site.model.Question;

@Controller
@RequestMapping(value="/agile")
public class AgilePageController {

	@Value("${acc.visiontovalue.site.basepath}")
	private String basepath;

	@RequestMapping(method=RequestMethod.GET)
	public String loadAgileCommunityPage(HttpServletRequest request,Model model){
		return "redirect:/agile/questions" ;
	}
	
	@RequestMapping(value="/questions" ,method=RequestMethod.GET)
	public String loadAgileCommunityQuestionsPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"questions");
		return "questions";
	}

	
	@RequestMapping(value="/library" ,method=RequestMethod.GET)
	public String loadAgileCommunityLibraryPage(HttpServletRequest request,Model model){
		
		List<BestPractice> bestpracticesList = getBestPracticesList();
		
		setCommonAttributes(model,"library");
		model.addAttribute("bestpracticesList",bestpracticesList);
		
		return "library";
	}

	private List<BestPractice> getBestPracticesList() {
		List<BestPractice> bestpracticesList = new ArrayList<BestPractice>();
		
		BestPractice bestPractice1 = new BestPractice();
		bestPractice1.setBestPracticeId(1L);
		bestPractice1.setTopic("Best Practice 1");
		bestPractice1.setSolutionString("Solution String");
		bestPractice1.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice2 = new BestPractice();
		bestPractice2.setBestPracticeId(2L);
		bestPractice2.setTopic("Best Practice 2");
		bestPractice2.setSolutionString("Solution String");
		bestPractice2.setLastUpdatedTime(new Date());
		
		
		BestPractice bestPractice3 = new BestPractice();
		bestPractice3.setBestPracticeId(3L);
		bestPractice3.setTopic("Best Practice 3");
		bestPractice3.setSolutionString("Solution String");
		bestPractice3.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice4 = new BestPractice();
		bestPractice4.setBestPracticeId(4L);
		bestPractice4.setTopic("Best Practice 4");
		bestPractice4.setSolutionString("Solution String");
		bestPractice4.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice5 = new BestPractice();
		bestPractice5.setBestPracticeId(5L);
		bestPractice5.setTopic("Best Practice 5");
		bestPractice5.setSolutionString("Solution String");
		bestPractice5.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice6 = new BestPractice();
		bestPractice6.setBestPracticeId(6L);
		bestPractice6.setTopic("Best Practice 6");
		bestPractice6.setSolutionString("Solution String");
		bestPractice6.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice7 = new BestPractice();
		bestPractice7.setBestPracticeId(7L);
		bestPractice7.setTopic("Best Practice 7");
		bestPractice7.setSolutionString("Solution String");
		bestPractice7.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice8 = new BestPractice();
		bestPractice8.setBestPracticeId(4L);
		bestPractice8.setTopic("Best Practice 8");
		bestPractice8.setSolutionString("Solution String");
		bestPractice8.setLastUpdatedTime(new Date());
		
		BestPractice bestPractice9 = new BestPractice();
		bestPractice9.setBestPracticeId(9L);
		bestPractice9.setTopic("Best Practice 9");
		bestPractice9.setSolutionString("Solution String");
		bestPractice9.setLastUpdatedTime(new Date());
		
		bestpracticesList.add(bestPractice1);
		bestpracticesList.add(bestPractice2);
		bestpracticesList.add(bestPractice3);
		bestpracticesList.add(bestPractice4);
		bestpracticesList.add(bestPractice5);
		bestpracticesList.add(bestPractice6);
		bestpracticesList.add(bestPractice7);
		bestpracticesList.add(bestPractice8);
		bestpracticesList.add(bestPractice9);
		return bestpracticesList;
	}
	
	

	@RequestMapping(value="/library/detail/{bestPracticeId}" ,method=RequestMethod.GET)
	public String loadAgileCommunityLibraryDetailPage(@PathVariable("bestPracticeId") long bestPracticeId,HttpServletRequest request,Model model){
		
		BestPractice bestPractice = null;
		
		for(BestPractice temp:getBestPracticesList()){
			
			if(temp.getBestPracticeId() == bestPracticeId){
				
				bestPractice = temp ;
				break;
			}
			
		}
		setCommonAttributes(model,"library");
		model.addAttribute("bestPractice",bestPractice);	
		return "library_detail";
	}
	
	@RequestMapping(value="/ask_question" ,method=RequestMethod.GET)
	public String loadAgileCommunityAskQuestionPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"ask_question");
		return "ask_question";
	}
	
	@RequestMapping(value="/scenarios" ,method=RequestMethod.GET)
	public String loadAgileCommunityScenariosPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"scenarios");
		return "scenarios";
	}
	
	
	
	@RequestMapping(value="/questions/view" ,method=RequestMethod.GET)
	public String loadQuestionsTab(@RequestParam("tab") String tab,HttpServletRequest request,Model model){
		
		List<Question> questionList = new ArrayList<Question>();
		
		for(Question question:getQuestionList()){
			
			if(tab.equals(question.getCategory())){
				
				questionList.add(question);
			}
			
		}
		
		setCommonAttributes(model,"questions");
		model.addAttribute("questionList",questionList);	
		
		return "questions_view";
	}

	@RequestMapping(value="/questions/detail/{questionId}" ,method=RequestMethod.GET)
	public String loadQuestionsDetail(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = null;
		
		for(Question question1:getQuestionList()){
			
			if(questionId == question1.getQuestionId()){
				
				question = question1;
				break;
			}
			
		}
		
		setCommonAttributes(model,"questions");
		model.addAttribute("question",question);
		return "questions_detail";
	}
	
	private List<Question> getQuestionList() {
		
		List<Question> questionList = new ArrayList<Question>();
		Question question1 = new Question ();
		question1.setQuestionId(1L);
		question1.setCommunity("agile");
		question1.setAnswerCount(0);
		question1.setCategory("visiontoroadmap");
		question1.setQuestionString("How do I create vision for my product/program that is crisp yet compelling ?");
		question1.setDetailedDescription("What would be the best techniques ?");
		question1.setLastModifiedDate(new Date());
		question1.setRecommendedSolution("Vision Box:"
								+ "\nTechnique to identify the most exiting features of a product."
								+ "\n\nHow-To:"
								+"\n- Provide stakeholders a box (cereal or shoe) wrapped with white paper"
								+"\n- Ask them to design features on the box"
								+"\n- Front Side – Name, branding, key benefits"
								+"\n- Back Side – Key features, operating instructions");
		
		question1.setAnswerList(new ArrayList<Answer>());
		
		Question question2 = new Question ();
		question2.setQuestionId(2L);
		question2.setCommunity("agile");
		question2.setAnswerCount(0);
		question2.setCategory("visiontoroadmap");
		question2.setQuestionString("What are the essential ingredients of vision statement that will bring out the real essence of product vision ?");
		question2.setDetailedDescription("What are the essential ingredients of vision statement that will bring out the real essence of product vision ?");
		question2.setLastModifiedDate(new Date());
		question2.setRecommendedSolution("Elevator Pitch/Speech/Statement:"
				+"\nAn elevator pitch, elevator speech, or elevator statement is a short summary used to quickly and simply define a person, profession, product, service, organization or event and its value proposition"

				+"\n\nHow-To:"
				+"\nThe vision need to have below components:"
				+"\nFor     -  <<target customers>>"
				+"\nWho are dissatisfied with   -  <<the current market alternative>>"
				+"\nOur product is a   - <<new product category>>"
				+"\nThat provides   - <<key problem-solving capability>>"
				+"\nUnlike    - <<the product alternative>>"
				+"\nOur product    - <<describe the key product features>>");
		question2.setAnswerList(new ArrayList<Answer>());
		
		Question question3 = new Question ();
		question3.setQuestionId(3L);
		question3.setCommunity("agile");
		question3.setAnswerCount(0);
		question3.setCategory("visiontoroadmap");
		question3.setQuestionString("How do I make sure that the business needs are consolidated from different sources as organization is complex and there are too many stakeholders influencing the product/features being developed");
		question3.setDetailedDescription("How do I make sure that the business needs are consolidated from different sources as organization is complex and there are too many stakeholders influencing the product/features being developed");
		question3.setLastModifiedDate(new Date());
		question3.setRecommendedSolution("Voice of Customer (VOC):"
				+"\nVoice of the customer (VOC) is a term used to describe the in-depth process of capturing a customer's expectations, preferences and aversions. It is a market research technique that produces a detailed set of customer wants and needs, organized into a hierarchical structure, and then prioritized in terms of relative importance and satisfaction with current alternatives."

				+"\n\nHow-To:"
				+"\nThere are many possible ways to gather the information – focus groups, individual interviews, contextual inquiry, ethnographic techniques, etc. But all involve a series of structured in-depth interviews, which focus on the customers' experiences with current products or alternatives within the category under consideration. Needs statements are then extracted, organized into a more usable hierarchy, and then prioritized by the customers");
		question3.setAnswerList(new ArrayList<Answer>());
		
		Question question4 = new Question ();
		question4.setQuestionId(4L);
		question4.setCommunity("agile");
		question4.setAnswerCount(0);
		question4.setCategory("visiontoroadmap");
		question4.setQuestionString("How do I ensure that the high business value requirements flow seamlessly from business stakeholders to IT team?");
		question4.setDetailedDescription("How do I ensure that the high business value requirements flow seamlessly from business stakeholders to IT team?");
		question4.setLastModifiedDate(new Date());
		question4.setRecommendedSolution("Value Stream Mapping(VSM):"
				+"\nVSM is used to analyze an existing process to identify the improvement areas. It is a method of creating \"One Page Picture\" of all the activities and tasks that occur in a process, from the time a customer provides the requirement, until the application, product, or service meeting the requirement is provided to the customer. The goal is to depict data and information flows across and throughput all the process steps that are required to provide the application, product or service to the customer. VSM documents both value and non-value adding (wastes) steps in a process"

				+"\n\nHow-To:"
				 +"\n1. Create a charter, define the problem, set the goals and objectives, and select the mapping team."
				 +"\n2.Draw a current state value stream map, which shows the current steps, delays, and information flows required to deliver the target product or service."
				 +"\n3.Assess the current state value stream map in terms of creating flow by eliminating waste."
				 +"\n4.Draw a future state value stream map."
				 +"\n5.Work toward the future state condition");
		
		question4.setAnswerList(new ArrayList<Answer>());
		
		Question question5 = new Question ();
		question5.setQuestionId(5L);
		question5.setCommunity("agile");
		question5.setAnswerCount(0);
		question5.setCategory("visiontoroadmap");
		question5.setQuestionString("How do I define roadmap for my product/program ?");
		question5.setDetailedDescription("How do I define roadmap for my product/program ?");
		question5.setLastModifiedDate(new Date());
		question5.setRecommendedSolution("Prune the Product Tree:"
				+"\nPrune the Product Tree is a technique to shape the requirements as per business needs"

				+"\n\nHow-To:"
				+"\n1. Draw a large tree on whiteboard / chart paper"
				+"\n2. Hand out index cards / post it’s shaped like leaves to business"
				+"\n3. Thick limbs represent major areas of the system"
				+"\n4. Ask the stakeholders to create a well balanced tree");
		
		question5.setAnswerList(new ArrayList<Answer>());
		
		Question question6 = new Question ();
		question6.setQuestionId(6L);
		question6.setCommunity("agile");
		question6.setAnswerCount(0);
		question6.setCategory("visiontoroadmap");
		question6.setQuestionString("How do I make sure that the 3 constraints are properly balanced ?");
		question6.setDetailedDescription("How do I make sure that the 3 constraints are properly balanced ?");
		question6.setLastModifiedDate(new Date());
		question6.setRecommendedSolution("Trade-Off Matrix:"
				+"\nTrade-Off Matrix is a coaching tool to educate stakeholders on constraints – Scope, Schedule and Resources. It promotes transparency among the stakeholders"

				+"\n\nHow-To:"
				+"\nGet the stakeholders together to agree on \"Given fixed <<first constraint>>, we will choose a <<second constraint>> and adjust <<third constraint>> as necessary\"");
		question6.setAnswerList(new ArrayList<Answer>());
		
		Question question7 = new Question ();
		question7.setQuestionId(7L);
		question7.setCommunity("agile");
		question7.setAnswerCount(0);
		question7.setCategory("visiontoroadmap");
		question7.setQuestionString("How do I prioritize the business requirements ?");
		question7.setDetailedDescription("How do I prioritize the business requirements ?");
		question7.setLastModifiedDate(new Date());
		question7.setRecommendedSolution("Buy a feature:"
				+"\nBuy a feature improves the quality of this decision by asking your customers to help you make it.Participants see a list of proposed product features and a cost (expressed as development effort or street-level pricing) associated with each. Each participant “buys” a desirable feature; participants may also pool resources to buy features too expensive to be purchased with individual funds."

				+"\n\nw-To:"
				+"\n1. List down all the features in a product backlog"
				+"\n2. Add prices to each features directly or use \"shirt sizes\" to compute the prices.  Add associated benefits(if any) for each features"
				+"\n3. Invite all the participants and the game rules"
				+"\n4. Allocate virtual  limited budget to each participants which they can use to purchase the features "
				+"\n5. Allow the participants to bid on the features they would like to put their money "
				+"\n6. Mark the features as “Purchased” through collaborative negotiation which makes up to the top of the list"
				+"\n7. Analyze the result to identify:"
				+"\n - Who purchased which features?"
				+"\n - Which one was the highest bid?"
				+"\n - Who negotiated with whom?"
				+"\n8. Iterate this process till all the participants feel confident on the prioritized list of features");
		
		question7.setAnswerList(new ArrayList<Answer>());
		
		
		Question question8 = new Question ();
		question8.setQuestionId(8L);
		question8.setCommunity("agile");
		question8.setAnswerCount(0);
		question8.setCategory("visiontoroadmap");
		question8.setQuestionString("How do I compute Business value for features?");
		question8.setDetailedDescription("How do I compute Business value for features?");
		question8.setLastModifiedDate(new Date());
		question8.setRecommendedSolution("Kano Analysis:"
				+"\nKano model helps to uncover, classify, and integrate customer needs and attributes into software or services that you are developing. It is used during requirement analysis to identify, prioritize and categorize requirements"

				+"\n\nQuality Function Deployment:"
				+"\nQFD is a strcutured  method in which customer requirements are translated into appropriate technical requirements for each stage of application or product development. In simple words, QFD is the voice of the customer translated into voice of the developer");
		
		question8.setAnswerList(new ArrayList<Answer>());
		
		Question question9 = new Question ();
		question9.setQuestionId(9L);
		question9.setCommunity("agile");
		question9.setAnswerCount(0);
		question9.setCategory("visiontoroadmap");
		question9.setQuestionString("How do I handle the challenge of high story point backlog?");
		question9.setDetailedDescription("How do I handle the challenge of high story point backlog?");
		question9.setLastModifiedDate(new Date());
		question9.setRecommendedSolution("Affinity Diagram:"
				+ "Affinity Diagram is a tool that gathers large amount of textual data(ideas, opinions, issues) and organise them into groups based on their natural relationship. This method taps a team's creativity and intuition.");
		question9.setAnswerList(new ArrayList<Answer>());
		
		Question question10 = new Question ();
		question10.setQuestionId(10L);
		question10.setCommunity("agile");
		question10.setAnswerCount(0);
		question10.setCategory("roadmaptodeploy");
		question10.setQuestionString("");
		question10.setDetailedDescription("How do I handle the challenge of high story point backlog?");
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
	
	private void setCommonAttributes(Model model,String page) {
		model.addAttribute("base_path",basepath);
		model.addAttribute("communityName","Agile");
		model.addAttribute("community","agile");
		model.addAttribute("page",page);
	}
	
}
