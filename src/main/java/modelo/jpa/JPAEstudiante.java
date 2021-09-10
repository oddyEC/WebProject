package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.UsuarioDAO;
import modelo.entidad.Estudiante;



public class JPAEstudiante extends JPAGenericDAO<Estudiante, String> implements UsuarioDAO<Estudiante, String> {
	
	public JPAEstudiante() {
		super(Estudiante.class);
		
	}
	@Override
	public Estudiante autorizar(String cedula, String contraseña ) {
		Estudiante estudianteEntidad = this.leer(cedula);
		if (estudianteEntidad != null) {
			if (estudianteEntidad.getContraseña().equals(contraseña))
				return estudianteEntidad;
		}
		return null;

	}
	@Override
	public List<Estudiante> listar() {
		Query query= em.createNamedQuery("listarEstudiantes",Estudiante.class);
		List<Estudiante> estudiantes =(List<Estudiante>) query.getResultList();
		if (estudiantes.size() != 0) {
			return estudiantes;
		}
		return null;
	}

	
}
