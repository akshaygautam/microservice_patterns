package com.akshaygautam.productserice.everything;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshaygautam.commondto.DTO.ProductDTO;

@RestController
@RequestMapping("/product-service")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/product/{id}")
	public ProductDTO getProduct(@PathVariable int id) {
		return this.productService.getProduct(id);
	}
}
