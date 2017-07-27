package edu.curso.java.dao;

import java.util.List;

import edu.curso.java.bo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Long> {

	List<Usuario> buscarUsuariosPorNombre(String nombre);
	
	List<Usuario> buscarUsuariosPorNombre(Long idProyecto,String nombreCompleto);
	
	List<Usuario> recuperarUsuariosInactivos();
	
	List<Usuario> recuperarUsuariosPrincipalesInactivos();
}