package controlador.estudiante;

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
import modelo.jpa.JPAGenericDAO;

/**
 * Servlet implementation class anadirTurnoController
 */
@WebServlet("/anadirTurnoController")
public class anadirTurnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public anadirTurnoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}
	
	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dia = req.getParameter("diaTurno");
		String hora = req.getParameter("hTurno");
		String cuarto = req.getParameter("cuartoHTurno");
		String horaInicio = hora+":";
		switch (cuarto) {
		case "q1":
			horaInicio +="00";
			break;
		case "q2":
			horaInicio +="15";
			break;
		case "q3":
			horaInicio +="30";
			break;
		case "q4":
			horaInicio +="45";
			break;
		}
		DAOFactory fabrica = new JPAFactory();
		String cedula = (String) req.getSession().getAttribute("usuario");
		Docente profe = (Docente)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		List<Turno> listaTurnos = fabrica.crearTurnoDAO().listarAsociados(profe);
		Turno turn = new Turno(dia,horaInicio,profe);
		boolean flag = false;
		if(listaTurnos != null) {
			for(Turno t:listaTurnos) {
				if ((t.getDia().equals(turn.getDia())) && (t.getHoraInicio().equals(turn.getHoraInicio())) ) {
					flag = true;
					break;
				}
			}
		}
		req.setAttribute("result", !flag);
		if (!flag){
			fabrica.crearTurnoDAO().crear(turn);
		}
		getServletContext().getRequestDispatcher("/anadirTurno.jsp").forward(req, resp);
		
	}

}
