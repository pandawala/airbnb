package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;
    public ReviewController(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/userReviews")
    public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal PropertyUser user){
        List<Review> byPropertyUser = reviewRepository.findByPropertyUser(user);

        return new ResponseEntity<>(byPropertyUser,HttpStatus.OK);
    }

    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview(@PathVariable long propertyId,
                                            @RequestBody Review review,
                                            @AuthenticationPrincipal PropertyUser user){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();

        Review r = reviewRepository.findByPropertyAndPropertyUser(property, user);
        if(r!=null){
            return new ResponseEntity<>("you have already added a review for user",HttpStatus.OK);
        }

        review.setProperty(property);
        review.setPropertyUser(user);

        reviewRepository.save(review);

        return new ResponseEntity<>(" review successfully added", HttpStatus.OK);
    }
}
