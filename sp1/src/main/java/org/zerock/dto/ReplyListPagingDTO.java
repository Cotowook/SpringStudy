package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ReplyListPagingDTO {
	private static final int BLOCK_SIZE = 10;
	
	private List<ReplyDTO> replyDTOList;	// 게시물 목록
	private int totalCount;	// 전체 데이터 개수
	private int page;	// 현재 페이지 번호
	private int size;
	
	private int start; // 블록의 시작 번호
	private int end; 
	private boolean prev;
	private boolean next;
	private List<Integer> pageNums;
	
	private String types;
	private String keyword;
	
	public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
		super();
		this.replyDTOList = replyDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		int tempEnd = (int) (Math.ceil(page / (BLOCK_SIZE * 1.0))) * 10; 
		this.start = tempEnd - (BLOCK_SIZE - 1); 
		this.prev = start != 1;

		int lastPage = (int)Math.ceil(totalCount/(double)size); // ex) 122 / 10.0 = 12.2  => 13
		this.end = Math.min(tempEnd, lastPage);
		this.next = end < lastPage; 
		
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}	
	
	
}
