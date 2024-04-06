package com.integracao.sistemas.api.trabalho01api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integracao.sistemas.api.trabalho01api.model.Produto;
import java.util.List;



@Repository
public interface I_ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByNomeContaining(String nome);
}
