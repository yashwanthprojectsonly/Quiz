package com.javaownprojects.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javaownprojects.quiz.dao.QuestionDao;
import com.javaownprojects.quiz.dao.QuizDao;
import com.javaownprojects.quiz.model.Question;
import com.javaownprojects.quiz.model.QuestionWrapper;
import com.javaownprojects.quiz.model.Quiz;
import com.javaownprojects.quiz.model.ResponseReceived;


@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionDao.findRandomQuestionsByCategoryService(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> fetchQuizQuestionsService(Integer id) {
		try {
//			First we have to fetch Quiz object from the DB and store it in quiz variable.
//			Optional<Quiz> class is used because, if we send quiz id request which is present in the DB, the data will be fetched. But if the quiz id is not 
//			there, it will throw NullPointerException. Optional will say that it might be an optional data. The data might come or not come.
			Optional<Quiz> quiz = quizDao.findById(id);
//			We should get the questions also.
			List<Question> questionsFromDB = quiz.get().getQuestions();
//			We have to convert the Question into QuestionWrapper because we are returning question wrapper.
			List<QuestionWrapper> questionsForUsers = new ArrayList<>();
			for(Question q : questionsFromDB) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
				questionsForUsers.add(qw);
			}
			return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<QuestionWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseReceived> responseReceived) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		
		for(ResponseReceived rr : responseReceived) {
			if(rr.getResponse().equals(questions.get(i).getRightAnswer())){
				right++;
			}
			i++;
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}
	
	
}
