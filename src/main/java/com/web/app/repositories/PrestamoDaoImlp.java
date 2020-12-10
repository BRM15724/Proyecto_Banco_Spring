package com.web.app.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.models_entitys.Cliente;
import com.web.app.models_entitys.Prestamo;

@Repository
public class PrestamoDaoImlp implements PrestamoDAO {

	@Autowired
	private EntityManager em;

	@Override
	@Transactional
	public void insert(Prestamo p) {

		if (p.getTipoPrestamo() == 1) {
			
			p.setMonto((float) (p.getMonto()+((0.05)*p.getMonto())));

		}
		if (p.getTipoPrestamo() == 2) {

			p.setMonto((float) (p.getMonto()+((0.10)*p.getMonto())));
		}
		if (p.getTipoPrestamo() == 3) {

			p.setMonto((float) (p.getMonto()+((0.30)*p.getMonto())));
		}
		em.persist(p);

	}

	@Override
	@Transactional
	public void delete(Integer id) {

		Prestamo eliminar = find(id);
		em.remove(eliminar);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {

		return em.createQuery("from Prestamo order by monto desc", Prestamo.class).getResultList();

	}

	@Override
	@Transactional
	public Prestamo find(Integer id) {

		return em.find(Prestamo.class, id);
	}

	@Override
	@Transactional
	public void updateMonto(Integer id, float monto) {

		Prestamo cambio = find(id);
		cambio.setMonto(cambio.getMonto() - monto);

	}

	@Override
	@Transactional
	public List<Prestamo> PrestamosPagados() {
		float monto = 0;
		@SuppressWarnings("unchecked")
		List<Prestamo> resultado = em.createQuery("SELECT p FROM Prestamo p WHERE p.monto<=:monto order by monto desc")
				.setParameter("monto", monto).getResultList();

		return resultado;
	}

	@Override
	@Transactional
	public List<Prestamo> PrestamosActivos() {
		float monto = 0;
		@SuppressWarnings("unchecked")
		List<Prestamo> resultado = em.createQuery("SELECT p FROM Prestamo p WHERE p.monto>:monto order by monto desc")
				.setParameter("monto", monto).getResultList();

		return resultado;
	}
}
