package com.javaownprojects.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseReceived {
	private Integer id;
	private String response;
}
