package org.moviecrudspring.productapi.Repository;

import org.moviecrudspring.productapi.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends MongoRepository<Product, String>
{

}
