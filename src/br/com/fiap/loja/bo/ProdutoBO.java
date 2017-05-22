package br.com.fiap.loja.bo;

import java.util.List;

import br.com.fiap.loja.dao.ProdutoDAO;
import br.com.fiap.loja.dao.impl.ProdutoDAOImpl;
import br.com.fiap.loja.entity.Produto;
import br.com.fiap.loja.exception.DataBaseException;

public class ProdutoBO {
	
	 private ProdutoDAO dao = new ProdutoDAOImpl();
	 
	 public Produto buscarPorID(Integer id){
		 return dao.buscarPorId(id);		 
	 }
	 
	 public void CadastrarProduto(Produto produto){
		 try {
			dao.adicionar(produto);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
	 }
	 
	 public List<Produto> buscarTodos(){
		 return dao.buscarTodos();
	 }
	 
}
