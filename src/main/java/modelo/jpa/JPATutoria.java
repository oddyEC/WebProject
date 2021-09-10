package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.TutoriaDAO;
import modelo.entidad.Docente;
import modelo.entidad.Estudiante;
import modelo.entidad.Tutoria;

public class JPATutoria extends JPAGenericDAO<Tutoria, Integer> implements TutoriaDAO {
	public JPATutoria() {
		super(Tutoria.class);
		
	}

	@Override
	public List<Tutoria> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tutoria> listarPorDocente(Docente d) {
		Query consulta = em.createNamedQuery("obtenerTutoriasDocente", Tutoria.class);
		consulta.setParameter("docente", d);
		List<Tutoria> tutorias = (List<Tutoria>) consulta.getResultList();
		
		if(tutorias.size() !=0) {
			return tutorias;
		}
		return null;
	}

	@Override
	public List<Tutoria> listarPorEstudiante(Estudiante e) {
		Query consulta = em.createNamedQuery("obtenerTutoriasEstudiante", Tutoria.class);
		consulta.setParameter("estudiante", e);
		List<Tutoria> tutorias = (List<Tutoria>) consulta.getResultList();
		
		if(tutorias.size() !=0) {
			return tutorias;
		}
		return null;
	}
	
	
}
