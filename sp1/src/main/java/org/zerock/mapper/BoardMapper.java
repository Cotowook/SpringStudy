package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDTO;

public interface BoardMapper {
	int insert(BoardDTO dto);
	
	BoardDTO selectOne(Long bno);
	
	int remove(Long bno);
	
	int update(BoardDTO dto);
	
	List<BoardDTO> list();
	
	List<BoardDTO> list2(@Param("skip") int skip, @Param("count") int count);
	// MyBatis에서 여러 값을 전달하려면 @Param, Map, 객체 등등으로 가능
	// 잘못된 코드 : int skip, int count 다음과 같이 매개변수 사용할 경우 => MyBatis에선 skip, count 라는 이름을 모르기 때문에 #{skip} 사용불가
	// 	1. @Param 사용 : @Param("skip") 으로 변경시 사용가능 (파라미터가 1개일 경우 @Param 생략가능. 2개 이상인 경우에는 반드시 명시)
	//	2. Map 사용 : list2(Map<String, Integar>param);
	//	3. DTO 객체 사용 : 
	
	int listCount();
}
