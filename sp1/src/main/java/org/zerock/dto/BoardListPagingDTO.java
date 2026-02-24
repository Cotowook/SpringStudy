package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;

// BoardService의 기존 list()는 단순하게 목록 데이터만 전달했기 때문에 List<BoardDTO>로 처리가 가능했지만, 
// 전체 데이터의 수가 같이 전달되기 때문에 두 종류의 데이터를 같이 담을 수 있는 BoardListPagingDTO라는 새로운 DTO를 생성해서 처리

@Data
@AllArgsConstructor
public class BoardListPagingDTO {
	private final int BLOCK_SIZE = 10;
	
	private List<BoardDTO> boardDTOList;	// 게시물 목록
	private int totalCount;	// 전체 데이터 개수
	private int page;	// 현재 페이지 번호
	private int size;
	
	// 페이지 번호의 처리 
	private int start; // 블록의 시작 번호
	private int end; 
	private boolean prev;
	private boolean next;
	private List<Integer> pageNums;
	
	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
		super();
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;

		// start 계산을 위한 tempEnd 페이지 
		// tempEnd : 현재 블록의 마지막 페이지(임시)
		int tempEnd = (int) (Math.ceil(page / BLOCK_SIZE * 1.0)) * BLOCK_SIZE; // 10.0, 10 은 패이지 블록을 의미함
		this.start = tempEnd - (BLOCK_SIZE - 1);
		this.prev = start != 1;

		// 정확한 end 페이지 번호 계산 
		// lastPage : 전체 페이지
		int lastPage = (int)Math.ceil(totalCount/(double)size); // ex) 122 / 10.0 = 12.2  => 13
		this.end = Math.min(tempEnd, lastPage);
		this.next = end < lastPage; 
		
		// 화면에 출력할 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}	
	
	
}
