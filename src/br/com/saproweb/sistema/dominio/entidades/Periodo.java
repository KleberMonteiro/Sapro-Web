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
@Table(name = "periodo")
public class Periodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "periodo")
	private String periodo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaSegunda;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaTerca;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaQuarta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaQuinta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaSexta;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaSabado;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Disciplina disciplinaDomingo; 
	
	// Gets e Sets
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

	public Disciplina getDisciplinaSegunda() {
		return disciplinaSegunda;
	}

	public void setDisciplinaSegunda(Disciplina disciplinaSegunda) {
		this.disciplinaSegunda = disciplinaSegunda;
	}

	public Disciplina getDisciplinaTerca() {
		return disciplinaTerca;
	}

	public void setDisciplinaTerca(Disciplina disciplinaTerca) {
		this.disciplinaTerca = disciplinaTerca;
	}

	public Disciplina getDisciplinaQuarta() {
		return disciplinaQuarta;
	}

	public void setDisciplinaQuarta(Disciplina disciplinaQuarta) {
		this.disciplinaQuarta = disciplinaQuarta;
	}

	public Disciplina getDisciplinaQuinta() {
		return disciplinaQuinta;
	}

	public void setDisciplinaQuinta(Disciplina disciplinaQuinta) {
		this.disciplinaQuinta = disciplinaQuinta;
	}

	public Disciplina getDisciplinaSexta() {
		return disciplinaSexta;
	}

	public void setDisciplinaSexta(Disciplina disciplinaSexta) {
		this.disciplinaSexta = disciplinaSexta;
	}

	public Disciplina getDisciplinaSabado() {
		return disciplinaSabado;
	}

	public void setDisciplinaSabado(Disciplina disciplinaSabado) {
		this.disciplinaSabado = disciplinaSabado;
	}

	public Disciplina getDisciplinaDomingo() {
		return disciplinaDomingo;
	}

	public void setDisciplinaDomingo(Disciplina disciplinaDomingo) {
		this.disciplinaDomingo = disciplinaDomingo;
	}

}
