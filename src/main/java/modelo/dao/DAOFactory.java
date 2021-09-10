package modelo.dao;

public abstract class DAOFactory {
	
	public abstract UsuarioDAO crearUsuarioDAO(String tipo);
	public abstract DepartamentoDAO crearDepartamentoDAO();
	public abstract TurnoDAO crearTurnoDAO();
	public abstract TutoriaDAO crearTutoriaDAO();

}
