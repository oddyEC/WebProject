package controlador.administrador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidad.Estudiante;
import modelo.jpa.JPAFactory;

/**
 * Servlet implementation class listarEstudianteController
 */
@WebServlet("/listarEstudianteController")
public class listarEstudianteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarEstudianteController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aBuscar = request.getParameter("search");
		List<Estudiante> nomina;
		if(aBuscar == null) {
			nomina = listar();
		}else {
			nomina = buscar(aBuscar);
		}
		request.setAttribute("nomina", nomina);
		getServletContext().getRequestDispatcher("/listaEstudiantes.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aBorrar = request.getParameter("del");
		eliminar(aBorrar);
		response.sendRedirect("listarEstudianteController");
	}
	
	private List<Estudiante> listar(){
		DAOFactory fabrica = new JPAFactory();
		return fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).listar();
	}
	
	private List<Estudiante> buscar(String cedula){
		DAOFactory fabrica = new JPAFactory();
		Estudiante buscado = (Estudiante) fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).leer(cedula);
		List<Estudiante> lista = new ArrayList<Estudiante>();
		if(buscado != null) {
			lista.add(buscado);
			return lista;
		}
		return null;
	}

	private void eliminar(String cedula) {
		DAOFactory fabrica = new JPAFactory();
		fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).eliminarPorId(cedula);
	}

}
