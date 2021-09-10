package controlador.docente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidad.Departamento;
import modelo.entidad.Docente;
import modelo.entidad.Estudiante;
import modelo.jpa.JPAFactory;

@WebServlet("/visualizarDatosDocenteController")
public class visualizarDatosDocenteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public visualizarDatosDocenteController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String cedula = (String) sesion.getAttribute("usuario");
		DAOFactory fabricaC = new JPAFactory();
		Docente docente = (Docente) fabricaC.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		request.setAttribute("docente", docente);
		getServletContext().getRequestDispatcher("/visualizarDocente.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
