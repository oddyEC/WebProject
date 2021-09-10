package modelo.dao;

import modelo.entidad.Departamento;

public interface DepartamentoDAO extends GenericDAO<Departamento, Integer> {
	public Departamento leerByNombre(String name);
}
