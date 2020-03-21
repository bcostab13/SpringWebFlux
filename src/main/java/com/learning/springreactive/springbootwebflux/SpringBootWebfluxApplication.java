package com.learning.springreactive.springbootwebflux;

import com.learning.springreactive.springbootwebflux.models.dao.ProductoDAO;
import com.learning.springreactive.springbootwebflux.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

    @Autowired
    private ProductoDAO productoDAO;

    private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux.just(
          new Producto("lavadora", 12.5),
                new Producto("secadora", 10.5),
                new Producto("peine", 1.0)
        ).flatMap(producto -> productoDAO.save(producto))
                .subscribe(producto -> log.info(producto.getId()));
    }
}
