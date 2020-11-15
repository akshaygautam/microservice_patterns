package com.akshaygautam.productserice.everything;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akshaygautam.commondto.DTO.ProductRatingDTO;

@Service
public class RatingService {
	
	@Value("${rating.service.url}")
	private String ratingService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ProductRatingDTO getRatings(int productId) {
		String url = this.ratingService+"/"+productId;
		return restTemplate.getForObject(url, ProductRatingDTO.class);
	}
}
