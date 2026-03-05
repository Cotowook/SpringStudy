package org.zerock.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AccountDTO implements UserDetails {
	private String uid;
	private String upw;
	private String uname;
	private String email;
	private List<AccountRole> roleNames = new ArrayList<>();
	
	public void addRole(AccountRole role) {
		roleNames.add(role);
	}
	
	public void clearRole() {
		roleNames.clear();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roleNames.stream()
				.map(accountRole-> new SimpleGrantedAuthority("ROLE_" + accountRole.name()))
				.toList();
	}

	@Override
	public String getPassword() {
		return upw;
	}

	@Override
	public String getUsername() {
		return uid; // uid, uname 혼동 주의. uid가 로그인시 필요한 name을 말함
		// 로그인 식별값 (ID)를 리턴하는 것을 권장
	}
	
	
	
	// 기술적으로 필요없지만 확장을 대비하여 재정의
	// 관리지가 계정을 정지시킬수 있다. 비밀번호 만료 정책있음
	
	@Override
	public boolean isAccountNonExpired() {
		// 만료되지 않았음
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// 잠긴 계정 아님
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// 인증정보 활용 가능함
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// 사용 가능(=활성 계정임)
		return true;
	}

	
}
