package com.mateusulrich.codefood.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Problem {
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Object> objects;

	@Getter
	@Builder
	public static class Object {
		private String name;
		private String userMessage;
	}

}
