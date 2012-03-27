package br.com.saproweb.sistema.dominio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "periodo")
	private String periodo;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "periodo_dia", joinColumns = { @JoinColumn(name = "id_periodo") },
		inverseJoinColumns = { @JoinColumn(name = "id_dia") })
	private List<DiaDisciplina> dias;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<DiaDisciplina> getDias() {
		return dias;
	}

	public void setDias(List<DiaDisciplina> dias) {
		this.dias = dias;
	}

}