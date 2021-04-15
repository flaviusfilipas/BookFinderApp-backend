package com.bookfinder.job;

import com.bookfinder.builder.URIBuilder;
import com.bookfinder.domain.custom.WatchlistBook;
import com.bookfinder.dto.BookDTO;
import com.bookfinder.dto.UserWatchlistDTO;
import com.bookfinder.dto.custom.OfferDTO;
import com.bookfinder.dto.custom.ScrapyResponseDTO;
import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.enums.EmailType;
import com.bookfinder.service.BookService;
import com.bookfinder.service.EmailService;
import com.bookfinder.service.UserService;
import com.bookfinder.service.UserWatchlistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
@Slf4j
public class ScheduledJobs {
    private final BookService bookService;
    private final UserWatchlistService userWatchlistService;
    private final WebClient webClient;
    private final EmailService emailService;
    private final UserService userService;

    public ScheduledJobs(BookService bookService, UserWatchlistService userWatchlistService,
                         EmailService emailService, UserService userService) {
        this.bookService = bookService;
        this.userWatchlistService = userWatchlistService;
        this.emailService = emailService;
        this.userService = userService;
        this.webClient =  WebClient.create();
    }
    @Scheduled(cron="0 0 2 * * *")
    public void updateWatchlistBook() throws UnsupportedEncodingException, URISyntaxException {
        log.info("Updating watchlist books");
        List<WatchlistBookDTO> allWatchlistBooks = bookService.getAllWatchlistBooks();
        for (WatchlistBookDTO watchlistBookDTO : allWatchlistBooks) {
            BookDTO bookDTO = watchlistBookDTO.getBookDTO();
            OfferDTO offer = bookDTO.getOffer();
            ScrapyResponseDTO body = getScrapyResponseDTO(offer);
            Double newPrice = getBookPrice(body);
            if(!newPrice.equals(offer.getPrice())){
                UserWatchlistDTO userWatchlistDTO = userWatchlistService.findById(watchlistBookDTO.getId());
                userWatchlistDTO.setLastPrice(newPrice);
                userWatchlistService.save(userWatchlistDTO);
            }
        }
    }

    @Scheduled(cron="0 0 1 * * *")
    public void managePriceAlerts() throws UnsupportedEncodingException, URISyntaxException {
        log.info("Managing price alerts");
        Predicate<WatchlistBook> hasPriceAlertPredicate = watchlistBook -> watchlistBook.getUserWatchlist().getHasPriceAlert();
        List<WatchlistBookDTO> allWatchlistBooksWithPriceAlert = bookService.getAllFilteredWatchlistBooks(hasPriceAlertPredicate);
        for(WatchlistBookDTO watchlistBookDTO : allWatchlistBooksWithPriceAlert){
            BookDTO bookDTO = watchlistBookDTO.getBookDTO();
            OfferDTO offer = bookDTO.getOffer();
            ScrapyResponseDTO body = getScrapyResponseDTO(offer);
            Double newPrice = getBookPrice(body);
            if(Math.round(newPrice) < Math.round(watchlistBookDTO.getLastPrice())){
                watchlistBookDTO.setLastPrice(newPrice);
                UserWatchlistDTO updatedWatchlistBook = userWatchlistService.save(watchlistBookDTO, watchlistBookDTO.getBookDTO().getId());
                watchlistBookDTO.setLastPrice(updatedWatchlistBook.getLastPrice());
                String userEmail = getUserEmail(watchlistBookDTO);
                emailService.sendMail(userEmail,watchlistBookDTO, EmailType.PRICE_ALERT);
            }
        }
    }

    @Scheduled(cron="0 0 0 * * *")
    public void manageStockAlerts() throws UnsupportedEncodingException, URISyntaxException {
        log.info("Managing stock alerts");
        Predicate<WatchlistBook> hasStockAlertPredicate = watchlistBook -> watchlistBook.getUserWatchlist().getHasStockAlert();
        List<WatchlistBookDTO> allWatchlistBooksWithPriceAlert = bookService.getAllFilteredWatchlistBooks(hasStockAlertPredicate);
        for(WatchlistBookDTO watchlistBookDTO : allWatchlistBooksWithPriceAlert){
            BookDTO bookDTO = watchlistBookDTO.getBookDTO();
            OfferDTO offer = bookDTO.getOffer();
            ScrapyResponseDTO body = getScrapyResponseDTO(offer);
            boolean hasStock = Objects.requireNonNull(body).getItems()
                    .stream()
                    .findFirst()
                    .map(BookDTO::getOffer)
                    .map(OfferDTO::getHasStock)
                    .orElseThrow(() -> new RuntimeException("Error no price"));
            if(hasStock){
                bookDTO.getOffer().setHasStock(true);
                bookService.save(bookDTO);
                String userEmail = getUserEmail(watchlistBookDTO);
                emailService.sendMail(userEmail,watchlistBookDTO, EmailType.STOCK_ALERT);
            }
        }
    }

    private String getUserEmail(WatchlistBookDTO watchlistBookDTO) {
        return userService
                .findById(Integer.parseInt(watchlistBookDTO.getCurrentUserId()))
                .getEmail();
    }

    private ScrapyResponseDTO getScrapyResponseDTO(OfferDTO offer) throws UnsupportedEncodingException, URISyntaxException {
        ResponseEntity<ScrapyResponseDTO> response = webClient
                .get()
                .uri(URIBuilder.buildUriForProvider(offer.getProvider(), offer.getLink()))
                .retrieve()
                .toEntity(ScrapyResponseDTO.class)
                .block();
        return Objects.requireNonNull(response).getBody();
    }
    private Double getBookPrice(ScrapyResponseDTO body) {
        return Objects.requireNonNull(body).getItems()
                .stream()
                .findFirst()
                .map(BookDTO::getOffer)
                .map(OfferDTO::getPrice)
                .orElseThrow(() -> new RuntimeException("Error no price"));
    }
}

