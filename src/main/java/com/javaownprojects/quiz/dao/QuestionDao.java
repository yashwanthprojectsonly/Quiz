package com.javaownprojects.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaownprojects.quiz.model.Question;

//5

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> findByCategory(String category);
	
	@Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() limit :numQ", nativeQuery = true)
	List<Question> findRandomQuestionsByCategoryService(String category, int numQ);
}
