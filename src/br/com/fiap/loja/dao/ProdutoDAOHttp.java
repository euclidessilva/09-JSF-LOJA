package br.com.fiap.loja.dao;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.loja.entity.Produto;

public class ProdutoDAOHttp {
	Client c = new Client ();
	
	private final String URL = "http://10.6.2.29:8080/08-REST-PRODUTO-BANCO/rest/loja/";
	//método que retorn via GET todos os produtos em um List
	public List<Produto> getProdutos (){
		try {
			Gson gson = new Gson();
			//Recurso jersey que aponta para o endpoint da nossa API
			WebResource wr = c.resource(URL + "produtos");
			//faz o get para buscar na API os produtos
			String json = wr.get(String.class);
			//Cria um List de produtos a partir do json quebrado em tokens
			List<Produto> produtos = gson.fromJson(json, new TypeToken<List<Produto>>() {}.getType());
			//retorna produtos
			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//
	public Produto getProduto (String id){
		try {
			Gson gson = new Gson();
			WebResource wr = c.resource(URL + "produtoPorId" + id);
			String json = wr.get(String.class);
			Produto produto = gson.fromJson(json, Produto.class);
			return produto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
