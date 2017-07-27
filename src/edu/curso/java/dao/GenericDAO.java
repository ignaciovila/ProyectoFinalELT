package edu.curso.java.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {
	
	PK create(T o);
	
	T read(PK id);
	
	List<T> returnList();
	
	void update(T o);
	
	void delete(PK id);
}
