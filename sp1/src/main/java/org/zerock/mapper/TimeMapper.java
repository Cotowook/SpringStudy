package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	// DB의 현재 시간을 조회하는 SQL 
	@Select("SELECT NOW()")
	String getTIme();
	
	String getTime2();
}
