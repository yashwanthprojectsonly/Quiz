package com.javaownprojects.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//This QuestionWrapper class is used instead of Question class for the "USER" because when "USER" sends request to fetch Quiz details(questions),
//he/she should not be able to access rightAnswer.
@Data
@RequiredArgsConstructor
public class QuestionWrapper {
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
//	Lombok was not generating Constructors after using @Data annotation, so it had to be done manually
	public QuestionWrapper(Integer id, String questionTitle, String option1, String option2, String option3,
			String option4) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
	
}
