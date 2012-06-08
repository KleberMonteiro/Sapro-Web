package br.com.saproweb.sistema.dominio.service;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.saproweb.utils.filtros.CustomUsernamePasswordAuthenticationFilter;

@Named("loginService")
public class LoginServiceImpl implements LoginService, Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Inject
	@Named("customUsernamePasswordAuthenticationFilter")
	private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter;

	@Override
	public void logar(String usuario, String senha) {
		try {
			customUsernamePasswordAuthenticationFilter.setUsername(usuario);
			customUsernamePasswordAuthenticationFilter.setPassword(senha);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/j_spring_security_check");
		} catch (IOException e) {
			logger.debug("logar: Erro ao tentar o login do usuario: " + usuario
					+ ". Nao foi possivel recuperar o contexto.");
			e.printStackTrace();
		}
	}
}
