<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	
</script>

<c:choose>
	<c:when test="${not empty tareas}">
		<table id="tablaTareas"
			class="table table-striped table-bordered table-hover">
			<tr>
				<th class="text-center">Titulo</th>
				<th class="text-center">Estado</th>
				<th class="text-center">Tipo</th>
				<th class="text-center">Horas</th>
				<th class="text-center">Fecha Inicio</th>
				<th class="text-center">Fecha Fin</th>
				<th class="text-center">Opciones</th>
			</tr>
		
			<c:forEach items="${tareas}" var="t">
				<tr>
					<td class="text-center">${t.titulo}</td>
					<td class="text-center">${t.estado}</td>
					<td class="text-center">${t.tipo}</td>
					<td class="text-center">${t.cantHoras}</td>
					<td class="text-center"><fmt:formatDate value="${t.fechaInicio}"
								pattern="yyyy-MM-dd" /></td>
					<td class="text-center"><fmt:formatDate value="${t.fechaFin}"
								pattern="yyyy-MM-dd" /></td>
					<td class="text-center" hidden="true" id="idTarea">${t.id}</td>
					<td class="text-center">
						<form id="form${t.id}">
							<input type="hidden" name="id" value="${t.id}"></input>
						</form>
						<a href="<c:url value='/tareas/vertarea.html?id=${t.id}&idProyecto=${idProyecto}'/>"
							class="btn btn-default"> <span
								class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="font-size: 10px;"> </span>Ver</a> 
						<button class="btn btn-default" type="button" id="buscoTarea"
							data-toggle="modal" data-target="#myModal">
		
							<span class="glyphicon glyphicon-info-sign" aria-hidden="true"
								style="font-size: 10px;"> </span>Info
						</button>
						<a href="<c:url value='/tareas/editartarea.html?id=${t.id}&idProyecto=${idProyecto} '/>" class="btn btn-default">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"
								style="font-size: 10px;"> </span>Editar
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h4>No hay tareas que se correspondan con su busqueda</h4>
	</c:otherwise>
</c:choose>		

<div class="modal fade" id="myModal"></div>