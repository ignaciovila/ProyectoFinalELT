<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:forEach items="${tarea.comentarios}" var="c">
	<div class="col-md-9">
		<div class="panel panel-default">
			<div class="panel-heading" id="panelComentario">
<!-- 				<button class="btn btn-default btn-xs" type="button" data-toggle="modal" -->
<!-- 					data-target="#myModalComentario" id="borrarComentario"> -->
<!-- 					<span class="glyphicon glyphicon-trash" aria-hidden="true" -->
<!-- 						style="font-size: 10px;"> </span> -->
<!-- 				</button> -->
				
				<a href="/trackandbug/tareas/borrarcomentario.html?id=${c.id}&idTarea=${tarea.id}&idProyecto=${proyecto.id}" class="btn btn-default btn-xs">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a>
				
				<input type="hidden" id="idComentario" value="${c.id}"></input>
				<span class="text-muted" ><fmt:formatDate
						value="${c.fechaComent}" pattern="yyyy-MM-dd"/> </span>
			</div>
			<div class="panel-body">${c.comentario}</div>
			
			<form id="form${c.id}">
					<input type="hidden" name="id" value="${c.id}"></input>
					<input type="hidden" name="idTarea" value="${tarea.id}"></input>
					<input type="hidden" name="idProyecto" value="${proyecto.id}"></input>
				</form>
		</div>

	</div>
</c:forEach>

<div class="modal fade" id="myModalComentario"></div>