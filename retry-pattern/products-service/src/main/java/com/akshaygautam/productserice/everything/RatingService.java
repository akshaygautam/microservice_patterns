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

@Service
public class RatingService {
	
	@Value("${rating.service.url}")
	private String ratingService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Retryable(backoff = @Backoff(delay = 1000),
			value = {SocketTimeoutException.class},
			maxAttempts = 2)
	public ProductRatingDTO getRatings(int productId) {
		String url = this.ratingService+"/"+productId;
		return restTemplate.getForObject(url, ProductRatingDTO.class);
	}
	

    @Recover
    public ProductRatingDTO getRecoveryRatings() {
        return new ProductRatingDTO();
    }
}
