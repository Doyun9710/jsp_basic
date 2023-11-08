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
	private String filename;
	private long filesize;
	private String hit;
	private String wip;
	private String wdate;
	private int wgap;
	

	// 다음글 / 이전글
	private String nextseq;
	private String nextsub;
	private String beseq;
	private String besub;
	
	
	public BoardTO() {
		this.nextseq = "";
		this.nextsub = "다음글이 없습니다.";
		this.beseq = "";
		this.besub = "이전글이 없습니다.";
	}
}
