<%@page import="negocioImpl.MedicoNegocioImpl"%>
<%@page import="jdk.nashorn.internal.runtime.ListAdapter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turnos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos Medicos</title>


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">

  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" />
  
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

	
</head>
<body>
<form method="post" action="ServletTurnos">

<%
try{
Usuario UsuarioLogeado = new Usuario();
UsuarioLogeado = (Usuario) session.getAttribute("DatosUsuario");
String MensajeBienvenida = "Bienvenido "+UsuarioLogeado.getNombreUsuario();

%>
<div>

	<ul class="" id="Navegacion">
	<li class="nav__Usuario"><a class="nav__li-a" href="ServletLogIn?CerrarSesion=1">Cerrar Sesion</a></li>
	  <li class="nav__li"><%if(UsuarioLogeado.getTipoUsuario() == 1){ %><a class="nav__li-a" href="ServletReportes?MostrarReportes=1">Home</a><%} %></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServletMedicos?ListarMedicos=1">Medicos</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServeletPaciente?ListarPacientes=1">Pacientes</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServletTurnos?ListaTurnos=1">ListarTurnos</a></li>
	   <li class="nav__li"><%if(UsuarioLogeado.getTipoUsuario() == 1){ %><a class="nav__li-a" href="Reportes.jsp">Reportes</a><%} %></li>
	 <li class="nav__Usuario"><%=MensajeBienvenida%></li>
	 
	</ul>
</div>

<%
}catch(NullPointerException e){
response.sendRedirect(request.getContextPath() + "/ServletLogIn?" + "SessionVencida=1");

}
%>

<%
    String agregadoEs = null;
	if(request.getAttribute("AgregadoEs") != null){
		agregadoEs = request.getAttribute("AgregadoEs").toString();
			if(agregadoEs == "true")
			{
				agregadoEs = "Turno agregado exitosamente.";
			}
			else {
				agregadoEs = "El Turno no pudo ser agregado.";
			}
			%>
			<%=agregadoEs%>
			<%
		}
	%>
<h3>Listar turnos</h3>


<%! 
Turnos turnos = new Turnos();
ArrayList<Turnos> ListaTurnos = new ArrayList<Turnos>();
%>

<%	
	if(request.getAttribute("ListaT")!=null)
	{	
		ListaTurnos =  (ArrayList<Turnos>)request.getAttribute("ListaT");
	} %>
	

	
	
<table	 id="ListaTurno" width="100%" class="table table-hover table-bordered">
	<thead>
	<tr>
		<th width="25px">codigo turno</th>
		<th width="25px">Nombre del medico</th>
		<th width="25px" style="width: 76px; ">DNI del paciente</th>
		<th width="25px">Nombre del paciente</th>
		<th width="25px">Sexo del paciente</th>
		<th width="25px">Direccion del paciente</th>
		<th width="25px">Fecha de nacimiento del paciente</th>
		<th width="25px">Telefono del paciente</th>
		<th width="25px">Correo del paciente</th>
		<th width="25px" style="width: 67px; ">Dia del turno</th>
		<th width="25px">Horario</th>
		<th width="25px">Especialidad</th>
		<th width="25px">Observaciones</th>
		<th width="25px">Estado del turno</th>
		<th width="25px">Modificar</th>
	</tr>
	</thead>
	<%if(ListaTurnos!=null)
		for(Turnos tur: ListaTurnos){
			String Estado = "null";
   			switch(tur.getEstado_Turno())
   			{
   			case "LIBRE": Estado = "LIBRE";
   				break;
   			case "OCUPADO": Estado = "OCUPADO";
   				break;
   			case "AUSENTE": Estado = "AUSENTE";
   				break;
   			case "PRESENTE": Estado = "PRESENTE";
   				break;
   			} 
		%>
	<tr> 	 
		<form method="post" action="ServletTurnos">
				<td><input readonly="" name="Cod_Turno" value="<%=tur.getCod_Turno() %>"></td>	
				<td><%=tur.getMedico_Nombre() %></td>
				<td><%=tur.getDNI_Paciente_Turno() %><input type="hidden" name="DNI_Paciente" value="<%=tur.getDNI_Paciente_Turno()%>" /></td> 
				<td><%=tur.getNombre_Paciente() %></td>
				<td><%=tur.getSexo_Paciente() %></td>
				<td><%=tur.getDireccion_Paciente()%></td>
				<td><%=tur.getNac_Paciente() %></td>
				<td><%=tur.getTelefono_Paciente() %></td>
				<td><%=tur.getCorreo_Paciente()%></td>
				<td><%=tur.getDia_Turno() %></td>
				<td><%=tur.getInicio_Turno() %></td>
				<td><%=tur.getEspecialidad_Turno() %></td>
				<td><input type="text" name="Observaciones" value="<%=tur.getObservacines()%>" /></td> 
				<%
   					if(Estado == "LIBRE")
   					{
   				%>
   					<td>
   						<select name="Estado">
   						  <option value="LIBRE" selected>Libre</option>
   						  <option value="OCUPADO">Ocupado</option>
   						</select>
   					</td>
   				<%
   				}
   				else if(Estado == "OCUPADO")
   					{
   				%>
   					<td>
   					<select name="Estado">
   						  <option value="LIBRE">Libre</option>
   						  <option value="OCUPADO" selected>Ocupado</option>
   						</select>
   					</td>
   				<%
   					}
   				else{
   				%>
   				<td>
   					<select name="Estado">
   						  <option value="<%=tur.getEstado_Turno() %>" selected><%=tur.getEstado_Turno() %></option>
   						  <option value="LIBRE">Libre</option>
   						  <option value="OCUPADO">Ocupado</option>
   					</select>
   				</td>
   				<%
   					}
   				%>
				<td><input type="submit" name="btnModificarAdmin" value="Modificar"></td>
				
				
		</form>
		</tr>
			
		<% } %>
</table>
</form>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>

<script type="text/javascript">

    //IDIOMA ESPAÑOL DEL DATATABLE 

    $(document).ready(function() {

      $('#ListaTurno').DataTable({

        "language": {

          "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"

        }

      });

    });

  </script>
</body>
</html>