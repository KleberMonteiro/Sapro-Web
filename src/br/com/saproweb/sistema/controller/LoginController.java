package br.com.saproweb.sistema.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.service.LoginService;

@Named
@Scope("view")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	@Inject
	@Named("loginService")
	private LoginService loginService;

	public void doLogin() {
		loginService.logar(this.username, this.password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}