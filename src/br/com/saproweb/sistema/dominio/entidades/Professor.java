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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "nome", nullable = false, columnDefinition="VARCHAR(60) default ''")
	private String nome = "";
	
	@Column(name = "matricula", nullable = false, columnDefinition="VARCHAR(30) default ''")
	private String matricula = "";
	
	@OneToOne(cascade = CascadeType.ALL)
	private QuadroDeHorarios quadroDeHorarios;
	
	@ManyToMany(targetEntity = Disciplina.class, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "professor_disciplina", joinColumns = @JoinColumn(name = "id_professor"), inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
	private List<Disciplina> disciplinas;

	// Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public QuadroDeHorarios getQuadroDeHorarios() {
		return quadroDeHorarios;
	}

	public void setQuadroDeHorarios(QuadroDeHorarios quadroDeHorarios) {
		this.quadroDeHorarios = quadroDeHorarios;
	}

}
