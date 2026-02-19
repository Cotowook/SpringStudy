package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller // 해당 클래스의 객체가 스프링에서 Bean으로 관리되는 대상임을 지정
@RequiredArgsConstructor
@ToString
@Log4j2
@RequestMapping("/sample") // '/sample'로 시작하는 요청을 HellowController가 처리한다,
public class HelloController {
	private final HelloService hellowService;
	
	/*
	 * @Autowired public HelloController(HelloService service) { this.hellowService
	 * = service; }
	 */

	//@RequestMapping(value="/exa1" , method=RequestMethod.GET) // 옛날 방식
	@GetMapping("/ex1") // PutMapping, DeleteMapping 등을 지원
	public void ex1() {
		log.info("/sample/ex1");
	}
	// 메소드가 void이면 스프링이 요청 URL 기반으로 뷰 이름을 추론. 즉 사용된 요청 경로 = 뷰 이름
	
}
