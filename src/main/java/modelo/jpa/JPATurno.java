package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.TurnoDAO;
import modelo.entidad.Docente;
import modelo.entidad.Turno;

public class JPATurno extends JPAGenericDAO<Turno, Integer> implements TurnoDAO {
	public JPATurno() {
		super(Turno.class);
		
	}

	@Override
	public List<Turno> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Turno> listarAsociados(Docente d){
		Query q = em.createNamedQuery("listarTurnos", Turno.class);
		q.setParameter("docente", d);
		List<Turno> turnos = (List<Turno>) q.getResultList();
		if (turnos.size() !=0) {
			return turnos;
		}
		return null;
	}

}
