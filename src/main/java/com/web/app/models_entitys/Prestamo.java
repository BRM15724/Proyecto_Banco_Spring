package com.web.app.models_entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Prestamo")
public class Prestamo {
	
	@Id
	private Integer idPrestamo;
	private float monto;
	private String fechaCreacion;
	private String fechaExpiracion;
	private Integer tipoPrestamo;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	public Integer getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public Integer getTipoPrestamo() {
		return tipoPrestamo;
	}
	public void setTipoPrestamo(Integer tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	

}
