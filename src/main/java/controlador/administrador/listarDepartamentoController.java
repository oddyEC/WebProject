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
import modelo.jpa.JPAFactory;

@WebServlet("/listarDepartamentoController")
public class listarDepartamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarDepartamentoController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aBuscar = request.getParameter("search");
		List<Departamento> dptos;
		if(aBuscar == null) {
			dptos = this.listar();
		} else {
			dptos = buscar(aBuscar);
		}
		request.setAttribute("dptos", dptos);
		getServletContext().getRequestDispatcher("/listaDepartamentos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("del") != null) {
			Integer aBorrar = Integer.parseInt(request.getParameter("del"));
			eliminar(aBorrar);
			response.sendRedirect("listarDepartamentoController");
		}
		if (request.getParameter("edit") != null) {
			Integer idEditar = Integer.parseInt(request.getParameter("edit"));
			request.setAttribute("idEditar", idEditar);
			DAOFactory fabrica = new JPAFactory();
			Departamento dpto = fabrica.crearDepartamentoDAO().leer(idEditar);
			String nombreEditar = dpto.getNombre(); 
			request.setAttribute("nombreEditar", nombreEditar);
			getServletContext().getRequestDispatcher("/actualizarDepartamento.jsp").forward(request, response);
		}
	}
	private List<Departamento> listar(){
		DAOFactory fabrica = new JPAFactory();
		return fabrica.crearDepartamentoDAO().listar();
	}
	private List<Departamento> buscar(String nombre){
		DAOFactory fabrica = new JPAFactory();
		List<Departamento> deptos = new ArrayList<Departamento>();
		Departamento encontrado = fabrica.crearDepartamentoDAO().leerByNombre(nombre);
		if(encontrado != null) {
			deptos.add(encontrado);
			return deptos;
		}
		return null;
	}
	private void eliminar(Integer id) {
		DAOFactory fabrica = new JPAFactory();
		fabrica.crearDepartamentoDAO().eliminarPorId(id);
	}
}
