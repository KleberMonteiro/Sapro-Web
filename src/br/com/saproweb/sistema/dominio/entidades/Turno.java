package br.com.saproweb.sistema.dominio.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turno")
public class Turno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "horario_1")
	private boolean horario1 = false;
	
	@Column(name = "horario_2")
	private boolean horario2 = false;

	// Gets e Sets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isHorario1() {
		return horario1;
	}

	public void setHorario1(boolean horario1) {
		this.horario1 = horario1;
	}

	public boolean isHorario2() {
		return horario2;
	}

	public void setHorario2(boolean horario2) {
		this.horario2 = horario2;
	}	

}
