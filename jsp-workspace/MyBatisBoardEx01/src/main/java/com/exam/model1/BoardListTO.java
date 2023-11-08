package com.exam.model1;

import java.util.ArrayList;

import com.exam.model1.BoardTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListTO {
	private int cpage;
	private int recordPerPage;
	private int blockPerPage;
	private int totalPage;
	private int totalRecord;
	private int startBlock;
	private int endBlock;
	
	private String searchKey;
	private String searchWord;
	
	private ArrayList<BoardTO> boardLists;

	public BoardListTO() {
		this.cpage = 1;
		this.recordPerPage = 10;
		this.blockPerPage = 5;
		this.totalPage = 1;
		this.totalRecord = 0;
	}
}
