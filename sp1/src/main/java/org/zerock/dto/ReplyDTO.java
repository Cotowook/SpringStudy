package org.zerock.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
	private Long rno;
	private String replytext;
	private String replyer;
	
	// 자바 객체 <-> JSON 변환시 데이터의 포맷을 제어. 여기선 문자열 형태의 패턴을 사용해서 출력
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime replyDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	private boolean delFlag;
	
	private Long bno;
}
