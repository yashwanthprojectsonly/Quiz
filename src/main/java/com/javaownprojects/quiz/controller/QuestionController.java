package com.javaownprojects.quiz.controller;

//2

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaownprojects.quiz.model.Question;
import com.javaownprojects.quiz.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestionsService();
//		Since we are returning ResponseEntity and in the parameter with List<Question>>(questionService.getAllQuestionsService()and HttpStatus.OK),
//		there is no need of returning ResponseEntity with parameter again, just return statement as ResponseEntity is enough.
	}
	
	@GetMapping("category/{category}") //category written inside the {} to assign whatever value @PathVariable gives.
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) { //Adding @PathVariable
//	inside parameter will assign that parameter value to the {category} in @GetMapping("category/{category}")
		return questionService.getAllQuestionsByCategoryService(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestionService(question);
	}
}