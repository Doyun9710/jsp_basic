package com.exam.model1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardTO {
	private String seq;
	private String subject;
	private String writer;
	private String mail;
	private String password;
	private String content;
	private String hit;
	private String wip;
	private String wdate;
	private int wgap;
}
