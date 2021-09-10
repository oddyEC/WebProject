package controlador.docente;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/actualizarTurnoController")
public class actualizarTurnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public actualizarTurnoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cedulaDocente = (String) request.getSession().getAttribute("usuario");
		DAOFactory fabrica = new JPAFactory();
		Docente docente = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedulaDocente);
		
		List<Turno> turnosDocente = fabrica.crearTurnoDAO().listarAsociados(docente);
		List<String> turnos = new ArrayList<String>();
		if (turnosDocente != null) {
			for (Turno t: turnosDocente) {
				String[] datos = t.getHoraInicio().split(":");
				turnos.add(t.getDia() + "-" + datos[0] + "-" + datos[1]);
			}
		} else {
			//////////////////////////
		}
		request.setAttribute("turnos", turnos);
		getServletContext().getRequestDispatcher("/actualizarTurno.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cedulaDocente = (String) request.getSession().getAttribute("usuario");
		DAOFactory fabrica = new JPAFactory();
		Docente docente = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedulaDocente);
		
		List<Turno> turnosDocente = fabrica.crearTurnoDAO().listarAsociados(docente);
		if (turnosDocente != null) {
			for (Turno t: turnosDocente) {
				fabrica.crearTurnoDAO().eliminar(t);
			}
		}
		
		// Esto sera de hacer xd???
		//docente.setTurnos(new ArrayList<Turno>()); // Vaciar la lista del docente
		
		String[] turnos = request.getParameterValues("turno");
		if (turnos != null) {
			for (String turno: turnos) {
				String[] datos = turno.split("-");
				String horaInicio = datos[1] + ":" + datos[2];
				Turno t = new Turno(datos[0], horaInicio, docente);
				fabrica.crearTurnoDAO().crear(t);
			}
		} else {
			// Mensaje de "Seleccione algo si quiera xd"
		}
		getServletContext().getRequestDispatcher("/listarHorarioTutoriasController").forward(request, response);
	}

}
