package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.TestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TestService {
	private final TestMapper testMapper;
	
	// 트랜잭션 설정 전/후 테스트
	@Transactional
	public void insertAll(String str) {
		int resultA = testMapper.insertA(str);
		log.info("insert A : " + resultA);
		
		int resultB = testMapper.insertB(str);
		log.info("insert B : " + resultB);
	}
	
	
	
}
