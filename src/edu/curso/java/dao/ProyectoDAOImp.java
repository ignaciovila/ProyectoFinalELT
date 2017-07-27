package edu.curso.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.curso.java.bo.Proyecto;

@Repository
public class ProyectoDAOImp extends GenericDAOImp<Proyecto, Long> implements ProyectoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Proyecto> buscarProyectosPorNombre(String nombre){
		String hql = "from Proyecto as p where p.borrado = false AND p.nombre LIKE :nombre order by p.id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("nombre","%" + nombre + "%");
		return query.list();
	}
	
}
