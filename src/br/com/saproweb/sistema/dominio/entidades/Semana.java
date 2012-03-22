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
@Table(name = "semana")
public class Semana implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia domingo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia segunda;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia terca;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia quarta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia quinta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia sexta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Dia sabado;

	//Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Dia getDomingo() {
		return domingo;
	}

	public void setDomingo(Dia domingo) {
		this.domingo = domingo;
	}

	public Dia getSegunda() {
		return segunda;
	}

	public void setSegunda(Dia segunda) {
		this.segunda = segunda;
	}

	public Dia getTerca() {
		return terca;
	}

	public void setTerca(Dia terca) {
		this.terca = terca;
	}

	public Dia getQuarta() {
		return quarta;
	}

	public void setQuarta(Dia quarta) {
		this.quarta = quarta;
	}

	public Dia getQuinta() {
		return quinta;
	}

	public void setQuinta(Dia quinta) {
		this.quinta = quinta;
	}

	public Dia getSexta() {
		return sexta;
	}

	public void setSexta(Dia sexta) {
		this.sexta = sexta;
	}

	public Dia getSabado() {
		return sabado;
	}

	public void setSabado(Dia sabado) {
		this.sabado = sabado;
	}

}
