package com.cadastro.api.mappers;

import com.cadastro.api.entities.Produto;
import com.cadastro.api.models.ProdutoRequest;
import com.cadastro.api.models.ProdutoResponse;

public class ProdutoMapper {

// Convertendo "Produto" que é o objeto de entrada -> para "ProdutoResponse" que é o objeto de saída.
    public static ProdutoResponse produtoMapperResponse(Produto produto){
        ProdutoResponse out = new ProdutoResponse();
        out.setId(produto.getId()); // set é o que vai retornar, get é o que vai atribuir
        out.setNome(produto.getNome());
        out.setCategoria(produto.getCategoria());
        out.setValor(produto.getValor());

        return out;
    }

// Convertendo "ProdutoRequest" para "Produto"
    public static Produto produtoMapperRequest(ProdutoRequest produtoRequest){
        Produto out = new Produto();
        out.setId(produtoRequest.getId()); // set é o que vai retornar, get é o que vai atribuir
        out.setNome(produtoRequest.getNome());
        out.setCategoria(produtoRequest.getCategoria());
        out.setValor(produtoRequest.getValor());

        return out;
    }
}
