<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	
</script>
<c:choose>
	<c:when test="${not empty usuarios}">
		<table id="tablaUsuarios"
		class="table table-striped table-bordered table-hover">
		<tr>
			<th class="text-center">Nombre</th>
			<th class="text-center">Opciones</th>
		</tr>
	
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td class="text-center">${u.nombreCompleto}</td>
				<td class="text-center" hidden="true" id="idUsuario">${u.id}</td>
				<td class="text-center">
					<form id="form${u.id}">
						<input type="hidden" name="id" value="${u.id}"></input>
					</form>
					<button class="btn btn-default" type="button" id="buscoUsuario"
						data-toggle="modal" data-target="#myModal">
	
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"
							style="font-size: 10px;"> </span>Ver
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
		<h4>No hay usuarios que se correspondan con su busqueda</h4>
	</c:otherwise>
</c:choose>	

<div class="modal fade" id="myModal"></div>