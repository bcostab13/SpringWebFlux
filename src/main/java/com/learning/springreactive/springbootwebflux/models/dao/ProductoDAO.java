package com.learning.springreactive.springbootwebflux.models.dao;

import com.learning.springreactive.springbootwebflux.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDAO extends ReactiveMongoRepository<Producto, String> {

}
