package com.bookfinder.controller;

import com.bookfinder.dto.UserWatchlistDTO;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.service.UserWatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-watchlists")
@CrossOrigin("*")
public class UserWatchlistResource {
    private final UserWatchlistService userWatchlistService;

    public UserWatchlistResource(UserWatchlistService userWatchlistService) {
        this.userWatchlistService = userWatchlistService;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        userWatchlistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/alerts/{id}")
    public ResponseEntity<UserWatchlistDTO> addAlert(@PathVariable Integer id, @RequestParam("alertType") String alertType){
        UserWatchlistDTO userWatchlistDTO = userWatchlistService.manageAlert(id, alertType,true);
        return ResponseEntity.ok(userWatchlistDTO);
    }
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<UserWatchlistDTO> deleteAlert(@PathVariable Integer id, @RequestParam("alertType") String alertType){
        UserWatchlistDTO userWatchlistDTO = userWatchlistService.manageAlert(id, alertType,false);
        return ResponseEntity.ok(userWatchlistDTO);
    }
}
