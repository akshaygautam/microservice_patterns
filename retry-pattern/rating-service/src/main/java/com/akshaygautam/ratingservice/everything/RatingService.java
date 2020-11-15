package com.akshaygautam.ratingservice.everything;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.akshaygautam.commondto.DTO.ProductRatingDTO;
import com.akshaygautam.commondto.DTO.ReviewsDTO;

@Service
public class RatingService {
	private Map<Integer, ProductRatingDTO> map;

	@PostConstruct
	private void init() {
		this.map = new HashMap<>();
		// dummy review objects for product id 1
		ReviewsDTO dto11 = this.createReviewRating("vins", "guru", 1, "", 5);
		ReviewsDTO dto12 = this.createReviewRating("marshall", "mathers", 1, "decent", 3);
		ProductRatingDTO prDto1 = new ProductRatingDTO();
		prDto1.setReviews(List.of(dto11, dto12));
		this.map.put(1, prDto1);
		// dummy review objects for product id 2
		ReviewsDTO dto21 = this.createReviewRating("slim", "shady", 2, "best", 5);
		ReviewsDTO dto22 = this.createReviewRating("snoop", "dogg", 2, "great", 4);
		ProductRatingDTO prDto2 = new ProductRatingDTO();
		prDto2.setReviews(List.of(dto21, dto22));
		this.map.put(2, prDto2);
	}

	public ProductRatingDTO getRatingForProduct(int productId) {
		return this.map.getOrDefault(productId, new ProductRatingDTO());
	}

	private ReviewsDTO createReviewRating(String fn, String ln, int productId, String comment, int rating) {
		ReviewsDTO dto = new ReviewsDTO();
		dto.setUserFirstname(fn);
		dto.setUserLastname(ln);
		dto.setProductId(productId);
		dto.setComment(comment);
		dto.setRating(rating);
		return dto;
	}

}
