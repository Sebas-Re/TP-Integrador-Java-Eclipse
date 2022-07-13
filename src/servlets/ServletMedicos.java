package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;

/**
 * Servlet implementation class ServletMedicos
 */
@WebServlet("/ServletMedicos")
public class ServletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMedicos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Boolean filas = false;
    	
    	if(request.getParameter("Param=1")!=null) {
			try {
			MedicoNegocioImpl neg = new MedicoNegocioImpl();
			ArrayList<Turnos> lista= neg.listarTurnos();
			
			request.setAttribute("ListaT", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("TurnosMedico.jsp");
			rd.forward(request, response);	
			}
		catch (Exception e) {
				// TODO: handle exception
			}
					
		}
		
		if(request.getParameter("ListarMedicos")!=null) {
			
			try {
				MedicoNegocioImpl neg = new MedicoNegocioImpl();
				ArrayList<Medicos> lista= neg.listarMedicos();
				
				request.setAttribute("ListaM", lista);
				RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
				rd.forward(request, response);	
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		

		
		
			if(request.getParameter("btnFiltrar")!=null) {
				Medicos M = new Medicos();
				MedicoNegocioImpl neg = new MedicoNegocioImpl();
				
				if(!request.getParameter("txtFiltrarNombre").equals("")) {
					M.setNombre_m(request.getParameter("txtFiltrarNombre"));
				}
				else M.setNombre_m("");
				switch (request.getParameter("FiltarEstado")) {
				case "0":
					M.setEstado(0);
					break;
				case "1":
					M.setEstado(1);
					break;
				case "2":
					M.setEstado(2);
					break;
				default:
					break;
				}
				
				
				ArrayList<Medicos> lista= neg.FiltrarNombreEstado(M); 
				
				request.setAttribute("ListaM", lista);
				RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
				rd.forward(request, response);	
			}
			
			if(request.getParameter("BtnModificar")!=null)
				
			{
				Medicos m = new Medicos();
				MedicoNegocioImpl neg = new MedicoNegocioImpl();	
				System.out.println(request.getParameter("Dni"));
				m.setDni_m(request.getParameter("Dni"));
				m.setNombre_m(request.getParameter("Nombre"));
				m.setApellido_m(request.getParameter("Apellido"));
				m.setSexo_m(request.getParameter("Sexo"));
				m.setNacionalidad(request.getParameter("Nacionalidad"));
				
				m.setFechaNac_m(request.getParameter("FechaDeNacimiento"));
				m.setDireccion_m(request.getParameter("Direccion"));
				m.setLocalidad_m(request.getParameter("Localidad"));
				m.setProvincia_m(request.getParameter("Provincia"));
				m.setCorreoElectronico_m(request.getParameter("Mail"));
				m.setTelefono_m(request.getParameter("Telefono"));
				m.setEspecialidad(request.getParameter("Especialidad"));
				
				m.setEstado(Integer.parseInt(request.getParameter("Estado")));

				filas = neg.modificarMedico(m);
				
				ArrayList<Medicos> lista= neg.listarMedicos(); 
				
				request.setAttribute("ListaM", lista);
				request.setAttribute("ModificacionEsM", filas);
				RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
				rd.forward(request, response);
			}
			
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Agregado = 0;
		
		if(request.getParameter("BtnAgregar")!=null)
		{
		request.setAttribute("ContraInvalida", null);
		request.setAttribute("txtEmail",null);
		Usuario Us = new Usuario();
		
		Us.setNombreUsuario(request.getParameter("txtNombreUs"));
		String Contra1=request.getParameter("txtContra").toString();
		String Contra2=request.getParameter("txtContraConfirm").toString();
		if(Contra1.equals(Contra2) && Contra1 != "" && Contra2 != "") {
			Us.setContraseñaUsuario(request.getParameter("txtContra"));
		}
		else {
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1&ContraInvalida=true");
		return;
		}
		
		if(request.getParameter("txtEmail") == null) {
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1&EmailVacio=true");
			return;
		}
		 
		Us.setTipoUsuario(0);
		
		Medicos med = new Medicos();
		
		med.setDni_m(request.getParameter("txtDni"));
		med.setNombre_m(request.getParameter("txtNombreMed"));
		med.setApellido_m(request.getParameter("txtApellido"));
		med.setSexo_m(request.getParameter("txtSexo"));
		med.setFechaNac_m(request.getParameter("txtFechaNac"));
		med.setDireccion_m(request.getParameter("txtDirec"));
		med.setLocalidad_m(request.getParameter("txtLocalidad"));
		med.setProvincia_m(request.getParameter("txtProv"));
		med.setCorreoElectronico_m(request.getParameter("txtEmail"));
		med.setTelefono_m(request.getParameter("txtTel"));
		med.setEspecialidad(request.getParameter("txtEsp"));
		med.setDia(request.getParameter("txtDia"));
		med.setHorarioAtencion(request.getParameter("txtHorarioAtencion"));
		med.setEstado(1);
		
		MedicoNegocioImpl negMed = new MedicoNegocioImpl();
		
		Agregado = negMed.agregarMedico(Us, med); //Si devuelve 1, sólo uno de los dos se añadió. Si devuelve 2, todo se agregó correctamente.
		}
		
		request.setAttribute("MedicoAgregado", Agregado);
		RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
		rd.forward(request, response);
		
	}

}
