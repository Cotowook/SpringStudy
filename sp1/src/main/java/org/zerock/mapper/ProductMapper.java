package org.zerock.mapper;

import org.zerock.dto.ProductDTO;

public interface ProductMapper {
	int insert(ProductDTO productDTO);
	
	int insertImage(ProductDTO productDTO);
}
