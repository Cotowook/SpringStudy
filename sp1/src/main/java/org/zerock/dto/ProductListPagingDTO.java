package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ProductListPagingDTO {
	private static final int BLOCK_SIZE = 10;
	
	private List<ProductListDTO> productDTOList;	
	private int totalCount;	
	private int page;	
	private int size;
	
	private int start; 
	private int end; 
	private boolean prev;
	private boolean next;
	private List<Integer> pageNums;
	
	public ProductListPagingDTO(List<ProductListDTO> productDTOList, int totalCount, int page, int size) {
		super();
		this.productDTOList = productDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		int tempEnd = (int) (Math.ceil(page / (BLOCK_SIZE * 1.0))) * 10; 
		this.start = tempEnd - (BLOCK_SIZE - 1); 
		this.prev = start != 1;

		int lastPage = (int)Math.ceil(totalCount/(double)size); 
		this.end = Math.min(tempEnd, lastPage);
		this.next = end < lastPage; 
		
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}	
	
	
}
