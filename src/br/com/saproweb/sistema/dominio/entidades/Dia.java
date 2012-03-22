package br.com.saproweb.sistema.dominio.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dia")
public class Dia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Turno manha;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Turno tarde;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Turno noite;

	//Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Turno getManha() {
		return manha;
	}

	public void setManha(Turno manha) {
		this.manha = manha;
	}

	public Turno getTarde() {
		return tarde;
	}

	public void setTarde(Turno tarde) {
		this.tarde = tarde;
	}

	public Turno getNoite() {
		return noite;
	}

	public void setNoite(Turno noite) {
		this.noite = noite;
	}	

}
