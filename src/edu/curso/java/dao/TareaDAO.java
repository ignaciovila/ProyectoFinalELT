package edu.curso.java.dao;

import java.util.List;

import edu.curso.java.bo.Tarea;

public interface TareaDAO extends GenericDAO<Tarea, Long> {

	List<Tarea> buscarTareasPorNombre(Long idProyecto, String titulo);

}