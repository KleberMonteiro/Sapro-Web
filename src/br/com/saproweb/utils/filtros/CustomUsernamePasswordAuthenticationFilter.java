package br.com.saproweb.utils.filtros;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public CustomUsernamePasswordAuthenticationFilter() {
		super();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		Authentication result = null;
		
		if (this.username == null) {
			this.username = "";
		}

		if (this.password == null) {
			this.password = "";
		}

		this.username = this.username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				this.username, this.password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		result = this.getAuthenticationManager().authenticate(authRequest);
		
		this.username = null;
		this.password = null;
		
		return result;
	}


	/**
	 * Provided so that subclasses may configure what is put into the
	 * authentication request's details property.
	 * 
	 * @param request
	 *            that an authentication request is being created for
	 * @param authRequest
	 *            the authentication request object that should have its details
	 *            set
	 */
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource
				.buildDetails(request));
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}
