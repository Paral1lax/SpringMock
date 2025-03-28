package com.example.SpringMock.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private float balance; // Я изменил на float, потому что BigDecimal почему-то меняет значение и дописывает кучу символов после запятой
    private BigDecimal maxLimit;
}
