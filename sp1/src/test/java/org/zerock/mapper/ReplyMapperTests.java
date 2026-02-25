package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {
	@Autowired
	private ReplyMapper replyMapper;

	@Test
	public void testInsert() {
		Long bno = 424L;
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(bno)
				.replytext("Reply Test Content4")
				.replyer("Reply Test Replyer4")
				.build();
		
		int insertCount = replyMapper.insert(replyDTO);
		
		log.info("-----------");
		log.info(insertCount);
		log.info("-----------");
	}

	@Test 
	public void testInserts() {
		Long[] bnos = {421L, 422L, 423L};
		
		for (Long bno : bnos) {
			for (int i = 0; i < 10; i++) {
				ReplyDTO replyDTO = ReplyDTO.builder()
						.bno(bno)
						.replyer("replyer1")
						.replytext("sample Reply")
						.build();
				
				replyMapper.insert(replyDTO);
			}
		}
		
	}
	
	@Test
	public void testRead() {
		Long bno = 424L;
		
		ReplyDTO replyDTO = replyMapper.read(bno);
		
		log.info("-----------");
		log.info(replyDTO);
		log.info("-----------");
	}
	
	@Test
	public void testListOfBoard() {
		Long bno = 422L;
		
		List<ReplyDTO> list = replyMapper.listOfBoard(bno, 0, 10);
		
		list.forEach(log::info);
	}
	
	
	@Test
	public void testUpdate() {
		Long rno = 3L;
		String str = "hello world!";
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.rno(rno)
				.replytext(str)
				.build();
		
		int modifyCount = replyMapper.update(replyDTO);
		
		log.info("-----------");
		log.info("update Cnt : " + modifyCount);
		log.info("-----------");
	}
	
	@Test
	public void testDelete() {
		Long rno = 2L;
		
		int removeCount = replyMapper.delete(rno);
		
		log.info("-----------");
		log.info("remove Cnt : " + removeCount);
		log.info("-----------");
	}	
}
