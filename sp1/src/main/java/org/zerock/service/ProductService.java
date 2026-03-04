package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;
import org.zerock.dto.ProductListPagingDTO;
import org.zerock.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ProductService {
	private final ProductMapper productMapper;
	
	public Integer register(ProductDTO productDTO) {
		 productMapper.insert(productDTO);
		 
		 Integer pno = productDTO.getPno();
		 
		 if(productDTO.getImageList() != null && !productDTO.getImageList().isEmpty())
			 productMapper.insertImages(productDTO);
		 
		 return pno;
	}
	
	public ProductListPagingDTO getList(int page, int size) {
		page = page <= 0 ? 1 : page;
		size = (size < 10 || size > 100) ? 10 : size;
		int skip = (page - 1) * size;
		
		List<ProductListDTO> list =  productMapper.list(skip, size);
		
		int total = productMapper.listCount();
		
		return new ProductListPagingDTO(list, total, page, size);
	}
	
	public ProductDTO read(Integer pno) {
		return productMapper.selectOne(pno);
	}
	
	
}
