package edu.curso.java.controllers.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class TareaForm {
	private Long id;
	private String titulo;
	private String estado;
	private String tipo;
	private int cantHoras;
	private List<Long> idUsuarios = new ArrayList<Long>();
	private List <Long> idComentarios = new ArrayList<Long>();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio = new Date();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin = new Date();
		
	public List<Long> getIdComentarios() {
		return idComentarios;
	}

	public void setIdComentarios(List<Long> idComentarios) {
		this.idComentarios = idComentarios;
	}

	public int getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}

	public List<Long> getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(List<Long> idUsuarios) {
		this.idUsuarios = idUsuarios;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}
