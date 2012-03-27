package br.com.saproweb.sistema.dominio.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.saproweb.utils.enumeration.DiaEnum;

@Entity
@Table(name = "diadisciplina")
public class DiaDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "dia")
	@Enumerated(EnumType.ORDINAL)
	private DiaEnum dia;
	
	@OneToOne
	private Disciplina disciplina;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DiaEnum getDia() {
		return dia;
	}

	public void setDia(DiaEnum dia) {
		this.dia = dia;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}	

}
