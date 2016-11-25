package br.edu.ifpb.ws.analyzerQuestionsRESTful.analyzers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.ws.analyzerQuestionsRESTful.entities.pojos.MSG;
import br.edu.ifpb.ws.analyzerQuestionsRESTful.enumerations.TSuggestions;
import br.edu.ifpb.ws.analyzerQuestionsRESTful.services.SuggestionService;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>	
 *
 */
@Service
public class QuestionAnalyzerMSG {

	private QuestionAnalyzerFinal qaf;
	private List<String> messages;
	private MSG msg;
	
	@Autowired
	private SuggestionService suggestionService;
	
	/**
	 * 
	 * @param service
	 */
	public QuestionAnalyzerMSG() {
		qaf = new QuestionAnalyzerFinal();
	}

	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgObjective(String description) {
		messages = new ArrayList<>();
		msg = new MSG();
		if (qaf.analyzerObjective(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.IS_OBJECTIVE).getMsg());

			if (qaf.analyzerShortDescriptionQuestion(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.IS_OBJECIVE_DC).getMsg());

			if (!qaf.isQuestionUnique(description))
				messages.add(suggestionService.findByTipo(TSuggestions.IS_OBJECTIVE_QUESTION_UNIQUE).getMsg());

			if (qaf.avoidingMuchCode(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.IS_OBJECTIVE_MUCH_CODE).getMsg());
		}

		msg.setSubHeaders(messages);
		return msg;
	}

	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgExample(String description) {
		msg = new MSG();
		if (qaf.analyzerShowExample(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.SHOW_EXAMPLE).getMsg());
		}
		return msg;
	}

	/**
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public MSG msgClarity(String title, String description) {
		messages = new ArrayList<>();
		msg = new MSG();
		if (qaf.analyzerClarity(title, description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.IS_CLARITY).getMsg());

			if (qaf.analyzerObjective(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.IS_OBJECTIVE).getMsg());

			if (qaf.analyzerCoherencyBodyAndTitle(title, description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.COERENCY_BODY_WITH_TITLE).getMsg());

			if (qaf.analyzerShowExample(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.SHOW_EXAMPLE).getMsg());

			if (!qaf.isQuestionUnique(description))
				messages.add(suggestionService.findByTipo(TSuggestions.UNIQUE_QUESTION).getMsg());
		}

		msg.setSubHeaders(messages);
		return msg;
	}

	/**
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public MSG msgUnderstandableDescription(String title, String description) {
		messages = new ArrayList<>();
		msg = new MSG();
		if (qaf.analyzerUnderstandableDescription(title, description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.UNDESTANDABLE_DESCRTION).getMsg());

			if (qaf.analyzerObjective(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.IS_OBJECTIVE).getMsg());

			if (qaf.analyzerClarity(title, description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.IS_CLARITY).getMsg());
		}

		msg.setSubHeaders(messages);
		return msg;
	}

	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgBeEducated(String description) {
		messages = new ArrayList<>();
		msg = new MSG();
		if (qaf.analyzerBeEducated(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.BE_EDUCADED).getMsg());

			if (qaf.analyzerUsingProperLanguage(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.USING_PROPER_LANGUAGE).getMsg());

			if (qaf.includingGreetings(description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.GREAT).getMsg());
		}

		msg.setSubHeaders(messages);
		return msg;
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public MSG msgCoherencyBodyAndTitle(String title, String description) {
		msg = new MSG();
		if (qaf.analyzerCoherencyBodyAndTitle(title, description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.COERENCY_BODY_WITH_TITLE).getMsg());
		}

		return msg;
	}
	
	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgUsingProperLanguage(String description) {
		msg = new MSG();
		if (qaf.analyzerUsingProperLanguage(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.USING_PROPER_LANGUAGE).getMsg());
		}

		return msg;
	}

	/**
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public MSG msgUnderstandableTitle(String title, String description) {
		messages = new ArrayList<>();
		msg = new MSG();
		if (qaf.analyzerUnderstandableTitle(title, description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.TITULO_BEM_DEFINIDO).getMsg());
			if (!qaf.isMediumSizeTitle(title))
				messages.add(suggestionService.findByTipo(TSuggestions.TITLE_MEDIO).getMsg());

			if (qaf.analyzerCoherencyBodyAndTitle(title, description) == 0)
				messages.add(suggestionService.findByTipo(TSuggestions.COERENCY_BODY_WITH_TITLE).getMsg());
		}

		msg.setSubHeaders(messages);
		return msg;
	}
	
	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgShortDescriptionQuestion(String description) {
		msg = new MSG();
		if (qaf.analyzerShortDescriptionQuestion(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.SHORT_DESCRIPTION).getMsg());
		}

		return msg;
	}

	/**
	 * 
	 * @param description
	 * @return
	 */
	public MSG msgDoNotCreateHomeworkQuestions(String description) {
		msg = new MSG();
		if (qaf.analyzerDoNotCreateHomeworkQuestions(description) == 0) {
			msg.setHeader(suggestionService.findByTipo(TSuggestions.HOME_WORK_QUESTION).getMsg());
		}

		return msg;
	}
}
