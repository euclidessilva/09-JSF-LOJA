package br.com.fiap.loja.dao;

import br.com.fiap.loja.entity.Produto;
import br.com.fiap.loja.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{
	
	public Usuario getUsuario (String email, String senha);

}
