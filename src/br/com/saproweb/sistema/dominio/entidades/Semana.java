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
@Table(name = "semana")
public class Semana implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "semana_diaturnos", joinColumns = { @JoinColumn(name = "id_semana") },
		inverseJoinColumns = { @JoinColumn(name = "id_diaturnos") })
	private List<DiaTurnos> dias;

	//Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<DiaTurnos> getDias() {
		return dias;
	}

	public void setDias(List<DiaTurnos> dias) {
		this.dias = dias;
	}	

}
