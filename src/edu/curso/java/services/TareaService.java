package edu.curso.java.services;

import java.util.List;

import edu.curso.java.bo.Tarea;

public interface TareaService {

	List<Tarea> recuperarTareas();

	Long guardarTarea(Tarea tarea, Long idProyecto, List<Long> idUsuarios);

	Tarea recuperarTareaPorId(Long id);

	void borrarTareaPorId(Long id);

	List<Tarea> buscarTareasPorNombre(Long idProyecto,String nombre);

	void actualizarTarea(Tarea Tarea, Long idProyecto, List<Long> idUsuarios, Integer horas);

	void actualizarTarea(Tarea tarea);
}