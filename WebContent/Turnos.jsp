<%@page import="entidad.Medicos"%>
<%@page import="entidad.Pacientes"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos</title>

<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" />
  
</head>
<body>
<div id="formulario" align="center">

<script languaje="javascript">
function ir(){

window.location.href = "http://localhost:8080/TPINT_GRUPO_1_LAB4/ServletTurnos?AgregarMedicos=2";
}
</script>

<form name="formRegistro" action="" method="post">

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


	<form method="post" action="ServletTurnos">
			<table>
			
		<tr>
				<td>DNI del <br>Paciente</td>
				
						<%!ArrayList<Pacientes> listarPacientes=null; %>
						<% 
						if( request.getAttribute("DNIP")!=null){
						
						listarPacientes = (ArrayList<Pacientes>)request.getAttribute("DNIP");
						
						   for(Pacientes pcte : listarPacientes) 
   		{
						       
						%> 
						    <td><input type="hidden" name="dni_Paciente" value="<%=pcte.getDNI() %>"><%=pcte.getDNI() %></td>
						<%
							}
						}
						
						%>
					
				
			</tr>
			<tr>
			<form method="post" action="ServletTurnos">
				<td>Medico</td>
				<td>
					<select name="Medico">
					<%!ArrayList<Medicos> listaMedicos; %>
						<% 
						if( request.getAttribute("ListaM")!=null){
						listaMedicos = (ArrayList<Medicos>)request.getAttribute("ListaM");
						
						  for(Medicos med : listaMedicos) 
   							{
   							if(med.getEstado()==1){
						       String fdatos = med.getDni_m()+" - "+med.getNombre_m()+" "+med.getApellido_m()+" - "+med.getEspecialidad()+" - "+med.getDia(); 
						%> 
						    <option value="<%=fdatos%>"><%=fdatos%></option> 
						<%
								}
							}
						}
						
						%>
					</select>
				</td>
				
			</tr>
			<tr>
				<td>Fecha Turno</td>
				<td>
					<input type="date" name="FechaTur" placeholder="Fecha Nac (a-m-d)" required/></p>
				</td>
			</tr>
				<tr>
				<td>Horario Inicio</td>
				<td>
					<input type="time" name="Hora_Inicio">
				</td>
			</tr>
			<tr>
				<td>Horario Fin</td>
				<td>
					<input type="time" name="Hora_Fin">
				</td>
			</tr>
		</table>
	
		<br>
				<td><input type="submit" name="btnAgregarTurno" value="Agregar Turno"></td>	
				</form>
		</form>
		</div>
		
</body>
</html>