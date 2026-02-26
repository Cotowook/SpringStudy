package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ReplyDTO;

public interface ReplyMapper {
	int insert(ReplyDTO dto);
	
	ReplyDTO read(@Param("rno") Long rno);
	
	int update(ReplyDTO dto);
	
	int delete(@Param("rno") Long rno);
	
	List<ReplyDTO> listOfBoard(
			@Param("bno") Long bno,
			@Param("skip") int skip,
			@Param("limit") int limit);
	
	int countOfBoard(@Param("bno") Long bno);
}
