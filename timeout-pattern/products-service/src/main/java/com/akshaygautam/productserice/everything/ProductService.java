package com.akshaygautam.productserice.everything;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshaygautam.commondto.DTO.ProductDTO;
import com.akshaygautam.commondto.DTO.ProductRatingDTO;

@Service
public class ProductService {

	private Map<Integer, ProductDTO> map;

	@Autowired
	private RatingService ratingService;

	@PostConstruct
	private void init() {
		this.map = new HashMap();
		this.map.put(1, this.getProduct(1, "The eminem show", 12.12));
		this.map.put(2, this.getProduct(2, "Blood on the dance floor", 12.45));
	}

	public ProductDTO getProduct(int productId) {
		System.out.println("------Looking for product---------");
		ProductDTO dto = this.map.get(productId);
		try {
			System.out.println("------Looking for Rating---------");
			ProductRatingDTO productRating = this.ratingService.getRatings(productId);
			dto.setProductRating(productRating);
			System.out.println("------Found rating---------");
		} catch (Exception e) {
			System.out.printf("oh!! something is not right with rating service, %s", e.getMessage());
		}
		return dto;
	}

	private ProductDTO getProduct(int productId, String description, double price) {
		ProductDTO dto = new ProductDTO();
		dto.setProductId(productId);
		dto.setDescription(description);
		dto.setPrice(price);
		return dto;
	}
}
