package csci5408.catme.service;

import csci5408.catme.domain.Question;
import csci5408.catme.domain.QuestionType;

import java.util.List;

public interface IQuestionService {

	List<QuestionType> getQuestionTypes();

	List<Question> questionsByUser(Long userId, String sortType, String sortField);

	Long getQuestionTypeIdByName(String questionType);

	Question addQuestion(Question question);

	Boolean removeQuestion(Question question);

	Question getById(Long id);
}
