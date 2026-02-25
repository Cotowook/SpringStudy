package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardService {
	private final BoardMapper boardMapper;

	public List<BoardDTO> getList() { // Mapper에서 얻은 DTO 목록을 반환
		return boardMapper.list();
	}

	public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword){ // 게시물 목록 조회 + 페이징 처리 : 현재 페이지의 번호와 화면에 필요한 데이터를 개수를 파라미터로 전달받아
		// Mapper의 list2()와 listCount() 호출
		page = page <= 0 ? 1 : page;
		size = (size < 10 || size > 100) ? 10 : size;
		int skip = (page - 1) * size;
		
		
		String[] types = (typeStr != null && !typeStr.isBlank() ) ? typeStr.split("") : null;
		
		List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);
		int total = boardMapper.listCountSearch(types, keyword);
		
		return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
	}

	public Long register(BoardDTO dto) { // 등록 기능을 작성하고, 추가된 게시물의 번호를 반환하도록 구성
		int insertCount = boardMapper.insert(dto);

		log.info("insert Cnt : " + insertCount);
		
		return dto.getBno();
	}

	public BoardDTO read(Long bno) {	// 게시물 조회 처리 : 파라미터는 게시물의 번호(bno)이고 리턴 타입은 BoardDTO를 사용
		BoardDTO dto = boardMapper.selectOne(bno);
		return dto;
	}
	
	public void remove(Long bno) {
		boardMapper.remove(bno);
	}
	
	public void modify(BoardDTO dto) {
		boardMapper.update(dto);
	}
	
	
}
