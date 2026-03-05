package org.zerock.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, // 403 에러를 만날을 때 처리할 내용을 작성하면 된다.
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("------- AccessDeniedHandler --------");
		
		response.sendRedirect(request.getContextPath() + "/sample/access-denied");
	}
	
}
