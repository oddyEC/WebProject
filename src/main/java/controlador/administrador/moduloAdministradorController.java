package controlador.administrador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidad.Departamento;
import modelo.jpa.JPAFactory;

@WebServlet("/moduloAdministradorController")
public class moduloAdministradorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory fabrica = new JPAFactory();
		List<Departamento> listaDptos = fabrica.crearDepartamentoDAO().listar();
		request.setAttribute("listaDptos", listaDptos);
		getServletContext().getRequestDispatcher("/añadirDocente.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	}
}
