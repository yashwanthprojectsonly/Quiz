package com.javaownprojects.quiz.service;

import java.util.ArrayList;

//4

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javaownprojects.quiz.dao.QuestionDao;
import com.javaownprojects.quiz.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>>  getAllQuestionsService() {
//		return questionDao.findAll();
		try {
			return new ResponseEntity<List<Question>>(questionDao.findAll(), HttpStatus.OK);
//			Before we were just returning only List of Question service data, but now since we want to get response code or status, we have to use 
//			ResponseEntity and in the parameter we have to pass two parameters, List<Question>>(questionService.getAllQuestionsService()and HttpStatus.OK)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getAllQuestionsByCategoryService(String category) {
		try {
			return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestionService(Question question) {
		 try {
			 questionDao.save(question);
			 return new ResponseEntity<>("Success", HttpStatus.CREATED); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

}
