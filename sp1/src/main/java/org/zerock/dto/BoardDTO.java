package org.zerock.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // 요청 파라미터 자동 수집 및 MyBatis에서 SELECT문의 결과를 처리할 때 필요(기본 생성자 + Setter방식)
public class BoardDTO {
	// 게시물 번호(bno)와 식별자(PK) 데이터는 null 표현을 하는 경우가 많으므로 Long 사용이 안전하고 좋은 습관(long을 사용하면
	// 0으로 초기화 -> 0번 게시물인지 null인지 분간 불가능)
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private boolean delFlag;

	public String getCreatedDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	// 빌더 패턴 연습 및 테스트용 
	private static void testBuilder() {
		// 기존 생성자 방식: 순서에 맞춰야 하고, 어떤 필드인지 한 눈에 파악이 힘듦  
		BoardDTO dto1 = new BoardDTO(1L, "제목", "내용", "작성자", null, null, false);
		
		// 빌더 방식: 순서와 상관없이 필드 이름으로 명시하여 확인 가능 
		BoardDTO dto2 = BoardDTO.builder()
							.title("제목")
							.writer("작성자")
							.content("내용")
							.build();
		// @Builder
		// 롬복에서 제공하는 어노테이션으로 빌더 패턴(Builder Pattern)을 자동으로 구현해주는 역할 
		// 장점: 생성자 파라미터 순서 기억 불필요, 필드 선택적 생성 가능
		// 사용 상황: 필드가 많거나 생성 과정이 복잡한 객체 생성 시 매우 유용
		}
}
