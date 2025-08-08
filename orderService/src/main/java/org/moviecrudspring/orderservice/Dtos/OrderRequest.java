package org.moviecrudspring.orderservice.Dtos;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, BigDecimal price  , int quantity, String skuCode) {
}
