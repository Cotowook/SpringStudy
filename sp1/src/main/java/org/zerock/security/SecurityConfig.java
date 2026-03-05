package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration	// 클래스를 스프링 설정 클래스로 등록하고, 내부의 Bean 메소드들이 반환하는 객체를 스프링 Bean으로 스프링 컨테이너에 등록
@Log4j2
@EnableWebSecurity	// 스프링 시큐리티 웹 보안 활성화.(클래스에서 정의한 보안 설정을 적용하도록 만드는 어노테이션) 
// root context 설정으로 모든 HTTP 요청은 시크리티 검사한다.
// securityFilterChain을 자동으로 구성
// 시큐리티 관련 기본 설정을 등록

// 메소드 단위에서 @PreAuthorize / @PostAuthorzie 어노테이션 활성화 하거나
// @EnableMethodSecurity(prePostEnabled = true) // 예제에서는 XML 설정을 사용하는 방식을 선택할 수 있다.
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("----------------- security config ---------------");
		// 이 안에서 필터 체인을 직접 조립해서 리턴하도록 작성
		
		http.formLogin(config -> { // 아이디/비밀번호 기반의 HTML 폼을 통한 로그인 긴으 활성화. 스프링 시큐리티의 기본 로그인 처리를 구성
			
		});
		
		http.csrf(csrf -> csrf.disable());
		
		http.exceptionHandling(handler -> {
			handler.accessDeniedHandler(new Custom403Handler()); // 403 응답 제어
			
			// handler.accessDeniedPage("페이지 이름") 특정 페이지로 이동시킬 경우 
		});
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 인자값으로 cost factor 넣을 수 있음. 
											// 해시를 느리게 설정해서 현실적인 시간 안에 비밀번호 유추가 불가능하도록 함
											// 정상 사용자 체감 시간은 0.1초. 해커 사용자 체감은 해시가 느릴수록 공격 비용 증가
	}
	
}
