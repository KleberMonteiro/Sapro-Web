package br.com.saproweb.sistema.dominio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private int periodo;
	
	@ManyToMany(targetEntity = Disciplina.class, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "periodo_disciplina", joinColumns = @JoinColumn(name = "id_periodo"), inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
	private List<Disciplina> disciplinas;
	
	// Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
