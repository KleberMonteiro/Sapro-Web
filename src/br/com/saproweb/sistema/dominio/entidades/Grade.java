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
@Table(name = "grade")
public class Grade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "grade_periodo", joinColumns = { @JoinColumn(name = "id_grade") },
		inverseJoinColumns = { @JoinColumn(name = "id_periodo") })
	private List<Periodo> periodos;
	
	// Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}	

}
