package com.example.ms_ordenes.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class ResponseOrder {
    private Long id;
    private Long idClient;
    private Set<ResponseProduct> products;
    private LocalDateTime createdDate;
}
