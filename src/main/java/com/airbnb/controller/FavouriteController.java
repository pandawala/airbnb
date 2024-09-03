package com.airbnb.controller;

import com.airbnb.entity.Favourite;
import com.airbnb.entity.FavouriteRepository;
import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    private FavouriteRepository favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(@RequestBody Favourite favourite
    , @AuthenticationPrincipal PropertyUser user){

        favourite.setPropertyUser(user);
        Favourite save = favouriteRepository.save(favourite);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }
}
