package loja.app.controller;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import loja.app.model.Produto;
import loja.app.shared.Utils;

@Controller("/produtos")
public class ProdutoController {

	private Map<String, Produto> produtos;

	@Get("/teste")
	public String index() {
		return "Executando com sucesso!!!!";
	}

	@Get("/status")
	public HttpStatus indexStatus() {
		System.out.println("realizado com sucesso!!!");
		return HttpStatus.OK;
	}

	@Post("/lista")
	public HttpResponse<Map<String, Produto>> getProdutos() {
		return HttpResponse.ok(produtos);
	}
	
	
	@Get("/{id}")
	public HttpResponse<Produto> getProduto(@PathVariable String id) {

		if (produtos.containsKey(id)) {
			Produto produto = produtos.get(id);
			return HttpResponse.ok(produto);
		} else {
			return HttpResponse.noContent();
		}
	}

	@Post
	public HttpResponse<Produto> salvarProduto(@Body Produto produto) {

		String id = Utils.gerandoID();
		produto.setId(id);

		if (produtos == null) {
			produtos = new HashMap<>();
		}

		produtos.put(id, produto);

		return HttpResponse.created(produto);
	}

	@Put("/{id}")
	public HttpResponse<Produto> atualizaProduto(@PathVariable String id, @Body Produto produto) {

		if (produtos.containsKey(id)) {
			Produto prod = produtos.get(id);
			prod.setNome(produto.getNome());
			prod.setPreco(produto.getPreco());
			
			return HttpResponse.ok(prod);
		} else {
			return HttpResponse.noContent();
		}
	}

	@Delete("/{id}")
	public HttpResponse<Produto> deleteProduto(@PathVariable String id) {

		if (produtos.containsKey(id)) {
			produtos.remove(id);
			return HttpResponse.ok();
		} else {
			return HttpResponse.noContent();
		}
	}

}