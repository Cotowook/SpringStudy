package org.zerock.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final AccountMapper accountMapper ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("---------------- loadUserByUsername ----------------");
		log.info(username);

		// 스프링 시큐리티에서 제공하는 사용자 정보가 담긴 규격 객체(UserDetails 구현 객체)를 반환
		/*
		 * UserDetails user = User.builder() .username(username)
		 * .password("$2a$10$QGIhCPCG7O4IEgMAxgweWeuPCUSIPlmJYiDr/axdDd/602EYbh4Xi") //
		 * 1111 .roles("USER") // ROLE_USER .build();
		 */
		
		// 임시로 AccountDTO를 직접 만들어 사용
		/*
		 * AccountDTO accountDTO = new AccountDTO(); accountDTO.setUid(username);
		 * accountDTO.setUpw(
		 * "$2a$10$QGIhCPCG7O4IEgMAxgweWeuPCUSIPlmJYiDr/axdDd/602EYbh4Xi");
		 * accountDTO.addRole(AccountRole.USER);
		 * accountDTO.addRole(AccountRole.MANAGER);
		 */
		
		AccountDTO accountDTO = accountMapper.selectOne(username);
		
		if(accountDTO == null) {
			throw new UsernameNotFoundException("Account Not Found");
		}
		
		return accountDTO;
	}
	
}
