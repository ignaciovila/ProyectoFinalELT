package edu.curso.java.dao;

import java.util.List;

import edu.curso.java.bo.Proyecto;

public interface ProyectoDAO extends GenericDAO<Proyecto, Long>{

	List<Proyecto> buscarProyectosPorNombre(String nombre);

}