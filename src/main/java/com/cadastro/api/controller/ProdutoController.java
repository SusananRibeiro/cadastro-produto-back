package com.cadastro.api.controller;
import com.cadastro.api.entities.Produto;
import com.cadastro.api.mappers.ProdutoMapper;
import com.cadastro.api.models.ProdutoRequest;
import com.cadastro.api.models.ProdutoResponse;
import com.cadastro.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue-produto")
    public ResponseEntity<List<ProdutoResponse>> carregarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponse> out = produtos.stream()
                .map(ProdutoMapper::produtoMapperResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(out);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue-produto/{id}")
    public ResponseEntity<ProdutoResponse> carregarProdutoById(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).get();

        ProdutoResponse out = ProdutoMapper.produtoMapperResponse(produto);

        return ResponseEntity.ok(out);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/crie-produto")
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoRepository.save(ProdutoMapper.produtoMapperRequest(produtoRequest));

        Produto produtoRetorno = produtoRepository.save(produto);

        ProdutoResponse out = ProdutoMapper.produtoMapperResponse(produtoRetorno);

        return ResponseEntity.status(201).body(out);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/atualize-produto/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long id,
                                                            @RequestBody ProdutoRequest produtoRequest){
        Produto produto = produtoRepository.findById(id)
                .map(record -> {
                    record.setNome(produtoRequest.getNome());
                    record.setCategoria(produtoRequest.getCategoria());
                    record.setValor(produtoRequest.getValor());

                    return produtoRepository.save(record);

                }).get();

        ProdutoResponse out = ProdutoMapper.produtoMapperResponse(produto);

        return ResponseEntity.ok(out);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/delete-produto/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

}
