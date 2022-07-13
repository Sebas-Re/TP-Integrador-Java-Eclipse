<%@page import="java.util.ArrayList"%>
<%@page import="negocioImpl.PacienteNegocioImpl"%>
<%@page import="entidad.Pacientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<a href="TurnosMedico.jsp">Volver</a>
<title>Insert title here</title>
</head>
<body>
	
<%	
	Pacientes p = new Pacientes();
	
	ArrayList<Pacientes> listarPacientes=null;
	
	if(request.getAttribute("ListaP")!=null)
       {
       listarPacientes = (ArrayList<Pacientes>)request.getAttribute("ListaP");
       }
%>
<table border="1" width="100%">
	<tr>
		<th width="25px">DNI</th>
		<th width="25px">Nombre</th>
		<th width="25px">Apellido</th>
		<th width="25px">Sexo</th>
		<th width="25px">Fecha de<br>nacimiento</th>
		<th width="25px">Nacionalidad</th>
		<th width="25px">Provincia</th>
		<th width="25px">Localidad</th>
		<th width="25px">Dirección</th>
		<th width="25px">Correo</th>
		<th width="25px">Teléfono</th>
		<th width="25px">Estado</th>
	</tr>
 <thead>
    <tbody>
    
       <%   if(listarPacientes!=null)
		for(Pacientes pcte : listarPacientes) 
		{
	%>
		<tr> 	 
		  	<form method="get" action="ServeletPaciente">
		  		<td><%=pcte.getDNI() %><input type="hidden" name="Dni" value="<%=pcte.getDNI()%>" /></td> 
				<td><%=pcte.getNombre() %></td>
				<td><%=pcte.getApellido() %></td>
				<td><%=pcte.getSexo() %></td>
				<td><%=pcte.getFechaNacimiento() %></td>
				<td><%=pcte.getNacionalidad() %></td>
				<td><%=pcte.getProvincia() %></td>
				<td><%=pcte.getLocalidad() %></td>
				<td><%=pcte.getDireccion() %></td>
				<td><%=pcte.getCorreo() %></td>
				<td><%=pcte.getTelefono() %></td>
				
				<%if(pcte.getEstado()==1){%>
				<td>Alta </td>
				<%}else{%>
				<td>baja </td>
				 <%}%>
				
		  	</form>
		</tr>
	<% } %>
    </tbody>
</table>
</body>
</html>