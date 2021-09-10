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
import modelo.entidad.Departamento;
import modelo.entidad.Docente;
import modelo.jpa.JPAFactory;


@WebServlet("/listarDocenteController")
public class listarDocenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public listarDocenteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aBuscar = request.getParameter("search");
		List<Docente> nomina;
		if (aBuscar == null) {
			nomina = this.listar();
		} else {
			nomina = buscar(aBuscar);
		}
		request.setAttribute("nomina", nomina);
		getServletContext().getRequestDispatcher("/listaDocentes.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("del") != null) {
			String aBorrar = request.getParameter("del");
			eliminar(aBorrar);
			response.sendRedirect("listarDocenteController");
		}
		if (request.getParameter("nuevo") != null) {
			DAOFactory fabrica = new JPAFactory();
			List<Departamento> listaDptos = fabrica.crearDepartamentoDAO().listar();
			request.setAttribute("listaDptos", listaDptos);
			getServletContext().getRequestDispatcher("/añadirDocente.jsp").forward(request, response);
		}
	}
	
	private List<Docente> listar(){
		DAOFactory fabrica = new JPAFactory();
		return (List<Docente>)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).listar();
	}

	private List<Docente> buscar(String cedula) {
		DAOFactory fabrica = new JPAFactory();
		List<Docente> lista = new ArrayList<Docente>();
		Docente encontrado = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		if(encontrado != null) {
			lista.add(encontrado);
			return lista;
		}
		return null;
	}

	private void eliminar(String cedula) {
		DAOFactory fabrica = new JPAFactory();
		fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).eliminarPorId(cedula);
	}
}
