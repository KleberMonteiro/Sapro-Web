package br.com.saproweb.utils.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.saproweb.sistema.dominio.entidades.Disciplina;

public class DisciplinaDataModel extends ListDataModel<Disciplina> implements
		SelectableDataModel<Disciplina> {

	public DisciplinaDataModel() {
	}

	public DisciplinaDataModel(List<Disciplina> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Disciplina getRowData(String rowKey) {

		List<Disciplina> disciplinas = (List<Disciplina>) getWrappedData();

		for (Disciplina disciplina : disciplinas) {
			if (disciplina.getNome().equals(rowKey))
				return disciplina;
		}

		return null;
	}

	@Override
	public Object getRowKey(Disciplina disciplina) {
		return disciplina.getNome();
	}

}
