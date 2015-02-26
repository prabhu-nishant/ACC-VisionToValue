package com.acc.visiontovalue.site.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.visiontovalue.site.form.EditQuestionForm;
import com.acc.visiontovalue.site.form.SubmitQuestionForm;
import com.acc.visiontovalue.site.form.TextAreaForm;
import com.acc.visiontovalue.site.model.Comment;
import com.acc.visiontovalue.site.model.Question;


@Controller
@RequestMapping(value="/developer")
public class DeveloperPageController {

private static List<Question> questionList = new ArrayList<Question>();
	
	@Value("${acc.visiontovalue.site.basepath}")
	private String basepath;

	@PostConstruct
	public void init() {
		setQuestionList();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String loadDeveloperCommunityPage(HttpServletRequest request,Model model){
		return "redirect:/developer/questions" ;
	}
	
	@RequestMapping(value="/questions" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityQuestionsPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"questions");
		return "questions";
	}

	@RequestMapping(value="/questions/view" ,method=RequestMethod.GET)
	public String loadQuestionsTab(@RequestParam("tab") String tab,HttpServletRequest request,Model model){
		
		List<Question> questionList = new ArrayList<Question>();
		
		for(Question question:getQuestionList()){
			
			if(tab.equals(question.getCategory()) && StringUtils.isNotBlank(question.getRecommendedSolution())){
				
				questionList.add(question);
			} 
			
		}
		
		setCommonAttributes(model,"questions");
		model.addAttribute("questionList",questionList);	
		
		return "questions_view";
	}

	private String displayQuestionDetail(Model model, Question question) {
		if (!model.containsAttribute("form")) {

			TextAreaForm form = new TextAreaForm();
			model.addAttribute("form", form);
		}
		
		setCommonAttributes(model,"questions");
		model.addAttribute("question",question);
		return "questions_detail";
	}
	
	@RequestMapping(value="/questions/detail/{questionId}" ,method=RequestMethod.GET)
	public String loadQuestionsDetail(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		return displayQuestionDetail(model, question);
	}
	
	@RequestMapping(value = "/questions/detail/{questionId}", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute
	@Valid TextAreaForm form,@PathVariable("questionId") long questionId, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		Question question = getQuestionDetail(questionId);
		Comment comment = new Comment();
		comment.setCommentId(questionId);
		comment.setCommentString(form.getTextArea());
		comment.setDate(new Date());
		question.getCommentList().add(comment);
		
		return displayQuestionDetail(model, question);
	}
			
	@RequestMapping(value="/questions/detail/{questionId}/delete/{commentId}" ,method=RequestMethod.GET)
	public String deleteCommentDetail(@PathVariable("questionId") long questionId,@PathVariable("commentId") long commentId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		deleteComment(question,commentId);
		
		return displayQuestionDetail(model, question);
	}
	
	
	@RequestMapping(value="/library" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityLibraryPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"library");
		model.addAttribute("questionList",getQuestionList());
		
