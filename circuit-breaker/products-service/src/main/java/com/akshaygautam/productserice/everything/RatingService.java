package com.akshaygautam.productserice.everything;

import java.net.SocketTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akshaygautam.commondto.DTO.ProductRatingDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class RatingService {
	
	@Value("${rating.service.url}")
	private String ratingService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Retryable(backoff = @Backoff(delay = 1000),
			value = {SocketTimeoutException.class},
			maxAttempts = 2)
	@CircuitBreaker(fallbackMethod = "fallback",name = "ratingService")
	public ProductRatingDTO getRatings(int productId) {
		String url = this.ratingService+"/"+productId;
		return restTemplate.getForObject(url, ProductRatingDTO.class);
	}
	

    public ProductRatingDTO getRecoveryRatings() {
        return new ProductRatingDTO();
    }
    
    public ProductRatingDTO fallback(int productId, Exception e) {
    	System.out.printf("Fallback circuit break, id: %d, exce: %s",productId, e.getMessage());
        return new ProductRatingDTO();
    }
}
