package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(
			@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "types", required = false) String types,
			@RequestParam(name = "keyword", required = false) String keyword, 
			Model model) {
		log.info("----------------------------------------");
		log.info("board list");

		log.info("page : " + page); // 현재 페이지 번호
		log.info("size : " + size); // 페이지당 출력되는 데이터 개수 
		
		model.addAttribute("dto", boardService.getList(page, size, types, keyword));
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("---------------");
		log.info("board register");
		log.info("---------------");
	}
	
	@PostMapping("/register")
	public String regsiterPOST(BoardDTO dto, RedirectAttributes rttr) {
		log.info("board register POST");

		Long bno = boardService.register(dto);
		rttr.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	// 게시물 조회는 GET 방식으로 게시물의 번호로 해당 게시물을 Model에 담아서 전달하는 방식으로 구성
	// 경로의 마지막 값을 게시물의 번호로 이용
	@GetMapping("/read/{bno}")
	public String read(@PathVariable("bno") Long bno, Model model) {
		log.info("---------------");
		log.info("board Read (bno) : " + bno);
		
		BoardDTO boardDTO = boardService.read(bno);
		model.addAttribute(boardDTO); // dto 객체를 Model에 담아서 전달
		
		return "/board/read";
	}
	
	// GET 방식으로 수정하려고 하는 게시물을 확인하고, POST 방식으로 수정하거나 삭제를 처리 
	@GetMapping("/modify/{bno}")
	public String modifyGET(@PathVariable("bno") Long bno, Model model) {
		log.info("----------------------------------------");
		log.info("board modify get");
		
		BoardDTO boardDTO = boardService.read(bno);
		
		model.addAttribute(boardDTO);
		
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPOST(BoardDTO dto) {
		log.info("----------------------------------------");
		log.info("board modify post");
		
		boardService.modify(dto);
		
		return "redirect:/board/read/" + dto.getBno();
	}

	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("----------------------------------------");
		log.info("board remove");
		
		boardService.remove(bno);
		rttr.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
}
