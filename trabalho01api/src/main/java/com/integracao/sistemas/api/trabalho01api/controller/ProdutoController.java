package com.integracao.sistemas.api.trabalho01api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracao.sistemas.api.trabalho01api.model.Produto;
import com.integracao.sistemas.api.trabalho01api.repository.I_ProdutoRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/produtos")
@AllArgsConstructor
@RestController
public class ProdutoController {


    private final I_ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listar(){
        //return produtoRepository.findByNome("RTX 2050");
        return produtoRepository.findAll();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);

        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContaining(nome);

        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(produtos);
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Produto create(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> update(@PathVariable Long produtoId, @RequestBody Produto produto) {
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }

        produto.setId(produtoId);
        produto = produtoRepository.save(produto);
        
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoId){
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(produtoId);
        return ResponseEntity.noContent().build();
    }

}
