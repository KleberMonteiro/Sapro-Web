package br.com.saproweb.utils;

import java.io.Serializable;

import br.com.saproweb.sistema.dominio.entidades.Dia;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.sistema.dominio.entidades.QuadroDeHorarios;
import br.com.saproweb.sistema.dominio.entidades.Semana;
import br.com.saproweb.sistema.dominio.entidades.Turno;

public class GeradorProfessor implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	public static Professor gerar(){		
		Professor professor = new Professor();		
		QuadroDeHorarios quadroDeHorarios = new QuadroDeHorarios();
		
		quadroDeHorarios.setSemana(gerarSemana());
		professor.setQuadroDeHorarios(quadroDeHorarios);
		
		return professor;				
	}
	
	private static Dia gerarDia() {		
		Dia dia = new Dia();		
		Turno turno = new Turno();		
		
		dia.setManha(turno);
		
		turno = new Turno();		
		dia.setTarde(turno);
		
		turno = new Turno();		
		dia.setNoite(turno);
		
		return dia;
	}
	
	private static Semana gerarSemana() {
		Semana semana = new Semana();		
		
		semana.setDomingo(gerarDia());		
		semana.setSegunda(gerarDia());
		semana.setTerca(gerarDia());
		semana.setQuarta(gerarDia());
		semana.setQuinta(gerarDia());
		semana.setSexta(gerarDia());
		semana.setSabado(gerarDia());
		
		return semana;
	}

}
