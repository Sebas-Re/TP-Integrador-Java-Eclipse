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
</head>
<body>
<h3>Listar turnos del medico</h3>
<form method="get" action="ServletMedicos">


<%

Usuario UsuarioLogeado = new Usuario();
UsuarioLogeado = (Usuario) session.getAttribute("DatosUsuario");
String MensajeBienvenida = "Bienvenido "+UsuarioLogeado.getNombreUsuario();
%>
<div>

	<ul class="" id="Navegacion">
	  <li class="nav__li"><a class="nav__li-a" href="UsuarioAdm.jsp">Home</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Medicos.jsp">Medicos</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Pacientes.jsp">Pacientes</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Turnos.jsp">Turnos</a></li>
	 <li class="nav__Usuario"><%=MensajeBienvenida%></li>
	</ul>
<br>
</div>




<%! 
Turnos turnos = new Turnos();
MedicoNegocioImpl neg = new MedicoNegocioImpl();
ArrayList<Turnos> ListaTurnos = new ArrayList<Turnos>();
%>

<%	
	ListaTurnos = neg.listarTurnos();
	if(request.getAttribute("ListaT")!=null)
	{	
		ListaTurnos =  (ArrayList<Turnos>)request.getAttribute("ListaT");
	} %>
	
	
	
	
	
	
<table border="1" width="100%">
	<tr>
		<th width="25px">Codigo del truno</th>
		<th width="25px" style="width: 67px; ">Dia del turno</th>
		<th width="25px" style="width: 67px; ">Fecha del turno</th>
		<th width="25px">Horario</th>
		<th width="25px">Especialidad</th>
		<th width="25px" style="width: 76px; ">DNI del paciente</th>
		<th width="25px">Informacion del paciente</th>
	</tr>
	<%if(ListaTurnos!=null)
		for(Turnos tur: ListaTurnos) 
		{ %>
	<tr> 	 
				<td><%=tur.getCod_Turno() %></td> 
				<td><%=tur.getDia_Turno() %></td>
				<td><%=tur.getFecha_Turno() %></td>
				<td><%=tur.getInicio_Turno() %> a <%=tur.getFin_Turno() %></td>
				<td><%=tur.getEspecialidad_Turno() %></td>
				<td><%=tur.getDNI_Paciente_Turno() %><input type="hidden" name="DNI_Paciente" value="<%=tur.getDNI_Paciente_Turno()%>" /></td> 
				<td> <input type="submit" name="btnInformacion" value="Informacion"> </td>
				
			
		</tr>
			
		<% } %>
</table>
</form>
</body>
</html>