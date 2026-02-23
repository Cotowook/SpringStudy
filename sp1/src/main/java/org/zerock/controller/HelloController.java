package org.zerock.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.SampleDTO;
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
		
		hellowService.hello1();
	}
	
	@GetMapping("/ex2")
	public String ex2() {
		log.info("/sample/ex2");
		
		hellowService.hello2("Wook");
		
		return "sample/success";
	}
	
	@GetMapping("/ex3")
	public String ex3() {
		log.info("/sample/ex3");
		return "redirect:/sample/ex3re";
		// 브라우저에게 /sample/ex3re로 이동하라는 메세지를 전송
	}
	
	@GetMapping("/ex4")
	public void ex4(@RequestParam(name = "n1", defaultValue = "1") int num,
				@RequestParam(name = "name", required = false) String name)  { // 필수 여부 지정 
		log.info("/sample/4");
		log.info("num: " + num);
		log.info("name: " + name);
	}
	
	@GetMapping("/ex5")
	public void ex5(SampleDTO dto) {
		log.info("/sample/ex5");
		log.info(dto);
	}
	
	@GetMapping("/ex6")
	public void ex6(Model model) {
		model.addAttribute("name", "Jone Doe");
		model.addAttribute("age", 20);
		// 참고: 뷰 렌더링 직전에 request attribute로 복사됨
	}
	
	@GetMapping("/ex7")
	public String ex7(RedirectAttributes rttr) {
		// 리다이렉트 시 새로운 요청으로 데이터를 전달하는 2가지 방법 
		rttr.addAttribute("name", "wook");
		rttr.addFlashAttribute("age", 20); // 1회성 데이터 전달용
		return "redirect:/sample/ex8";  // 내부적으로 세션에 임시 저장 후, 다음 요청에서 Model에 자동 주입되고 즉시 제거됨
	}

	@GetMapping("/ex8")
	public void ex8() {
		log.info("/sample/ex8");
	}
	
	
	// 메소드가 void이면 스프링이 요청 URL 기반으로 뷰 이름을 추론. 즉 사용된 요청 경로 = 뷰 이름
}
