package br.com.fiap.loja.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.loja.dao.ProdutoDAOHttp;
import br.com.fiap.loja.entity.Produto;

@ManagedBean
@ViewScoped
public class indexBean {
	
	private ProdutoDAOHttp daoHttp = new ProdutoDAOHttp ();
	private List<Produto> produtos;
	
	//método que será executado após a construção da classe
	@PostConstruct
	public void init () {
		produtos = new ArrayList<Produto>();
		produtos = daoHttp.getProdutos();
		
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
