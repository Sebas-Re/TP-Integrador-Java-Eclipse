package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;

import daolmpl.DaoPacienteImpl;
import daolmpl.DaoUsuarioImpl;
import entidad.Localidad;
import entidad.Pacientes;
import entidad.Pais;
import entidad.Provincia;
import entidad.Turnos;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.PacienteNegocioImpl;

/**
 * Servlet implementation class ServeletPaciente
 */
@WebServlet("/ServeletPaciente")
public class ServeletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean filas = false;

		
		if(request.getParameter("ListarPacientes")!=null) {

			try {
				NacionalidadNegocioImpl negNac = new NacionalidadNegocioImpl();
				
				ArrayList<Pais> listaPaises=null;
				listaPaises = negNac.traerPaises();
				
				ArrayList<Provincia> listaProvincias=null;
				listaProvincias = negNac.traerProvincias();
				
				ArrayList<Localidad> listaLocalidades=null;
				listaLocalidades = negNac.traerLocalidades();
				
				request.setAttribute("listaPaises", listaPaises);
				request.setAttribute("listaProvincias", listaProvincias);
				request.setAttribute("listaLocalidades", listaLocalidades);
				
				PacienteNegocioImpl neg = new PacienteNegocioImpl();
				
				ArrayList<Pacientes> lista= neg.listarPaciente(); 
				
				request.setAttribute("ListaP", lista);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Pacientes.jsp");
			rd.forward(request, response);	
		}
		
		if(request.getParameter("btnFiltrar")!=null) {
			Pacientes p = new Pacientes();
			PacienteNegocioImpl neg = new PacienteNegocioImpl();
			
			if(!request.getParameter("txtFiltrarNombre").equals("")) {
				p.setNombre(request.getParameter("txtFiltrarNombre"));
			}
			switch (request.getParameter("FiltarEstado")) {
			case "0":
				p.setEstado(0);
				break;
			case "1":
				p.setEstado(1);
				break;
			case "2":
				p.setEstado(2);
				break;
			default:
				break;
			}
			
			
			ArrayList<Pacientes> lista= neg.FiltrarNombreEstado(p); 
			
			request.setAttribute("ListaP", lista);
			RequestDispatcher rd = request.getRequestDispatcher("Pacientes.jsp");
			rd.forward(request, response);	
		}
		
		if(request.getParameter("BtnAgregar")!=null)
		{
			Pacientes p = new Pacientes();
			PacienteNegocioImpl neg = new PacienteNegocioImpl();
			p.setDNI(request.getParameter("Dni"));
			p.setNombre(request.getParameter("Nombre"));
			p.setApellido(request.getParameter("Apellido"));
			p.setSexo(request.getParameter("Sexo"));
			p.setNacionalidad(request.getParameter("Nacionalidad"));
			
			
			p.setFechaNacimiento(request.getParameter("FechaDeNacimiento"));
			p.setDireccion(request.getParameter("Direccion"));
			p.setLocalidad(request.getParameter("Localidad"));
			p.setProvincia(request.getParameter("Provincia"));
			p.setCorreo(request.getParameter("Mail"));
			p.setTelefono(request.getParameter("Telefono"));
			p.setEstado(1);
			
			DaoPacienteImpl PacDao = new DaoPacienteImpl();
			filas = PacDao.agregarPaciente(p);
			
			ArrayList<Pacientes> lista= neg.listarPaciente();
			
			request.setAttribute("ListaP", lista);
			request.setAttribute("AgregadoEs", filas);
			RequestDispatcher rd = request.getRequestDispatcher("Pacientes.jsp");
			rd.forward(request, response);
			
		}
		
		
		if(request.getParameter("BtnModificar")!=null)
		{
			Pacientes p = new Pacientes();
			PacienteNegocioImpl neg = new PacienteNegocioImpl();	
			System.out.println(request.getParameter("Dni"));
			p.setDNI(request.getParameter("Dni"));
			p.setNombre(request.getParameter("Nombre"));
			p.setApellido(request.getParameter("Apellido"));
			p.setSexo(request.getParameter("Sexo"));
			p.setNacionalidad(request.getParameter("Nacionalidad"));
			
			p.setFechaNacimiento(request.getParameter("FechaDeNacimiento"));
			p.setDireccion(request.getParameter("Direccion"));
			p.setLocalidad(request.getParameter("Localidad"));
			p.setProvincia(request.getParameter("Provincia"));
			p.setCorreo(request.getParameter("Mail"));
			p.setTelefono(request.getParameter("Telefono"));
			
			p.setEstado(Integer.parseInt(request.getParameter("Estado")));
			
			filas = neg.modificarPaciente(p);
			
			ArrayList<Pacientes> lista= neg.listarPaciente();
			
			request.setAttribute("ListaP", lista);
			request.setAttribute("ModificacionEs", filas);
			RequestDispatcher rd = request.getRequestDispatcher("Pacientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnIniciarTurno")!=null)
		{
			PacienteNegocioImpl neg = new PacienteNegocioImpl();
			Pacientes p = new Pacientes();
			p.setDNI(request.getParameter("Dni"));
			ArrayList<Pacientes> lista = neg.listarPacientexDNI(p);
			

			HttpSession session = request.getSession();	
			session.setAttribute("SessionDNIP", p.getDNI());
			
			request.setAttribute("ListaPT", lista);
			response.sendRedirect(request.getContextPath() + "/ServletTurnos?" + "AgregarInfo=1");
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean filas = false;
		
		
		
	}

}
