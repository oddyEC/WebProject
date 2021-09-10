package modelo.dao;

import java.util.List;

import modelo.entidad.Docente;
import modelo.entidad.Estudiante;
import modelo.entidad.Tutoria;

public interface TutoriaDAO extends GenericDAO<Tutoria, Integer> {
	public List<Tutoria> listarPorDocente(Docente d);
	public List<Tutoria> listarPorEstudiante(Estudiante e);
}
