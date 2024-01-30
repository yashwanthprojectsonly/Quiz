package com.javaownprojects.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaownprojects.quiz.model.QuestionWrapper;
import com.javaownprojects.quiz.model.ResponseReceived;
import com.javaownprojects.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizservice;
	
//	Only for admin to create quiz
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return quizservice.createQuiz(category, numQ, title);
	}
	
//	This is for user.
	@GetMapping("fetchQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> fetchQuizQuestions(@PathVariable Integer id) { //@PathVariable value will be passed in the {id}
		return quizservice.fetchQuizQuestionsService(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseReceived> responseReceived){
		return quizservice.calculateResult(id, responseReceived);
	}
	
}
