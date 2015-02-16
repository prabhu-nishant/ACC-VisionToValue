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
		question10.setQuestionString("As a Business User, I want  to see the solutions faster and provide feedbacks so that the solutions can align closer to our Business Users Requirements and improve Speed to Market");
		question10.setDetailedDescription("As a Business User, I want  to see the solutions faster and provide feedbacks so that the solutions can align closer to our Business Users Requirements and improve Speed to Market");
		question10.setLastModifiedDate(new Date());
		question10.setRecommendedSolution("Accenture Prototype Accelerator Factory:"
				+"\nIt is a prototyping service that is used to create the prototype for new applications or while modeling new business processes"

				+"\n\nThere are 4 types of prototypes:"
				+"\n1. Off the shelf"
				+"\n2. New Web"
				+"\n3. Mobile"
				+"\n4. Web + Mobile"

				+"\n\nHow-To:"
				+"\nThis process works as follows:"
				+"\n- Quickly analyze the business requirements and design "
				+"\n- Develop visual specifications to depict system flow and interaction"
				+"\n- Validate with business users and gather feedback."
				+"\n- Meet prototype objectives without ambiguity, use visuals instead of words, "
				+"\n- Ensure that everyone including business shares a common understanding.");
		
		question10.setAnswerList(new ArrayList<Answer>());
		
		Question question11 = new Question ();
		question11.setQuestionId(11L);
		question11.setCommunity("agile");
		question11.setAnswerCount(0);
		question11.setCategory("roadmaptodeploy");
		question11.setQuestionString("As a Developer, I want to make sure that I write optimized lines of code so that there is defect rate is low");
		question11.setDetailedDescription("As a Developer, I want to make sure that I write optimized lines of code so that there is defect rate is low");
		question11.setLastModifiedDate(new Date());
		question11.setRecommendedSolution("Test Driven Development (TDD):"
				+"\nTest-driven development (TDD) is an evolutionary approach to development which combines test-first development where you write a test before you write just enough production code to fulfill that test and refactoring."

				+"\n\nHow-To:"
				+"\n1. The developer writes an (initially failing) automated test case that defines a desired improvement or new function."
				+"\n2. The developer produces the minimum amount of code to pass that test."
				+"\n3. Finally refactors the new code to acceptable standards");
		question11.setAnswerList(new ArrayList<Answer>());
		
		Question question12 = new Question ();
		question12.setQuestionId(12L);
		question12.setCommunity("agile");
		question12.setAnswerCount(0);
		question12.setCategory("roadmaptodeploy");
		question12.setQuestionString("How do I know what is the release goal and by when can we achieve it ?");
		question12.setDetailedDescription("How do I know what is the release goal and by when can we achieve it ?");
		question12.setLastModifiedDate(new Date());
		
		question12.setRecommendedSolution("Release Planning:"
		+"\nRelease Planning is performed during Sprint 0 at the beginning of a release to identify the release timelines (typically 3-6 months), the goals of the release, the scope and the number of Iterations in that release"

		+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Release%20Planning%20Guidelines_E73369A2.html ");
		question12.setAnswerList(new ArrayList<Answer>());
		
		Question question13 = new Question ();
		question13.setQuestionId(13L);
		question13.setCommunity("agile");
		question13.setAnswerCount(0);
		question13.setCategory("roadmaptodeploy");
		question13.setQuestionString("How do I ensure that there is a continuous focus on productivity gain ?");
		question13.setDetailedDescription("How do I ensure that there is a continuous focus on productivity gain ?");
		question13.setLastModifiedDate(new Date());
		question13.setRecommendedSolution("Kaizen (Continuous Improvement):"
				+"\nKaizen refers to philosophy or practices that focus upon continuous improvement of processes in manufacturing ,engineering, business management or any process"
				+"\n\nHow-To:"
				+"\nThere are few techniques that can be used to perform continuous improvement."
				+"\n1. PDCA Cycle - Plan, Do, Check and Act cycle"
				+"\n2. Fishbone Diagram - visually representing a series of root causes stemming from one problem");
		
		question13.setAnswerList(new ArrayList<Answer>());
		
		
		Question question14 = new Question ();
		question14.setQuestionId(14L);
		question14.setCommunity("agile");
		question14.setAnswerCount(0);
		question14.setCategory("roadmaptodeploy");
		question14.setQuestionString("How do I know the Sprint Goal and what activities to be performed in the sprint ?");
		question14.setDetailedDescription("How do I know the Sprint Goal and what activities to be performed in the sprint ?");
		question14.setLastModifiedDate(new Date());
		question14.setRecommendedSolution("Sprint Planning:"
				+"\nThis is a planning meeting that is conducted at the beginning of a Sprint to identify the goal of the Sprint and create the Sprint Backlog. The Product Owner, the Scrum Master, the team, and the manager attend this meeting. The stories selected for the Sprint are broken into tasks, and the tasks are estimated in hours"

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/tasks/Sprint%20Planning_D7E1FA41.html ");
		question14.setAnswerList(new ArrayList<Answer>());
		
		Question question15 = new Question ();
		question15.setQuestionId(15L);
		question15.setCommunity("agile");
		question15.setAnswerCount(0);
		question15.setCategory("roadmaptodeploy");
		question15.setQuestionString("How do I perform daily planning and resolve day to day impediments ?");
		question15.setDetailedDescription("How do I perform daily planning and resolve day to day impediments ?");
		question15.setLastModifiedDate(new Date());
		question15.setRecommendedSolution("Daily Standup:"
				+"\nThis is a daily meeting of the team where all the team members and the Scrum master are present and discuss the progress, plan the day and highlight the impediments. This meeting is time-boxed to 15 minutes. "

				+"\nHow-To:"
				+"\nAll the team members has to answer following 3 questions:  "

				+"\nWhat did you do yesterday?" 
				+"\nWhat will you do today? "
				+"\nWhat are the impediments in your work? ");
		question15.setAnswerList(new ArrayList<Answer>());
		
		Question question16 = new Question ();
		question16.setQuestionId(16L);
		question16.setCommunity("agile");
		question16.setAnswerCount(0);
		question16.setCategory("roadmaptodeploy");
		question16.setQuestionString("How do I ensure that the sprint goals are delivered and meet the expectations ?");
		question16.setDetailedDescription("How do I ensure that the sprint goals are delivered and meet the expectations ?");
		question16.setLastModifiedDate(new Date());
		question16.setRecommendedSolution("Sprint Review:"
				+"\nThis is a review meeting with the Product Owner conducted at the end of the sprint. The Team demo the user stories committed for the sprint and the Product Owner provides feedback. Finally the user stories are accepted or rejected by the Product Owner."

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/tasks/Sprint%20Review_F7669F0B.html ");
		question16.setAnswerList(new ArrayList<Answer>());
		
		Question question17 = new Question ();
		question17.setQuestionId(17L);
		question17.setCommunity("agile");
		question17.setAnswerCount(0);
		question17.setCategory("roadmaptodeploy");
		question17.setQuestionString("As a team, we want to make sure that we retrospect at regular interval and fine tune the behaviour so that we can continuously improve");
		question17.setDetailedDescription("As a team, we want to make sure that we retrospect at regular interval and fine tune the behaviour so that we can continuously improve");
		question17.setLastModifiedDate(new Date());
		question17.setRecommendedSolution("Sprint Retrospective:"
				+"\nSprint Retrospective meetings are conducted at the end of each sprint. During this meeting, the ScrumMaster and the team discuss the just completed sprint and discuss what can be done to improve future sprints."

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/tasks/Sprint%20Retrospective_BA0E5E30.html");
		question17.setAnswerList(new ArrayList<Answer>());
		
		
		Question question18 = new Question ();
		question18.setQuestionId(18L);
		question18.setCommunity("agile");	
		question18.setAnswerCount(0);
		question18.setCategory("roadmaptodeploy");
		question18.setQuestionString("How do I ensure that the goal of continuous delivery is met ?");
		question18.setDetailedDescription("How do I ensure that the goal of continuous delivery is met ?");
		question18.setLastModifiedDate(new Date());
		question18.setRecommendedSolution("Configuration Management:"
			+"\nConfiguration Management is the backbone for continuous delivery. Having a robust configuration management would ensure a strong foundation for continuous integration, deploy"
			+"\n\nFor more details:\"https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Configuration%20Management%20Guidelines_A966194F.html \"");
		question18.setAnswerList(new ArrayList<Answer>());
		
		Question question19 = new Question ();
		question19.setQuestionId(19L);
		question19.setCommunity("agile");
		question19.setAnswerCount(0);
		question19.setCategory("roadmaptodeploy");
		question19.setQuestionString("How do I ensure that the team is able to integrate continuously automatically with less manual interventions ?");
		question19.setDetailedDescription("How do I ensure that the team is able to integrate continuously automatically with less manual interventions ?");
		question19.setLastModifiedDate(new Date());
		question19.setRecommendedSolution("Continuous Integration:"
				+"\nContinuous Integration provides a kind of environment which takes care of the frequent Build, Integration and Testing needs of a project / program. It consists of a series of automated steps which can be executed either when new code is added into repository or at a scheduled time depending on the way it has been configured to run. These automated steps ensure the integration works as desired and at the same time reduce the time associated with the frequent integration. Continuous integration ensures that the code is compiled, unit tested and compliant with standards"

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Continuous%20Integration%20Environment%20Guidelines_6CAE42E1.html");
		question19.setAnswerList(new ArrayList<Answer>());

		Question question20 = new Question ();
		question20.setQuestionId(20L);
		question20.setCommunity("agile");
		question20.setAnswerCount(0);
		question20.setCategory("roadmaptodeploy");
		question20.setQuestionString("How do I ensure the progress of the team in the sprint is seamless and transparent ?");
		question20.setDetailedDescription("How do I ensure the progress of the team in the sprint is seamless and transparent ?");
		question20.setLastModifiedDate(new Date());
		question20.setRecommendedSolution("Kanban Wall:"
				+"\nOne of the core principles of Kanban for software development is “Make it visible”. A Kanban Wall is a visual display of all the tasks along with its progress pertaining to a sprint. It consists of a sequence of defined steps or sub processes or states that a user story moves through until it is complete. The states are defined based on the sequence of activities in which the user stories are developed."

				+"\n\nFor more details, refer:"
				+"\nhttps://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Kanban%20Board%20Setup%20and%20Management%20Guidelines_D4026BC7.html ");
		question20.setAnswerList(new ArrayList<Answer>());
		
		Question question21 = new Question ();
		question21.setQuestionId(21L);
		question21.setCommunity("agile");
		question21.setAnswerCount(0);
		question21.setCategory("roadmaptodeploy");
		question21.setQuestionString("How do I ensure that deployment is fast and continuous ?");
		question21.setDetailedDescription("How do I ensure that deployment is fast and continuous ?");
		question21.setLastModifiedDate(new Date());
		question21.setRecommendedSolution("Automated Deploy:"
				+"\nThis practice can be achieved by the using a Continuous Integration framework, which will perform the automated deployment at regular and pre-determined intervals or can be triggered by code check in by a developer ");
		question21.setAnswerList(new ArrayList<Answer>());
		
		Question question22 = new Question ();
		question22.setQuestionId(22L);
		question22.setCommunity("agile");
		question22.setAnswerCount(0);
		question22.setCategory("roadmaptodeploy");
		question22.setQuestionString("How do I ensure that the IT development is aligned to business requirements ?");
		question22.setDetailedDescription("How do I ensure that the IT development is aligned to business requirements ?");
		question22.setLastModifiedDate(new Date());
		question22.setRecommendedSolution("Behaviour Driven Development:"
				+"\nBehavior-driven development combines the general techniques and principles of TDD with ideas from domain-driven design and object-oriented analysis and design to provide software developers and business analysts with shared tools and a shared process to collaborate on software development, with the aim of delivering \"software that matters\"."

				+"\n\nFor more details, refer: http://en.wikipedia.org/wiki/Behavior-driven_development ");
		question22.setAnswerList(new ArrayList<Answer>());
		
		
		Question question23 = new Question ();
		question23.setQuestionId(23L);
		question23.setCommunity("agile");
		question23.setAnswerCount(0);
		question23.setCategory("deploytovalue");
		question23.setQuestionString("");
		question23.setDetailedDescription("");
		question23.setLastModifiedDate(new Date());
		question23.setRecommendedSolution("");
		question23.setAnswerList(new ArrayList<Answer>());
		
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
