package modelo.dao;

import java.util.List;

import modelo.entidad.Docente;
import modelo.entidad.Turno;

public interface TurnoDAO extends GenericDAO<Turno, Integer> {
	public List<Turno> listarAsociados(Docente d);
}
