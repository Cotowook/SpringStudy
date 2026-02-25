package org.zerock.mapper;

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
		Long bno = 425L;
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(bno)
				.replyText("Reply Test Content")
				.replyer("Reply Test Replyer")
				.build();
		
		int insertCount = replyMapper.insert(replyDTO);
		
		log.info("-----------");
		log.info(insertCount);
		log.info("-----------");
	}

		
}
