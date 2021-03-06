package com.bookfinder.service;

import com.bookfinder.domain.UserWatchlist;
import com.bookfinder.dto.UserWatchlistDTO;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.mapper.UserWatchlistMapper;
import com.bookfinder.repository.UserWatchlistRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserWatchlistService {
    private final UserWatchlistRepository userWatchlistRepository;
    private final UserWatchlistMapper userWatchlistMapper;

    public UserWatchlistService(UserWatchlistRepository userWatchlistRepository, UserWatchlistMapper userWatchlistMapper) {
        this.userWatchlistRepository = userWatchlistRepository;
        this.userWatchlistMapper = userWatchlistMapper;
    }

    public UserWatchlistDTO save(WatchlistBookDTO watchlistBookDTO, Integer bookId){
        UserWatchlistDTO userWatchlistDTO = this.createUserWatchlist(watchlistBookDTO, bookId);
        return this.save(userWatchlistDTO);
    }
    public UserWatchlistDTO save(UserWatchlistDTO userWatchlistDTO){
        UserWatchlist userWatchlist = userWatchlistMapper.toEntity(userWatchlistDTO);
        UserWatchlist savedUserWatchlist = userWatchlistRepository.save(userWatchlist);
        return userWatchlistMapper.toDto(savedUserWatchlist);
    }

    public UserWatchlistDTO createUserWatchlist(WatchlistBookDTO watchlistBookDTO,Integer bookId){
        UserWatchlistDTO userWatchlistDTO = new UserWatchlistDTO();
        userWatchlistDTO.setUserId(watchlistBookDTO.getCurrentUserId());
        userWatchlistDTO.setBookId(bookId);
        userWatchlistDTO.setOriginalPrice(watchlistBookDTO.getOriginalPrice());
        userWatchlistDTO.setLastPrice(watchlistBookDTO.getOriginalPrice());
        userWatchlistDTO.setHasStockAlert(watchlistBookDTO.getHasStockAlert());
        userWatchlistDTO.setHasPriceAlert(watchlistBookDTO.getHasPriceAlert());
        return userWatchlistDTO;
    }

    public void deleteById(Integer id){
        userWatchlistRepository.deleteById(id);
    }

    public UserWatchlistDTO findById(Integer id){
        UserWatchlist userWatchlist =  userWatchlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user watchlist with id " + id));
        return userWatchlistMapper.toDto(userWatchlist);
    }

    public UserWatchlistDTO manageAlert(Integer id, String alertType,Boolean alertValue){
        UserWatchlistDTO userWatchlistDTO = this.findById(id);
        UserWatchlistDTO updatedWatchlist = null;
        switch (alertType) {
            case "stock": userWatchlistDTO.setHasStockAlert(alertValue);
            updatedWatchlist = this.save(userWatchlistDTO);
            break;
            case "price": userWatchlistDTO.setHasPriceAlert(alertValue);
            updatedWatchlist = this.save(userWatchlistDTO);
            break;
            case "all" : userWatchlistDTO.setHasPriceAlert(alertValue);
            userWatchlistDTO.setHasStockAlert(true);
            updatedWatchlist = this.save(userWatchlistDTO);
            break;
            default: System.out.println("Error");
        }
        return updatedWatchlist;
    }
}
