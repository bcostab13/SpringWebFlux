package com.learning.springreactive.springbootwebflux.controllers;

import com.learning.springreactive.springbootwebflux.models.dao.ProductoDAO;
import com.learning.springreactive.springbootwebflux.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoDAO productoDAO;

    @GetMapping
    public Flux<Producto> index() {
        Flux<Producto> productos = productoDAO.findAll().map(producto -> {

            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        });
        return productos;
    }

    @GetMapping("/{id}")
    public Mono<Producto> showDetail(@PathVariable("id") String id) {
        //Mono<Producto> producto = productoDAO.findById(id);
        Flux<Producto> productos = productoDAO.findAll();
        Mono<Producto> producto = productos.filter(p -> p.getId().equals(id)).next();
        return producto;
    }

}
