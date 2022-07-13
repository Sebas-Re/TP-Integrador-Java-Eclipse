<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocioImpl.PacienteNegocioImpl"%>
<%@page import="entidad.Pacientes"%>
<%@page import="java.sql.*" %>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Pacientes</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">

  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" />

<style type="text/css">
	<jsp:include page="css\pacientes.css"></jsp:include>
</style>



</head>
<body>
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
</div>
	<h2>Pacientes</h2>
	
	<h3>Agregar Pacientes</h3>
	<form method="get" action="ServeletPaciente">
		<input type="text" name="Dni" placeholder="Dni"/>
		<input type="text" name="Nombre" placeholder="Nombre"/>
		<input type="text" name="Apellido" placeholder="Apellido"/>
		<select name="Sexo">
		  <option value="Sexo" selected>Sexo</option>
		  <option value="Masculino">Masculino</option>
		  <option value="Femenino">Femenino</option>
		  <option value="Otro">Otro</option>
		</select>
		
		<input type="text" name="FechaDeNacimiento" placeholder="Fecha de nacimiento"/>
		
		<select>
			<option value="0">Seleccionar nacionalidad</option>
		</select>
		<select>
			<option value="0">Seleccionar nacionalidad</option>
		</select>
		
		<input type="text" name="Nacionalidad" placeholder="Nacionalidad"/>
		<input type="text" name="Provincia" placeholder="Provincia"/>
		<input type="text" name="Localidad" placeholder="Localidad"/>
		<input type="text" name="Direccion" placeholder="Dirección"/>
		<input type="text" name="Mail" placeholder="Correo Electronico"/>
		<input type="text" name="Telefono" placeholder="Teléfono"/>
		<input type="submit" name="BtnAgregar" value="Agregar" />
	</form>
	<%
    String agregadoEs = null;
	if(request.getAttribute("AgregadoEs") != null){
		agregadoEs = request.getAttribute("AgregadoEs").toString();
			if(agregadoEs == "true")
			{
				agregadoEs = "Paciente agregado exitosamente.";
			}
			else {
				agregadoEs = "El paciente no pudo ser agregado.";
			}
			%>
			<%=agregadoEs%>
			<%
		}
	%>
	<br />
	<h3>Listar, modificar, dar de baja pacientes</h3>
	
	<form method="get" action="ServeletPaciente">
		<input type="text" name="txtFiltrarNombre">
		<select name="FiltarEstado">
			  <option value="2">Todos</option>		
			  <option value="0">Baja</option>
			  <option value="1">Alta</option>
			</select>
		<input type="submit" name="btnFiltrar" value="Filtar">
	</form>
	</br>
	
<%	
	Pacientes p = new Pacientes();
	PacienteNegocioImpl neg = new PacienteNegocioImpl();
	
	ArrayList<Pacientes> listarPacientes=null;
	listarPacientes = neg.listarPaciente();
	
	if(request.getAttribute("ListaP")!=null)
       {
       listarPacientes = (ArrayList<Pacientes>)request.getAttribute("ListaP");
       }
%>
<%
    String modificadoEs = "hola";
	if(request.getAttribute("ModificacionEs") != null){
		modificadoEs = request.getAttribute("ModificacionEs").toString();
			if(modificadoEs == "true")
			{
				modificadoEs = "La modificacion se realizó exitosamente.";
			}
			else {
				modificadoEs = "No se pudo modificar.";
			}
			%>
			<%=modificadoEs%>
			<%
		}
	%>
	<%=modificadoEs%>
<table id="tablaListar" class="display">
	<thead>
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Sexo</th>
			<th>Fecha de<br>nacimiento</th>
			<th>Nacionalidad</th>
			<th>Provincia</th>
			<th>Localidad</th>
			<th>Dirección</th>
			<th>Correo</th>
			<th>Teléfono</th>
			<th>Estado</th>
			<th>Modificar/Baja</th>
		</tr>
	</thead>
    <tbody>
    
       <%   
       if(listarPacientes!=null)
       {
    	   for(Pacientes pcte : listarPacientes) 
   		{

   			int estadoPac;
   			String sexoPac = "null";
   			switch(pcte.getSexo())
   			{
   			case "Masculino": sexoPac = "Masculino";
   				break;
   			case "Femenino": sexoPac = "Femenino";
   				break;
   			case "Otro": sexoPac = "Otro";
   				break;
   			}

   	%>
   		<tr> 	 
   		  	<form method="get" action="ServeletPaciente">
   		  		<td><%=pcte.getDNI() %><input type="hidden" name="Dni" value="<%=pcte.getDNI()%>" /></td> 
   				<td><input name="Nombre" value="<%=pcte.getNombre()%>" /></td>
   				<td><input name="Apellido" value="<%=pcte.getApellido() %>" /></td>
   				<%
   					if(sexoPac == "Masculino")
   					{
   				%>
   					<td>
   						<select name="Sexo">
   						  <option value="Sexo">Sexo</option>
   						  <option value="Masculino" selected>Masculino</option>
   						  <option value="Femenino">Femenino</option>
   						  <option value="Otro">Otro</option>
   						</select>
   					</td>
   				<%
   				}
   				else if(sexoPac == "Femenino")
   					{
   				%>
   					<td>
   					<select name="Sexo">
   						  <option value="Sexo">Sexo</option>
   						  <option value="Masculino">Masculino</option>
   						  <option value="Femenino" selected>Femenino</option>
   						  <option value="Otro">Otro</option>
   						</select>
   					</td>
   				<%
   					}
   				else if(sexoPac == "Otro")
   					{
   				%>
   				<td>
   					<select name="Sexo">
   						  <option value="Sexo">Sexo</option>
   						  <option value="Masculino">Masculino</option>
   						  <option value="Femenino">Femenino</option>
   						  <option value="Otro" selected>Otro</option>
   					</select>
   				</td>
   				<%
   					}
   				%>
   				<td><input name="FechaDeNacimiento" value="<%=pcte.getFechaNacimiento() %>" /></td>
   				<td><input name="Nacionalidad" value="<%=pcte.getNacionalidad() %>" /></td>
   				<td><input name="Provincia" value="<%=pcte.getProvincia() %>" /></td>
   				<td><input name="Localidad" value="<%=pcte.getLocalidad() %>" /></td>
   				<td><input name="Direccion" value="<%=pcte.getDireccion() %>" /></td>
   				<td><input name="Mail" value="<%=pcte.getCorreo() %>" /></td>
   				<td><input name="Telefono" value="<%=pcte.getTelefono() %>" /></td>
   				<% 
   					if (pcte.getEstado()==1)
   					{
   						%>
   						<td>
   						<select name="Estado">
   						  <option value="1"selected>Alta</option>
   						  <option value="0">Baja</option>
   						</select>
   						</td>
   						<% 
   					}
   					else{
   						%>
   						<td>
   						<select name="Estado">
   						  <option value="1">Alta</option>
   						  <option value="0"selected>Baja</option>
   						</select>
   						</td>
   						<% 
   					}
   				%>
   				<td><input type="submit" name="BtnModificar" value="Modificar" /></td>
   		  	</form>
   		</tr>
   	<% } 
       }
       %>
    </tbody>
</table>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>

<script>

    //IDIOMA ESPAÑOL DEL DATATABLE  

    $(document).ready(function() {

      $('#tablaListar').DataTable({

        "language": {

          "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"

        }

      });

    });

  </script>

</body>
</html>