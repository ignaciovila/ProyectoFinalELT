package edu.curso.java.bo;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombreCompleto;
	private String usuario;
	private String password;
	private boolean activo;	
	private Date fechaAlta;
	private boolean borrado;
	private boolean projectManager;
	private String rol;
	
	
	public boolean isProjectManager() {
		return projectManager;
	}
	public void setProjectManager(boolean projectManager) {
		this.projectManager = projectManager;
	}
	
	public String getPassword() {
		return password;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}	
}
