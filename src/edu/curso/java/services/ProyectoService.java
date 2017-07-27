package edu.curso.java.services;

import java.util.List;

import edu.curso.java.bo.Proyecto;
import edu.curso.java.bo.Usuario;

public interface ProyectoService {
	
	void actualizarProyecto(Proyecto proyecto, Long idUsuarioPrincipal, List<Long> idUsuarios);

	Long guardarProyecto(Proyecto proyecto, Long idUsuarioPrincipal, List<Long> idUsuarios);
	
	List<Proyecto> buscarProyectosPorNombre(String nombre);

	List<Proyecto> recuperarProyectos();

	Long guardarProyecto(Proyecto proyecto);

	Proyecto recuperarProyectoPorId(Long id);

	void borrarProyectoPorId(Long id);

	void actualizarProyecto(Proyecto proyecto);
}