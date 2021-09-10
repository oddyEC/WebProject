package controlador.docente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidad.Docente;
import modelo.entidad.Turno;
import modelo.entidad.Tutoria;
import modelo.jpa.JPAFactory;

/**
 * Servlet implementation class listarTutoriasDocenteController
 */
@WebServlet("/listarTutoriasDocenteController")
public class listarTutoriasDocenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public listarTutoriasDocenteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leerTutorias(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void leerTutorias(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String cedula = (String)req.getSession().getAttribute("usuario");
		DAOFactory fabrica = new JPAFactory();
		Docente profesor = (Docente)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		List<Tutoria> tutorias = fabrica.crearTutoriaDAO().listarPorDocente(profesor);
		req.setAttribute("listaTutorias", tutorias);
		getServletContext().getRequestDispatcher("/listarTutoriasDocente.jsp").forward(req, resp);
	}

}
