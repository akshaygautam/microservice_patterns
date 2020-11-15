package com.akshaygautam.commondto.DTO;

import java.util.List;

public class ProductRatingDTO {
	private double avgRating;
	private List<ReviewsDTO> reviews;

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public List<ReviewsDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewsDTO> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "ProductRatingDTO [avgRating=" + avgRating + ", reviews=" + reviews + "]";
	}

}
