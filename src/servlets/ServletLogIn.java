package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daolmpl.DaoUsuarioImpl;
import entidad.Usuario;


/**
 * Servlet implementation class ServletLogIn
 */
@WebServlet("/ServletLogIn")
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnLogIn")!=null) {
			/*
		La funcion crearia un objeto de tipo DaoClinicaImpl, el cual instanciaria una conexion a la base de datos y buscaria aquel usuario
		cuyo usuario y contraseña fuera el mismo que el ingresado. En caso de que se encuentre, devolveria el tipo de usuario:
		0 si es administrador (redirige a UsAdmOP.jsp)
		1 si es medico [Redirige a UsuarioMedico.jsp (Si quieren, cambiar nombre pagina a UsMed.jsp para mayor consistencia en nombres)]
		-1 si no existe. (Redirige a LogIn.jsp)
		
	    El resultado de esta funcion estaría decidido a partir de un switch(TipoUsuario), el cual redirigiria a la pagina correspondiente
	    dependiendo del tipo de usuario devuelto por la BD
	
	       */
			
		DaoUsuarioImpl DaoUsuario = new DaoUsuarioImpl();
		Usuario us = DaoUsuario.validarUsuario(request.getParameter("txtLogInUsuario"), request.getParameter("txtLogInPass"));
		
		request.setAttribute("TipoUsuario", us.getTipoUsuario());
		HttpSession session = request.getSession();	
		session.setAttribute("DatosUsuario", us);
		
		switch (us.getTipoUsuario()) {
		case 1: //Administrador
			RequestDispatcher rd_admin = request.getRequestDispatcher("UsuarioAdm.jsp");
			rd_admin.forward(request, response);
			break;
		case 0: //Medico
			RequestDispatcher rd_medico = request.getRequestDispatcher("UsuarioMedico.jsp");
			rd_medico.forward(request, response);
			break;
		case -1: // No registrado en el sistema
			RequestDispatcher rd_LogIn = request.getRequestDispatcher("LogIn.jsp");
			rd_LogIn.forward(request, response);
			break;
		}
		
			
		
		}
		
	}

}
