package com.akshaygautam.ratingservice.everything;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshaygautam.commondto.DTO.ProductRatingDTO;

@RestController
@RequestMapping("/v1")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	private static final int MIN = 10;
	private static final int MAX = 5_000;

	@GetMapping("/ratings/{productId}")
	public ProductRatingDTO getRating(@PathVariable int productId) throws InterruptedException {
		int sleepTime = ThreadLocalRandom.current().nextInt(MIN,MAX);
		System.out.println("Rating service will respond in " + sleepTime + " ms");
		Thread.sleep(sleepTime);
		return ratingService.getRatingForProduct(productId);
	}
}