		return "library";
	}
	

	@RequestMapping(value="/library/detail/{questionId}" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityLibraryDetailPage(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		return displayLibraryDetail(model,question);
	}
	
	@RequestMapping(value = "/library/detail/{questionId}", method = RequestMethod.POST)
	public String submitLibraryForm(@ModelAttribute
	@Valid TextAreaForm form,@PathVariable("questionId") long questionId, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		Question question = getQuestionDetail(questionId);
		question.setRecommendedSolution(form.getTextArea());
		question.setStatus("Solution available");
		return displayLibraryDetail(model,question);
	}
		
	
	private String displayLibraryDetail(Model model, Question question) {
		
		setCommonAttributes(model,"library");
		model.addAttribute("question",question);	
		return "library_detail";
	}
	
	
	
	@RequestMapping(value="/library/detail/{questionId}/edit" ,method=RequestMethod.GET)
	public String editLibraryDetail(@ModelAttribute
			@Valid EditQuestionForm form,@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		if (!model.containsAttribute("form")) {

			Question question = getQuestionDetail(questionId);
			EditQuestionForm editQuestionform = new EditQuestionForm();
			editQuestionform.setQuestionString(question.getQuestionString());
			editQuestionform.setDetailedDescription(question.getDetailedDescription());
			editQuestionform.setRecommendedSolution(question.getRecommendedSolution());
			model.addAttribute("form", editQuestionform);
		}
		
		setCommonAttributes(model,"library");
		return "library_edit";
		
	}
	
	@RequestMapping(value="/library/detail/{questionId}/edit" ,method=RequestMethod.POST)
	public String submitEdittedLibraryDetail(@ModelAttribute
			@Valid EditQuestionForm form,@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		question.setQuestionString(form.getQuestionString());
		question.setDetailedDescription(form.getDetailedDescription());
		question.setRecommendedSolution(form.getRecommendedSolution());
		return "redirect:/developer/library/detail/"+questionId;
	}
	
	@RequestMapping(value="/library/detail/delete/{questionId}" ,method=RequestMethod.GET)
	public String deleteLibraryDetails(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		deleteQuestionDetail(questionId);
		setCommonAttributes(model,"library");
		model.addAttribute("questionList",getQuestionList());
		return "library";
	}
	
	@RequestMapping(value="/ask/question" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityAskQuestionPage(HttpServletRequest request,Model model){
		
		if (!model.containsAttribute("form")) {

			SubmitQuestionForm submitQuestionForm = new SubmitQuestionForm();
			model.addAttribute("form", submitQuestionForm);
		}
		
		setCommonAttributes(model,"ask_question");
		model.addAttribute("questionList",getQuestionList());
		return "ask_question";
	}
	
	@RequestMapping(value="/ask/question/detail/{questionId}" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityAskQuestionDetailPage(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		setCommonAttributes(model,"ask_question");
		model.addAttribute("question",question);
		return "ask_question_detail";
	}
	
	
	@RequestMapping(value="/ask/question/new" ,method=RequestMethod.GET)
	public String addNewQuestion(HttpServletRequest request,Model model){
		
		if (!model.containsAttribute("form")) {

			SubmitQuestionForm submitQuestionForm = new SubmitQuestionForm();
			model.addAttribute("form", submitQuestionForm);
		}
		
		setCommonAttributes(model,"ask_question");
		model.addAttribute("questionList",getQuestionList());
		return "ask_question";
	}
	
	
	@RequestMapping(value="/scenarios" ,method=RequestMethod.GET)
	public String loadDeveloperCommunityScenariosPage(HttpServletRequest request,Model model){
		
		setCommonAttributes(model,"scenarios");
		return "scenarios";
	}
	
	@RequestMapping(value="/scenarios/view" ,method=RequestMethod.GET)
	public String loadScenariosTab(@RequestParam("tab") String tab,HttpServletRequest request,Model model){
		
		List<Question> questionList = new ArrayList<Question>();
		
		for(Question question:getQuestionList()){
			
			if(tab.equals(question.getCategory()) && StringUtils.isNotBlank(question.getRecommendedSolution())){
				
				questionList.add(question);
			}
			
		}
		
		setCommonAttributes(model,"questions");
		model.addAttribute("questionList",questionList);	
		
		return "scenarios_view";
	}
	
	@RequestMapping(value="/scenarios/detail/{questionId}" ,method=RequestMethod.GET)
	public String loadScenariosDetail(@PathVariable("questionId") long questionId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		return displayScenarioDetail(model, question);
	}
	
	@RequestMapping(value = "/scenarios/detail/{questionId}", method = RequestMethod.POST)
	public String submitScenarioForm(@ModelAttribute
	@Valid TextAreaForm form,@PathVariable("questionId") long questionId, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		Question question = getQuestionDetail(questionId);
		Comment comment = new Comment();
		comment.setCommentId(questionId);
		comment.setCommentString(form.getTextArea());
		comment.setDate(new Date());
		question.getCommentList().add(comment);
		return displayScenarioDetail(model, question);
	}
	
	private String displayScenarioDetail(Model model, Question question) {
		if (!model.containsAttribute("form")) {

			TextAreaForm form = new TextAreaForm();
			model.addAttribute("form", form);
		}
		
		setCommonAttributes(model,"scenarios");
		model.addAttribute("question",question);
		return "scenarios_detail";
	}
	
	@RequestMapping(value="/scenarios/detail/{questionId}/delete/{commentId}" ,method=RequestMethod.GET)
	public String deleteScenarioAnswerDetail(@PathVariable("questionId") long questionId,@PathVariable("commentId") long commentId,HttpServletRequest request,Model model){
		
		Question question = getQuestionDetail(questionId);
		deleteComment(question,commentId);
		
		return displayScenarioDetail(model, question);
	}
	
	
	
	private void setCommonAttributes(Model model,String page) {
		model.addAttribute("base_path",basepath);
		model.addAttribute("communityName","Developer Coach");
		model.addAttribute("community","agile");
		model.addAttribute("page",page);
		
		if(page.equals("library")){
			model.addAttribute("view_list",getQuestionList());
		}
		else {
			model.addAttribute("view_list",getQuestionList());
		}
		
	}
	
	private void deleteComment(Question question,long commentId) {
		
		for(Comment answer:question.getCommentList()){
			
			if(commentId == answer.getCommentId()){
				question.getCommentList().remove(answer);
				break;
			}
		}
		
	}
	
	
	private void deleteQuestionDetail(long questionId) {
		
		for(Question question1:getQuestionList()){
			
			if(questionId == question1.getQuestionId()){
				questionList.remove(question1);
				break;
			}
			
		}
		
	}
	
	private Question getQuestionDetail(long questionId) {
		
		Question question = null;
		for(Question question1:getQuestionList()){
			
			if(questionId == question1.getQuestionId()){
				
				question = question1;
				break;
			}
			
		}
		return question;
	}
	
	private String convertDateToString(Date date){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 String dateString = sdf.format(date);
		 return dateString;
	}
	
	private Date convertStringToDate(String dateString){
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
		
			date = sdf.parse(dateString);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date;
	}
	
	private List<Question> getQuestionList() {
		
		return questionList;
		
	}
		
private void setQuestionList() {
		
		Question question1 = new Question ();
		question1.setQuestionId(1L);
		question1.setCommunity("agile");
		question1.setCommentCount(0);
		question1.setCategory("visiontoroadmap");
		question1.setQuestionString("How do I create vision for my product/program that is crisp yet compelling ?");
		question1.setDetailedDescription("What would be the best techniques ?");
		question1.setStatus("Solution available");
		question1.setLastModifiedDate(new Date());
		question1.setRecommendedSolution("Vision Box:"
								+ "\nTechnique to identify the most exiting features of a product."
								+ "\n\nHow-To:"
								+"\n- Provide stakeholders a box (cereal or shoe) wrapped with white paper"
								+"\n- Ask them to design features on the box"
								+"\n- Front Side – Name, branding, key benefits"
								+"\n- Back Side – Key features, operating instructions");
		
		question1.setCommentList(new ArrayList<Comment>());
		
		Question question2 = new Question ();
		question2.setQuestionId(2L);
		question2.setCommunity("agile");
		question2.setCommentCount(0);
		question2.setCategory("visiontoroadmap");
		question2.setQuestionString("What are the essential ingredients of vision statement that will bring out the real essence of product vision ?");
		question2.setDetailedDescription("What are the essential ingredients of vision statement that will bring out the real essence of product vision ?");
		question2.setStatus("Solution available");
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
		question2.setCommentList(new ArrayList<Comment>());
		
		Question question3 = new Question ();
		question3.setQuestionId(3L);
		question3.setCommunity("agile");
		question3.setCommentCount(0);
		question3.setCategory("visiontoroadmap");
		question3.setQuestionString("How do I make sure that the business needs are consolidated from different sources as organization is complex and there are too many stakeholders influencing the product/features being developed");
		question3.setDetailedDescription("How do I make sure that the business needs are consolidated from different sources as organization is complex and there are too many stakeholders influencing the product/features being developed");
		question3.setStatus("Solution available");
		question3.setLastModifiedDate(new Date());
		question3.setRecommendedSolution("Voice of Customer (VOC):"
				+"\nVoice of the customer (VOC) is a term used to describe the in-depth process of capturing a customer's expectations, preferences and aversions. It is a market research technique that produces a detailed set of customer wants and needs, organized into a hierarchical structure, and then prioritized in terms of relative importance and satisfaction with current alternatives."

				+"\n\nHow-To:"
				+"\nThere are many possible ways to gather the information – focus groups, individual interviews, contextual inquiry, ethnographic techniques, etc. But all involve a series of structured in-depth interviews, which focus on the customers' experiences with current products or alternatives within the category under consideration. Needs statements are then extracted, organized into a more usable hierarchy, and then prioritized by the customers");
		question3.setCommentList(new ArrayList<Comment>());
		
		Question question4 = new Question ();
		question4.setQuestionId(4L);
		question4.setCommunity("agile");
		question4.setCommentCount(0);
		question4.setCategory("visiontoroadmap");
		question4.setQuestionString("How do I ensure that the high business value requirements flow seamlessly from business stakeholders to IT team?");
		question4.setDetailedDescription("How do I ensure that the high business value requirements flow seamlessly from business stakeholders to IT team?");
		question4.setStatus("Pending with SME");
		question4.setLastModifiedDate(new Date());
		question4.setCommentList(new ArrayList<Comment>());
		
		Question question5 = new Question ();
		question5.setQuestionId(5L);
		question5.setCommunity("agile");
		question5.setCommentCount(0);
		question5.setCategory("visiontoroadmap");
		question5.setQuestionString("How do I define roadmap for my product/program ?");
		question5.setDetailedDescription("How do I define roadmap for my product/program ?");
		question5.setStatus("Pending with SME");
		question5.setLastModifiedDate(new Date());
		question5.setCommentList(new ArrayList<Comment>());
		
		Question question6 = new Question ();
		question6.setQuestionId(6L);
		question6.setCommunity("agile");
		question6.setCommentCount(0);
		question6.setCategory("visiontoroadmap");
		question6.setQuestionString("How do I make sure that the 3 constraints are properly balanced ?");
		question6.setDetailedDescription("How do I make sure that the 3 constraints are properly balanced ?");
		question6.setStatus("Pending with SME");
		question6.setLastModifiedDate(new Date());
		question6.setCommentList(new ArrayList<Comment>());
		
		Question question7 = new Question ();
		question7.setQuestionId(7L);
		question7.setCommunity("agile");
		question7.setCommentCount(0);
		question7.setCategory("visiontoroadmap");
		question7.setQuestionString("How do I prioritize the business requirements ?");
		question7.setDetailedDescription("How do I prioritize the business requirements ?");
		question7.setStatus("Solution available");
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
		
		question7.setCommentList(new ArrayList<Comment>());
		
		
		Question question8 = new Question ();
		question8.setQuestionId(8L);
		question8.setCommunity("agile");
		question8.setCommentCount(0);
		question8.setCategory("visiontoroadmap");
		question8.setQuestionString("How do I compute Business value for features?");
		question8.setDetailedDescription("How do I compute Business value for features?");
		question8.setStatus("Solution available");
		question8.setLastModifiedDate(new Date());
		question8.setRecommendedSolution("Kano Analysis:"
				+"\nKano model helps to uncover, classify, and integrate customer needs and attributes into software or services that you are developing. It is used during requirement analysis to identify, prioritize and categorize requirements"

				+"\n\nQuality Function Deployment:"
				+"\nQFD is a strcutured  method in which customer requirements are translated into appropriate technical requirements for each stage of application or product development. In simple words, QFD is the voice of the customer translated into voice of the developer");
		
		question8.setCommentList(new ArrayList<Comment>());
		
		Question question9 = new Question ();
		question9.setQuestionId(9L);
		question9.setCommunity("agile");
		question9.setCommentCount(0);
		question9.setCategory("visiontoroadmap");
		question9.setQuestionString("How do I handle the challenge of high story point backlog?");
		question9.setDetailedDescription("How do I handle the challenge of high story point backlog?");
		question9.setStatus("Solution available");
		question9.setLastModifiedDate(new Date());
		question9.setRecommendedSolution("Affinity Diagram:"
				+ "Affinity Diagram is a tool that gathers large amount of textual data(ideas, opinions, issues) and organise them into groups based on their natural relationship. This method taps a team's creativity and intuition.");
		question9.setCommentList(new ArrayList<Comment>());
		
		Question question10 = new Question ();
		question10.setQuestionId(10L);
		question10.setCommunity("agile");
		question10.setCommentCount(0);
		question10.setCategory("roadmaptodeploy");
		question10.setQuestionString("As a Business User, I want  to see the solutions faster and provide feedbacks so that the solutions can align closer to our Business Users Requirements and improve Speed to Market");
		question10.setDetailedDescription("As a Business User, I want  to see the solutions faster and provide feedbacks so that the solutions can align closer to our Business Users Requirements and improve Speed to Market");
		question10.setStatus("Solution available");
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
		
		question10.setCommentList(new ArrayList<Comment>());
		
		Question question11 = new Question ();
		question11.setQuestionId(11L);
		question11.setCommunity("agile");
		question11.setCommentCount(0);
		question11.setCategory("roadmaptodeploy");
		question11.setQuestionString("As a Developer, I want to make sure that I write optimized lines of code so that there is defect rate is low");
		question11.setDetailedDescription("As a Developer, I want to make sure that I write optimized lines of code so that there is defect rate is low");
		question11.setStatus("Solution available");
		question11.setLastModifiedDate(new Date());
		question11.setRecommendedSolution("Test Driven Development (TDD):"
				+"\nTest-driven development (TDD) is an evolutionary approach to development which combines test-first development where you write a test before you write just enough production code to fulfill that test and refactoring."

				+"\n\nHow-To:"
				+"\n1. The developer writes an (initially failing) automated test case that defines a desired improvement or new function."
				+"\n2. The developer produces the minimum amount of code to pass that test."
				+"\n3. Finally refactors the new code to acceptable standards");
		question11.setCommentList(new ArrayList<Comment>());
		
		Question question12 = new Question ();
		question12.setQuestionId(12L);
		question12.setCommunity("agile");
		question12.setCommentCount(0);
		question12.setCategory("roadmaptodeploy");
		question12.setQuestionString("How do I know what is the release goal and by when can we achieve it ?");
		question12.setDetailedDescription("How do I know what is the release goal and by when can we achieve it ?");
		question12.setStatus("Solution available");
		question12.setLastModifiedDate(new Date());
		
		question12.setRecommendedSolution("Release Planning:"
		+"\nRelease Planning is performed during Sprint 0 at the beginning of a release to identify the release timelines (typically 3-6 months), the goals of the release, the scope and the number of Iterations in that release"

		+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Release%20Planning%20Guidelines_E73369A2.html ");
		question12.setCommentList(new ArrayList<Comment>());
		
		Question question13 = new Question ();
		question13.setQuestionId(13L);
		question13.setCommunity("agile");
		question13.setCommentCount(0);
		question13.setCategory("roadmaptodeploy");
		question13.setQuestionString("How do I ensure that there is a continuous focus on productivity gain ?");
		question13.setDetailedDescription("How do I ensure that there is a continuous focus on productivity gain ?");
		question13.setStatus("Solution available");
		question13.setLastModifiedDate(new Date());
		question13.setRecommendedSolution("Kaizen (Continuous Improvement):"
				+"\nKaizen refers to philosophy or practices that focus upon continuous improvement of processes in manufacturing ,engineering, business management or any process"
				+"\n\nHow-To:"
				+"\nThere are few techniques that can be used to perform continuous improvement."
				+"\n1. PDCA Cycle - Plan, Do, Check and Act cycle"
				+"\n2. Fishbone Diagram - visually representing a series of root causes stemming from one problem");
		
		question13.setCommentList(new ArrayList<Comment>());
		
		
		Question question14 = new Question ();
		question14.setQuestionId(14L);
		question14.setCommunity("agile");
		question14.setCommentCount(0);
		question14.setCategory("roadmaptodeploy");
		question14.setQuestionString("How do I know the Sprint Goal and what activities to be performed in the sprint ?");
		question14.setDetailedDescription("How do I know the Sprint Goal and what activities to be performed in the sprint ?");
		question14.setStatus("Pending with SME");
		question14.setLastModifiedDate(new Date());
		question14.setCommentList(new ArrayList<Comment>());
		
		Question question15 = new Question ();
		question15.setQuestionId(15L);
		question15.setCommunity("agile");
		question15.setCommentCount(0);
		question15.setCategory("roadmaptodeploy");
		question15.setQuestionString("How do I perform daily planning and resolve day to day impediments ?");
		question15.setDetailedDescription("How do I perform daily planning and resolve day to day impediments ?");
		question15.setStatus("Solution available");
		question15.setLastModifiedDate(new Date());
		question15.setRecommendedSolution("Daily Standup:"
				+"\nThis is a daily meeting of the team where all the team members and the Scrum master are present and discuss the progress, plan the day and highlight the impediments. This meeting is time-boxed to 15 minutes. "

				+"\nHow-To:"
				+"\nAll the team members has to answer following 3 questions:  "

				+"\nWhat did you do yesterday?" 
				+"\nWhat will you do today? "
				+"\nWhat are the impediments in your work? ");
		question15.setCommentList(new ArrayList<Comment>());
		
		Question question16 = new Question ();
		question16.setQuestionId(16L);
		question16.setCommunity("agile");
		question16.setCommentCount(0);
		question16.setCategory("roadmaptodeploy");
		question16.setQuestionString("How do I ensure that the sprint goals are delivered and meet the expectations ?");
		question16.setDetailedDescription("How do I ensure that the sprint goals are delivered and meet the expectations ?");
		question16.setStatus("Pending with SME");
		question16.setLastModifiedDate(new Date());
		question16.setCommentList(new ArrayList<Comment>());
		
		Question question17 = new Question ();
		question17.setQuestionId(17L);
		question17.setCommunity("agile");
		question17.setCommentCount(0);
		question17.setCategory("roadmaptodeploy");
		question17.setQuestionString("As a team, we want to make sure that we retrospect at regular interval and fine tune the behaviour so that we can continuously improve");
		question17.setDetailedDescription("As a team, we want to make sure that we retrospect at regular interval and fine tune the behaviour so that we can continuously improve");
		question17.setStatus("Solution available");
		question17.setLastModifiedDate(new Date());
		question17.setRecommendedSolution("Sprint Retrospective:"
				+"\nSprint Retrospective meetings are conducted at the end of each sprint. During this meeting, the ScrumMaster and the team discuss the just completed sprint and discuss what can be done to improve future sprints."

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/tasks/Sprint%20Retrospective_BA0E5E30.html");
		question17.setCommentList(new ArrayList<Comment>());
		
		
		Question question18 = new Question ();
		question18.setQuestionId(18L);
		question18.setCommunity("agile");	
		question18.setCommentCount(0);
		question18.setCategory("roadmaptodeploy");
		question18.setQuestionString("How do I ensure that the goal of continuous delivery is met ?");
		question18.setDetailedDescription("How do I ensure that the goal of continuous delivery is met ?");
		question18.setStatus("Solution available");
		question18.setLastModifiedDate(new Date());
		question18.setRecommendedSolution("Configuration Management:"
			+"\nConfiguration Management is the backbone for continuous delivery. Having a robust configuration management would ensure a strong foundation for continuous integration, deploy"
			+"\n\nFor more details:\"https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Configuration%20Management%20Guidelines_A966194F.html \"");
		question18.setCommentList(new ArrayList<Comment>());
		
		Question question19 = new Question ();
		question19.setQuestionId(19L);
		question19.setCommunity("agile");
		question19.setCommentCount(0);
		question19.setCategory("roadmaptodeploy");
		question19.setQuestionString("How do I ensure that the team is able to integrate continuously automatically with less manual interventions ?");
		question19.setDetailedDescription("How do I ensure that the team is able to integrate continuously automatically with less manual interventions ?");
		question19.setStatus("Solution available");
		question19.setLastModifiedDate(new Date());
		question19.setRecommendedSolution("Continuous Integration:"
				+"\nContinuous Integration provides a kind of environment which takes care of the frequent Build, Integration and Testing needs of a project / program. It consists of a series of automated steps which can be executed either when new code is added into repository or at a scheduled time depending on the way it has been configured to run. These automated steps ensure the integration works as desired and at the same time reduce the time associated with the frequent integration. Continuous integration ensures that the code is compiled, unit tested and compliant with standards"

				+"\n\nFor more details, refer: https://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Continuous%20Integration%20Environment%20Guidelines_6CAE42E1.html");
		question19.setCommentList(new ArrayList<Comment>());

		Question question20 = new Question ();
		question20.setQuestionId(20L);
		question20.setCommunity("agile");
		question20.setCommentCount(0);
		question20.setCategory("roadmaptodeploy");
		question20.setQuestionString("How do I ensure the progress of the team in the sprint is seamless and transparent ?");
		question20.setDetailedDescription("How do I ensure the progress of the team in the sprint is seamless and transparent ?");
		question20.setStatus("Solution available");
		question20.setLastModifiedDate(new Date());
		question20.setRecommendedSolution("Kanban Wall:"
				+"\nOne of the core principles of Kanban for software development is “Make it visible”. A Kanban Wall is a visual display of all the tasks along with its progress pertaining to a sprint. It consists of a sequence of defined steps or sub processes or states that a user story moves through until it is complete. The states are defined based on the sequence of activities in which the user stories are developed."

				+"\n\nFor more details, refer:"
				+"\nhttps://methodology.accenture.com/dist_agile/#meth.dist_agile/guidances/guidelines/Agile%20Kanban%20Board%20Setup%20and%20Management%20Guidelines_D4026BC7.html ");
		question20.setCommentList(new ArrayList<Comment>());
		
		Question question21 = new Question ();
		question21.setQuestionId(21L);
		question21.setCommunity("agile");
		question21.setCommentCount(0);
		question21.setCategory("roadmaptodeploy");
		question21.setQuestionString("How do I ensure that deployment is fast and continuous ?");
		question21.setDetailedDescription("How do I ensure that deployment is fast and continuous ?");
		question21.setStatus("Solution available");
		question21.setLastModifiedDate(new Date());
		question21.setRecommendedSolution("Automated Deploy:"
				+"\nThis practice can be achieved by the using a Continuous Integration framework, which will perform the automated deployment at regular and pre-determined intervals or can be triggered by code check in by a developer ");
		question21.setCommentList(new ArrayList<Comment>());
		
		Question question22 = new Question ();
		question22.setQuestionId(22L);
		question22.setCommunity("agile");
		question22.setCommentCount(0);
		question22.setCategory("roadmaptodeploy");
		question22.setQuestionString("How do I ensure that the IT development is aligned to business requirements ?");
		question22.setDetailedDescription("How do I ensure that the IT development is aligned to business requirements ?");
		question22.setStatus("Solution available");
		question22.setLastModifiedDate(new Date());
		question22.setRecommendedSolution("Behaviour Driven Development:"
				+"\nBehavior-driven development combines the general techniques and principles of TDD with ideas from domain-driven design and object-oriented analysis and design to provide software developers and business analysts with shared tools and a shared process to collaborate on software development, with the aim of delivering \"software that matters\"."

				+"\n\nFor more details, refer: http://en.wikipedia.org/wiki/Behavior-driven_development ");
		question22.setCommentList(new ArrayList<Comment>());
		
		
		Question question23 = new Question ();
		question23.setQuestionId(23L);
		question23.setCommunity("agile");
		question23.setCommentCount(0);
		question23.setCategory("deploytovalue");
		question23.setQuestionString("How do I improve productivity of support incidents ?");
		question23.setDetailedDescription("How do I improve productivity of support incidents ?");
		question23.setStatus("Solution available");
		question23.setLastModifiedDate(new Date());
		question23.setRecommendedSolution("Value Stream Mapping(VSM):"
				+"\nVSM is used to analyze an existing process to identify the improvement areas. It is a method of creating \"One Page Picture\" of all the activities and tasks that occur in a process, from the time a customer provides the requirement, until the application, product, or service meeting the requirement is provided to the customer. The goal is to depict data and information flows across and throughput all the process steps that are required to provide the application, product or service to the customer. VSM documents both value and non-value adding (wastes) steps in a process");
		question23.setCommentList(new ArrayList<Comment>());
		
		Question question24 = new Question ();
		question24.setQuestionId(24L);
		question24.setCommunity("agile");
		question24.setCommentCount(0);
		question24.setCategory("deploytovalue");
		question24.setQuestionString("How do I reduce high resolution time for incidents ?");
		question24.setDetailedDescription("How do I reduce high resolution time for incidents ?");
		question24.setStatus("Solution available");
		question24.setLastModifiedDate(new Date());
		question24.setRecommendedSolution("SIPOC:"
				+"\nSIPOC is used to identify the key elements (Supplier, Inputs, Process, Outputs and Customers) of a process, which are to be considered to improve process performance. It gives a high-level picture of the process that depicts how the given process is servicing the customer");
		question24.setCommentList(new ArrayList<Comment>());
		
		Question question25 = new Question ();
		question25.setQuestionId(25L);
		question25.setCommunity("agile");
		question25.setCommentCount(0);
		question25.setCategory("deploytovalue");
		question25.setQuestionString("How do I ensure that the cycle time is reduced between the ticket in the queue till it is available in the production ?");
		question25.setDetailedDescription("How do I ensure that the cycle time is reduced between the ticket in the queue till it is available in the production ?");
		question25.setStatus("Pending with SME");
		question25.setLastModifiedDate(new Date());
		question25.setCommentList(new ArrayList<Comment>());
		
		Question question26 = new Question ();
		question26.setQuestionId(26L);
		question26.setCommunity("agile");
		question26.setCommentCount(0);
		question26.setCategory("deploytovalue");
		question26.setQuestionString("How do I ensure that the development and operations transition is seamless ?");
		question26.setDetailedDescription("How do I ensure that the development and operations transition is seamless ?");
		question26.setStatus("Solution available");
		question26.setLastModifiedDate(new Date());
		question26.setRecommendedSolution("Self-Service Provisioning:"
			+"\nWith the increasing complexity involved in software development, provisioning of environments for development teams has become complex and demanding task for IT operations. As cloud computing enables near infinite computing resources, organizations can now build cloud based self-service provisioning systems. These self systems enable development teams to rapidly clone and bring up environments as per needs, and avoiding to wait for IT operations to provision it. Such self-service solutions with accurate cloud-based provisioning, improves coordination between development and IT operations, in turn reducing development time and improving the quality of software delivered.");
		question26.setCommentList(new ArrayList<Comment>());
		
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
		questionList.add(question15);
		questionList.add(question16);
		questionList.add(question17);
		questionList.add(question18);
		questionList.add(question19);
		questionList.add(question20);
		questionList.add(question21);
		questionList.add(question22);
		questionList.add(question23);
		questionList.add(question24);
		questionList.add(question25);
		questionList.add(question26);
	}
	
}
