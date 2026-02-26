package org.zerock.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zerock.service.exception.ReplyException;

import lombok.extern.log4j.Log4j2;

// 모든 @RestController에서 발생한 예외를 전역적으로 처리하여 HTTP 상태코드와 JSON 응답으로 변환하기 위해 사용
//@RestControllerAdvice(basePackages = "org.zerock.reply") // 해당 패키지의 컨트롤러에만 적용
//@RestControllerAdvice(assignableTypes = ReplyController.class) // 특정 컨트롤러에만 적용
// 또는 특정 컨트롤러 클래스 내부에 @ExceotionHandler 작성
@RestControllerAdvice
@Log4j2
public class ReplyControllerAdvice {
	// 특정 타입의 예외가 발생할 때 동작하도록 설정
	@ExceptionHandler(ReplyException.class)
	public ResponseEntity<String> handleReplyError(ReplyException ex){
		log.error(ex.getMessage());

		// ResponseEntity 타입으로 404, 500 같은 HTTP 응답 상태 코드와 함께 에러 메세지를 전송
		return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
	}
}
