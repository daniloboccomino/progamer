/**
 * Digital Business Enablement
 * FIAP - Faculdade de Informática e Administração Paulista
 * Professor Joao Carlos Lima e Silva
 *
 * @class SetupBean.java
 * @description: Introducao JSF & Persistência com JPA
 * @author daniloboccomino - RM85473
 * @since Apr 6, 2021
 */

package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDao;
import br.com.fiap.model.Setup;

@Named  // CDI - Contexts and Dependency Injection
@RequestScoped
public class SetupBean {
	
	private Setup setup = new Setup();

	public void save() {
		new SetupDao().save(this.setup);
		System.out.println(this.setup);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Setup cadastrado com sucesso."));
	}
	
	public List<Setup> getSetups() {
		return new SetupDao().getAll();
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}

}
