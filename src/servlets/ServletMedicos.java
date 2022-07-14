package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Localidad;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Pais;
import entidad.Provincia;
import entidad.Turnos;
import entidad.Usuario;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
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
				
				m.setCodHorarioMedico(Integer.parseInt(request.getParameter("CodHorarioMODIFICAR")));
				m.setEstado(Integer.parseInt(request.getParameter("Estado")));

				filas = neg.modificarMedico(m);
				
				ArrayList<Medicos> lista= neg.listarMedicos(); 
				
				request.setAttribute("ListaM", lista);
				request.setAttribute("ModificacionEsM", filas);
				RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
				rd.forward(request, response);
			}
			
			
			if(request.getParameter("UserMedico")!=null) {
				
				try{
			
				RequestDispatcher rd = request.getRequestDispatcher("UsuarioMedico.jsp");
				rd.forward(request, response);
				}
				catch (Exception e) {
					
				}
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
	
		 
		Us.setTipoUsuario(0);
		
		Medicos med = new Medicos();
		
		med.setDni_m(request.getParameter("txtDni"));
		med.setNombre_m(request.getParameter("txtNombre"));
		med.setApellido_m(request.getParameter("txtApellido"));
		med.setFechaNac_m(request.getParameter("FechaNac"));
		med.setNacionalidad(request.getParameter("Nacionalidad"));
		med.setDireccion_m(request.getParameter("Direccion"));
		med.setLocalidad_m(request.getParameter("Localidad"));
		med.setProvincia_m(request.getParameter("Provincia"));
		med.setCorreoElectronico_m(request.getParameter("Mail"));
		med.setTelefono_m(request.getParameter("Telefono"));
		med.setEspecialidad(request.getParameter("Especialidad"));
		med.setSexo_m(request.getParameter("ddlSexo"));
		med.setCodHorarioMedico(Integer.parseInt(request.getParameter("codHorarioMedico")));
	//	med.setDia(request.getParameter("txtDia"));
	//	med.setHorarioAtencion(request.getParameter("txtHorarioAtencion"));
		med.setEstado(1);
		
		MedicoNegocioImpl negMed = new MedicoNegocioImpl();
		
		Agregado = negMed.agregarMedico(Us, med); //Si devuelve 1, sólo uno de los dos se añadió. Si devuelve 2, todo se agregó correctamente.
		}
		if(Agregado == 2) {
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1&MedicoAgregado=true");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1&MedicoAgregado=false");
		}
		
	}

}
