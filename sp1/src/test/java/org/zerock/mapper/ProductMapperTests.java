package org.zerock.mapper;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ProductMapperTests {
	@Autowired
	private ProductMapper productMapper;
	
	// 테스트의 transactional의 기본 동작 
	// 1. 테스트 시작 -> 트랜잭션 시작 
	// 2. 테스트 종료 -> 무조건 롤백
	// 	=> 테스트는 DB를 더럽히지 않는 것이 기본 철학
	@Transactional
	@Commit	// 테스트 트랜잭션을 롤백하지 않고 commit 하기 위한 어노테이션
	@Test
	public void testInsert() {
		ProductDTO productDTO = ProductDTO.builder()
				.pname("product")
				.pdesc("product desc")
				.writer("user1")
				.price(4000)
				.build();
		
		// 상품 테이블에 등록
		productMapper.insert(productDTO);
		
		// 상품 이미지 테이블에 등록
		productDTO.addImage(UUID.randomUUID().toString(), "test1.jpg");
		productDTO.addImage(UUID.randomUUID().toString(), "test2.jpg");

		productMapper.insertImages(productDTO);
	}
	
	@Test
	public void testSelectOne() {
		Integer pno = 1;
		
		ProductDTO dto = productMapper.selectOne(pno);
		
		log.info(dto);
		dto.getImageList().forEach(log::info);
	}
	
	
	@Transactional
	@Commit
	@Test
	public void testUpdateOne() {
		ProductDTO productDTO = ProductDTO.builder()
				.pno(1)
				.pname("update product")
				.pdesc("update product desc")
				.price(6000)
				.build();
		
		productDTO.addImage(UUID.randomUUID().toString() , "test3.jpg");
		productDTO.addImage(UUID.randomUUID().toString() , "test4.jpg");
		productDTO.addImage(UUID.randomUUID().toString() , "test5.jpg");

		// 1. 기존 이미지 삭제 
		productMapper.deleteImages(productDTO.getPno());
		
		// 2. 상품 정보 수정
		productMapper.updateOne(productDTO);

		// 3. 상품 이미지 갱신 
		productMapper.insertImages(productDTO);
		
	} 
	
	@Transactional
	@Commit
	@Test
	public void testInsertDummies() {
		for(int i=0; i<45; i++) {
			ProductDTO productDTO = ProductDTO.builder()
					.pname("Dummy Product " + i)
					.pdesc("Dummy Product Desc" + i)
					.writer("user" + (i%10))
					.price(4000)
					.build();
			
			// 상품 테이블에 등록
			productMapper.insert(productDTO);
			
			// 상품 이미지 테이블에 등록
			productDTO.addImage(UUID.randomUUID().toString() , i + "_test_1.jpg");
			productDTO.addImage(UUID.randomUUID().toString() , i + "_test_2.jpg");
			
			productMapper.insertImages(productDTO);
		}
		
	}
	
	
	@Test
	public void testList() {
		productMapper.list(0, 10).forEach(log::info);
		
		log.info(productMapper.listCount());
	}
	
}
