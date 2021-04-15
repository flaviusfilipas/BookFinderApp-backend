package com.bookfinder.dto.custom;

import com.bookfinder.dto.BookDTO;
import lombok.Data;

import java.util.List;
@Data
public class ScrapyResponseDTO {
    private String status;
    private List<BookDTO> items;
    private List<Object> items_dropped;
    private Object stats;
    private String spider_name;
}
