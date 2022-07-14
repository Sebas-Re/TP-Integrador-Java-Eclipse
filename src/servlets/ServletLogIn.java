package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import daolmpl.DaoUsuarioImpl;
import entidad.Usuario;
import negocioImpl.NegocioTurnosImpl;


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
		if(request.getParameter("CerrarSesion") != null)
		{
			HttpSession session = request.getSession();	
			session.setAttribute("DatosUsuario", null);
			request.setAttribute("SessionCerrada", true);
			RequestDispatcher rd_LogIn = request.getRequestDispatcher("LogIn.jsp");
			rd_LogIn.forward(request, response);
		}
		
		
		if(request.getParameter("SessionVencida") != null)
		{
			request.setAttribute("SessionVencida", true);
			RequestDispatcher rd_LogIn = request.getRequestDispatcher("LogIn.jsp");
			rd_LogIn.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnLogIn")!=null) {

		NegocioTurnosImpl neg = new NegocioTurnosImpl();	
		Usuario us = neg.TraerUsuario(request.getParameter("txtLogInUsuario").toString(), request.getParameter("txtLogInPass").toString());		
		
		
		
		request.setAttribute("TipoUsuario", us.getTipoUsuario());
		HttpSession session = request.getSession();	
		session.setAttribute("DatosUsuario", us);
		
		switch (us.getTipoUsuario()) {
		case 1: //Administrador
			
			response.sendRedirect(request.getContextPath() + "/ServletReportes?" + "MostrarReportes=1");
			break;
		case 0: //Medico
			us = neg.TraerDNIMedico(us);
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "UserMedico=1");
			break;
		case -1: // No registrado en el sistema
			
			request.setAttribute("SessionVencida", null);
			request.setAttribute("SessionCerrada", null);
			RequestDispatcher rd_LogIn = request.getRequestDispatcher("LogIn.jsp");
			rd_LogIn.forward(request, response);
			break;
		}
		
			
		
		}
		
	}



}
