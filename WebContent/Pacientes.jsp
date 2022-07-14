<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocioImpl.PacienteNegocioImpl"%>
<%@page import="negocioImpl.NacionalidadNegocioImpl"%>
<%@page import="entidad.Pacientes"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>
<%@page import="java.sql.*" %>

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
	
	ArrayList<Pais> listaPaises=null;
	ArrayList<Provincia> listaProvincias=null;
	ArrayList<Localidad> listaLocalidades=null;
	
	if(request.getAttribute("listaPaises")!=null)
	{
		listaPaises = (ArrayList<Pais>)request.getAttribute("listaPaises");
	}
	if(request.getAttribute("listaProvincias")!=null)
	{
		listaProvincias = (ArrayList<Provincia>)request.getAttribute("listaProvincias");
	}
	if(request.getAttribute("listaLocalidades")!=null)
	{
		listaLocalidades = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
	}
	
%>



	<h2>Pacientes</h2>
	
	<h3>Agregar Pacientes</h3>
	<form method="get" action="ServeletPaciente">
		<input type="number" name="Dni" placeholder="Dni" required/>
		<input type="text" name="Nombre" placeholder="Nombre" required/>
		<input type="text" name="Apellido" placeholder="Apellido" required/>
		<select name="Sexo" required>
		  <option value="Sexo" selected>Sexo</option>
		  <option value="Masculino">Masculino</option>
		  <option value="Femenino">Femenino</option>
		  <option value="Otro">Otro</option>
		</select>
		
		<input type="date" name="FechaDeNacimiento" placeholder="Fecha Nac (a-m-d)" required/>
			<select id="Nacionalidad" name="Nacionalidad" required>
				<option value="0">Seleccionar nacionalidad</option>
				<%
				int idPais;
				String nombrePais;
				if(listaPaises != null)
				{
					for(Pais p : listaPaises)
					{
						idPais = p.getId();
						nombrePais = p.getNombre();
					%>
					<option value="<%=idPais%>"><%=nombrePais%></option>
					<%
					}
				}
					%>
			</select>

			<select id="Provincia" name="Provincia" required>
				<option value="0">Seleccionar Provincia</option>
				<%
				int idProv;
				int idProvPais;
				String nombreProv;
				if(listaProvincias != null)
				{
					for(Provincia prov : listaProvincias)
					{
						idProv = prov.getIdProv();
						idProvPais = prov.getIdPaisProv();
						nombreProv = prov.getNombreProv();
					%>
					<option value="<%=idProv%>"><%=nombreProv%></option>
					<%
					}
				}
				%>
			</select>
		
			<select id="Localidad" name="Localidad" required>
				<option value="0">Seleccionar Localidad</option>
				<%
				int idLoc;
				int idLocProv;
				String nombreLoc;
				if(listaLocalidades != null)
				{
					for(Localidad loc : listaLocalidades)
					{
						idLoc = loc.getIdLoc();
						idProvPais = loc.getIdProvLoc();
						nombreLoc = loc.getNombreLoc();
					%>
					<option value="<%=idLoc%>"><%=nombreLoc%></option>
					<%
					}
				}
				%>
			</select>
		
		<input type="text" name="Direccion" placeholder="Dirección" required/>
		<input type="email" name="Mail" placeholder="Correo Electronico" required/>
		<input type="number" name="Telefono" placeholder="Teléfono" required/>
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
	
	ArrayList<Pacientes> listarPacientes=null;
	
	if(request.getAttribute("ListaP")!=null)
       {
       listarPacientes = (ArrayList<Pacientes>)request.getAttribute("ListaP");
       }
