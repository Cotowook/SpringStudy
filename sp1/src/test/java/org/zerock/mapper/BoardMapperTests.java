package org.zerock.mapper;

import java.util.List;
import java.util.logging.LoggingMXBean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testInsert() {
		BoardDTO boardDTO = BoardDTO.builder()
				.title("제목2").content("내용2").writer("작성자2")
				.build();
		
		int insertCount = boardMapper.insert(boardDTO);
		
		log.info("-----------");
		log.info(insertCount);
		log.info("-----------");
	}

	@Test
	public void testInsert2() {
		BoardDTO boardDTO = BoardDTO.builder()
				.title("제목3").content("내용3").writer("작성자3")
				.build();
		
		int insertCount = boardMapper.insert(boardDTO);
		
		log.info("-----------");
		log.info("insertCnt : " + insertCount);
		log.info("===========");
		log.info("BNO : " + boardDTO.getBno());
	}
	
	@Test
	public void testSelectOne() {
		Long bno = 2L;
		BoardDTO board = boardMapper.selectOne(bno);
		
		log.info("-----------");
		log.info("board : " + board);
		log.info("-----------");
	}
	
	@Test
	public void testRemove() {
		Long bno = 3L;
		int deleteResult = boardMapper.remove(bno);
		
		log.info("-----------");
		log.info("deleteCnt : " + deleteResult);
		log.info("-----------");
	}
	
	@Test
	public void testUpdate() {
		Long bno = 1L;
		BoardDTO boardDTO = BoardDTO.builder().
				title("Update Title").content("Update Content").delFlag(false).bno(bno)
				.build();
		int updateCnt = boardMapper.update(boardDTO);
		
		log.info("-----------");
		log.info("updateCnt : " + updateCnt);
		log.info("-----------");
	}
	
	@Test
	public void testList() {
		List<BoardDTO> dtoList = boardMapper.list();
		
		dtoList.stream().forEach(board->log.info(board));
		// 메소드 참조 사용시 : dtoList.stream().forEach(log::info);
	}
}
