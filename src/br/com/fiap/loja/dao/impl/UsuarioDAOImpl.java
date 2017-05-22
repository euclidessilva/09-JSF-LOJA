package br.com.fiap.loja.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.loja.dao.UsuarioDAO;
import br.com.fiap.loja.entity.Usuario;
import br.com.fiap.loja.singleton.EntityManagerFactorySingleton;


public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO{

	@Override
	public Usuario getUsuario(String email, String senha) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		try {
			Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.email = :email and u.senha =:senha").setParameter("email", email).setParameter("senha", senha).getSingleResult();
			return usuario;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
