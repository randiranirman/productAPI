package org.moviecrudspring.productapi.Dtos;

import java.math.BigDecimal;

public record ProductResponse(String id , String name , String description  , BigDecimal price ) {


}
