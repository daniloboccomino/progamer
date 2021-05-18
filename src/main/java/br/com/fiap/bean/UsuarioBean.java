/**
 * Digital Business Enablement
 * FIAP - Faculdade de Informática e Administração Paulista
 * Professor Joao Carlos Lima e Silva
 *
 * @class UsuarioBean.java
 * @description:
 * @author daniloboccomino - RM85473
 * @since Apr 17, 2021
 */

package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;

@Named  // CDI - Contexts and Dependency Injection
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	public void save() {
		new UsuarioDao().save(this.usuario);
		System.out.println(this.usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado com sucesso."));
	}
	
	public List<Usuario> getUsuarios() {
		return new UsuarioDao().getAll();
	}
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		boolean exist = new UsuarioDao().exist(usuario);
		if (exist) {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "index?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login invalido", "erro"));
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuario");
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
