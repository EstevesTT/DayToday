package br.com.fiap.daytoday.controllers;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.daytoday.models.metas;
import br.com.fiap.daytoday.repository.MetaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetasController {

    Logger log = LoggerFactory.getLogger(MetasController.class);

    List<metas> metas = new ArrayList<>();

    @Autowired // IoD IoC
    MetaRepository repository;

    @GetMapping
    public List<metas> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<metas> create(@RequestBody metas meta){
        log.info("cadastrando meta: " + meta);

        repository.save(meta);

        return ResponseEntity.status(HttpStatus.CREATED).body(meta);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<metas> show(@PathVariable Long id){
        log.info("buscando meta com id " + id);
        var metaEncontrada = repository.findById(id);

        if (metaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(metaEncontrada.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<metas> destroy(@PathVariable Long id){
        log.info("apagando meta com id " + id);
        var metaEncontrada = repository.findById(id);

        if (metaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(metaEncontrada.get());

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<metas> update(@PathVariable Long id, @RequestBody metas meta){
        log.info("alterando meta com id " + id);
        var metaEncontrada = repository.findById(id);

        if (metaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

            meta.setId(id);

        repository.save(meta);

        return ResponseEntity.ok(meta);

    }


    
    
}
