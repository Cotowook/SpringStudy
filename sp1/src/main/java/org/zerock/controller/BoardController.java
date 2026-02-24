package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	@GetMapping("/list")
	public void list() {
		log.info("---------------");
		log.info("board List");
		log.info("---------------");
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("---------------");
		log.info("board register");
		log.info("---------------");
	}
	
	@PostMapping("/register")
	public String regsiterPOST() {
		log.info("board register POST");
		
		return "redirect:/board/list";
	}
	
	// 게시물 조회는 GET 방식으로 게시물의 번호로 해당 게시물을 Model에 담아서 전달하는 방식으로 구성
	// 경로의 마지막 값을 게시물의 번호로 이용
	@GetMapping("/read/{bno}")
	public String read(@PathVariable("bno") Long bno) {
		log.info("---------------");
		log.info("board Read (bno) : " + bno);
		
		return "/board/read";
	}
	
	// GET 방식으로 수정하려고 하는 게시물을 확인하고, POST 방식으로 수정하거나 삭제를 처리 
	@GetMapping("/modify/{bno}")
	public String modifyGET(@PathVariable("bno") Long bno) {
		log.info("----------------------------------------");
		log.info("board modify get");
		
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPOST() {
		log.info("----------------------------------------");
		log.info("board modify post");
		
		return "redirect:/board/read/123";
	}

	
	@PostMapping("/remove")
	public String remove() {
		log.info("----------------------------------------");
		log.info("board remove");
		
		return "redirect:/board/list";
	}
}
