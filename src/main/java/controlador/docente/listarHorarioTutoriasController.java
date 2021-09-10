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
import modelo.jpa.JPAFactory;

/**
 * Servlet implementation class listarHorarioTutoriasController
 */
@WebServlet("/listarHorarioTutoriasController")
public class listarHorarioTutoriasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public listarHorarioTutoriasController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leerHorarios(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void leerHorarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String cedula = (String)req.getSession().getAttribute("usuario");
		DAOFactory fabrica = new JPAFactory();
		Docente profesor = (Docente)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		List<Turno> turnos = fabrica.crearTurnoDAO().listarAsociados(profesor);
		req.setAttribute("listaTurnos", turnos);
		getServletContext().getRequestDispatcher("/listarHorarioTutorias.jsp").forward(req, resp);
	}

}
