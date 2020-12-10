package com.web.app.repositories;

import java.util.List;

import com.web.app.models_entitys.Prestamo;

public interface PrestamoDAO {

	
	void insert (Prestamo p);
	List<Prestamo> findAll();
	Prestamo find(Integer id);
	void updateMonto(Integer id,float monto);
	List<Prestamo> PrestamosPagados();
	void delete(Integer id);
	List<Prestamo> PrestamosActivos();
}
