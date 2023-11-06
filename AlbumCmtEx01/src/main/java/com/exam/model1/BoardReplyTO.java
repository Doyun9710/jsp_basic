package com.exam.model1;

import java.util.ArrayList;

public class BoardReplyTO {
	private String seq;
	private int grp;
	private int grps;
	private int grpl;
	
	private String cwriter;
	private String cpassword;
	private String ccontent;
	private String cdate;
	
	private ArrayList<BoardReplyTO> boardReplyLists;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getGrps() {
		return grps;
	}
	public void setGrps(int grps) {
		this.grps = grps;
	}
	public int getGrpl() {
		return grpl;
	}
	public void setGrpl(int grpl) {
		this.grpl = grpl;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	
	public ArrayList<BoardReplyTO> getBoardReplyLists() {
		return boardReplyLists;
	}
	public void setBoardReplyLists(ArrayList<BoardReplyTO> boardReplyLists) {
		this.boardReplyLists = boardReplyLists;
	}
	
}
