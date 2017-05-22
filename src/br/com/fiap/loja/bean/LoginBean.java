package br.com.fiap.loja.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.loja.dao.UsuarioDAO;
import br.com.fiap.loja.dao.impl.UsuarioDAOImpl;
import br.com.fiap.loja.entity.Usuario;

@ManagedBean
@ViewScoped
public final class LoginBean {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	private Usuario usuario = new Usuario();
	
	public String logar (){
		//DAO busca usuario pelo getUuario passando como parametro os valores digitados na tela xhml
		//getEmail e getSenha
		usuario = usuarioDAO.getUsuario(usuario.getEmail(), usuario.getSenha());
		if (usuario == null) {
			//limpa o usuario q foi digitado no xhtml
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario nao cadastrado", "erro ao logar"));
			return null;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario cadastrado", "sucesso ao logar"));
			return "/main";
		} 
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
