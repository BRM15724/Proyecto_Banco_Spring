package com.web.app.models_entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "prestamo")
public class Prestamo {
	
	@Id
	private Integer idCliente;
	private float monto;
	private String fechaCreacion;
	private String fechaExpiracion;
	private Integer tipoPrestamo;
	
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
	
	
	
	

}
