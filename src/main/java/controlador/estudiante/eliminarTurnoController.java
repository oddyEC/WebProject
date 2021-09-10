package controlador.estudiante;

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

/**
 * Servlet implementation class eliminarTurnoController
 */
@WebServlet("/eliminarTurnoController")
public class eliminarTurnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public eliminarTurnoController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarInfo(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listarInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String dia = req.getParameter("dia");
		if(dia==null) {dia = "l";}
		DAOFactory fabrica = new JPAFactory();
		String cedula = (String) req.getSession().getAttribute("usuario");
		Docente profe = (Docente)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		List<Turno> listaTurnos = fabrica.crearTurnoDAO().listarAsociados(profe);
		List<String> horas=new ArrayList<String>();;
		List<String> cuartos=new ArrayList<String>();;
		if (listaTurnos != null) {
			for(Turno t:listaTurnos){
				if(t.getDia().equals(dia)) {
					 if(!horas.contains(t.getHoraInicio().split(":")[0])){
						 horas.add(t.getHoraInicio().split(":")[0]);
					 }
				}
			}
		}
		req.setAttribute("listaHoras", horas);
		getServletContext().getRequestDispatcher("/eliminarTurno.jsp").forward(req, resp);
	}
}