%>
<%
    String modificadoEs = null;
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
   			String nacPcte, provPcte, locPcte;
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
   		  		<td><%=pcte.getDNI() %><input type="hidden" name="Dni" value="<%=pcte.getDNI()%>"/></td> 
   				<td><input type="text" name="Nombre" value="<%=pcte.getNombre()%>"/></td>
   				<td><input type="text" name="Apellido" value="<%=pcte.getApellido() %>"/></td>
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
   				<td><input type="date" name="FechaDeNacimiento" value="<%=pcte.getFechaNacimiento() %>"/></td>
   				
   				<%
   					nacPcte = pcte.getNacionalidad();
   					switch(pcte.getNacionalidad())
   					{
	   					case "1":
	   						%>
	   						<td>
	   							<select name="Nacionalidad">
	   								<option value="1" selected>Argentina</option>
	   								<option value="2">Colombia</option>
	   								<option value="3">Brasil</option>
	   							</select>
	   						</td>
	   						<%
	   						break;
	   					case "2":
	   						%>
	   						<td>
	   							<select name="Nacionalidad">
	   								<option value="1">Argentina</option>
	   								<option value="2" selected>Colombia</option>
	   								<option value="3">Brasil</option>
	   							</select>
	   						</td>
	   						<%
	   						break;
	   					case "3":
	   						%>
	   						<td>
	   							<select name="Nacionalidad">
	   								<option value="1">Argentina</option>
	   								<option value="2">Colombia</option>
	   								<option value="3" selected>Brasil</option>
	   							</select>
	   						</td>
	   						<%
	   						break;
   					}
   					%>
   					<% 
   					provPcte = pcte.getProvincia();
   					switch(pcte.getProvincia()){
	   					case "1":
	   						%>
	   						<td>
		   						<select name="Provincia">
									<option value="1" selected>Buenos Aires</option>
									<option value="2">Chubut</option>
									<option value="3">Mendoza</option>
									<option value="4">Antioquia</option>
									<option value="5">Bolívar</option>
									<option value="6">Bahia</option>
									<option value="7">Goias</option>
								</select>
							</td>
	   						<%
	   						break;
	   					case "2":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2" selected>Chubut</option>
								<option value="3">Mendoza</option>
								<option value="4">Antioquia</option>
								<option value="5">Bolívar</option>
								<option value="6">Bahia</option>
								<option value="7">Goias</option>
							</select>
							</td>
   						<%
	   						break;
	   					case "3":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2">Chubut</option>
								<option value="3" selected>Mendoza</option>
								<option value="4">Antioquia</option>
								<option value="5">Bolívar</option>
								<option value="6">Bahia</option>
								<option value="7">Goias</option>
							</select>
							</td>
   						<%
	   						break;
	   					case "4":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2">Chubut</option>
								<option value="3">Mendoza</option>
								<option value="4" selected>Antioquia</option>
								<option value="5">Bolívar</option>
								<option value="6">Bahia</option>
								<option value="7">Goias</option>
							</select>
							</td>
   						<%
	   						break;
	   					case "5":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2">Chubut</option>
								<option value="3">Mendoza</option>
								<option value="4">Antioquia</option>
								<option value="5" selected>Bolívar</option>
								<option value="6">Bahia</option>
								<option value="7">Goias</option>
							</select>
							</td>
   						<%
	   						break;
	   					case "6":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2">Chubut</option>
								<option value="3">Mendoza</option>
								<option value="4">Antioquia</option>
								<option value="5">Bolívar</option>
								<option value="6" selected>Bahia</option>
								<option value="7">Goias</option>
							</select>
							</td>
   						<%
	   						break;
	   					case "7":
	   						%>
	   						<td>
	   						<select name="Provincia">
								<option value="1">Buenos Aires</option>
								<option value="2">Chubut</option>
								<option value="3">Mendoza</option>
								<option value="4">Antioquia</option>
								<option value="5">Bolívar</option>
								<option value="6">Bahia</option>
								<option value="7" selected>Goias</option>
							</select>
							</td>
   						<%
	   						break;
   					}
   				%>
   				<%
   				switch(pcte.getLocalidad()){
					case "1":
						%>
						<td>
   						<select name="Localidad">
							<option value="1" selected>Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "2":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2" selected>Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "3":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3" selected>Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "4":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4" selected>Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "5":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5" selected>Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "6":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6" selected>Localidad 1 Bra</option>
							<option value="7">Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
					case "7":
						%>
						<td>
   						<select name="Localidad">
							<option value="1">Localidad 1 Arg</option>
							<option value="2">Localidad 2 Arg</option>
							<option value="3">Localidad 3 Arg</option>
							<option value="4">Localidad 1 Col</option>
							<option value="5">Localidad 2 Col</option>
							<option value="6">Localidad 1 Bra</option>
							<option value="7" selected>Localidad 2 Bra</option>
						</select>
						</td>
						<%
						break;
				}
   				%>
   				<td><input type="text" name="Direccion" value="<%=pcte.getDireccion() %>" /></td>
   				<td><input type="email" name="Mail" value="<%=pcte.getCorreo() %>" /></td>
   				<td><input type="number" name="Telefono" value="<%=pcte.getTelefono() %>" /></td>
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
   				<td><input type="submit" name="btnIniciarTurno" value="agregar turno" /></td>
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