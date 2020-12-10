package com.web.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.app.models_entitys.Prestamo;

import com.web.app.repositories.PrestamoDAO;
@Component
public class PrestamoServiceImlp implements IPrestamoService {

	@Autowired
	private PrestamoDAO prestamoDAO;
	
	@Override
	public void insert(Prestamo p) {
		
		prestamoDAO.insert(p);
		
	}

	@Override
	public List<Prestamo> findAll() {
		
		return prestamoDAO.findAll();
	}

	@Override
	public Prestamo find(Integer id) {
	
		return prestamoDAO.find(id);
	}

	@Override
	public void updateMonto(Integer id,float monto) {
		
		prestamoDAO.updateMonto(id, monto);
	}

	@Override
	public List<Prestamo> PrestamosPagados() {
		
		return prestamoDAO.PrestamosPagados();
	}

	@Override
	public void delete(Integer id) {
		prestamoDAO.delete(id);
		
	}

	@Override
	public List<Prestamo> PrestamosActivos() {
		
		return prestamoDAO.PrestamosActivos();
	}

}
