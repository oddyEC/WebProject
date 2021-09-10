package modelo.jpa;

import modelo.dao.DAOFactory;
import modelo.dao.DepartamentoDAO;
import modelo.dao.TurnoDAO;
import modelo.dao.TutoriaDAO;
import modelo.dao.UsuarioDAO;

public class JPAFactory extends DAOFactory {
	
	public static final String DOCENTE = "profesor";
	public static final String ESTUDIANTE = "estudiante";
	
	@Override
	public UsuarioDAO crearUsuarioDAO(String tipo) {
		if(tipo.equals(DOCENTE)) {
			return new JPADocente();
		}
		else if(tipo.equals(ESTUDIANTE)) {
			return new JPAEstudiante();
		}
		else {
			return null;
		}
	}

	@Override
	public DepartamentoDAO crearDepartamentoDAO() {
		return new JPADepartamento();
	}

	@Override
	public TurnoDAO crearTurnoDAO() {
	
		return new JPATurno();
	}

	@Override
	public TutoriaDAO crearTutoriaDAO() {
		
		return new JPATutoria();
	}

}
