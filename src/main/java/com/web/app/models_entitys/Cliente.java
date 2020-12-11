package com.web.app.models_entitys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	private Integer idCliente;
	private String nombre;
	private Integer telefono;
	private String correo;
	private String direccion;
	private float monto;
	
	@OneToMany(
			mappedBy = "cliente", cascade = {
	        CascadeType.ALL
	        })
	private List<Prestamo> prestamo;
	
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public List<Prestamo> getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(List<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}

	
	
}
