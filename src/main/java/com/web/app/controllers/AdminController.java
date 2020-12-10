package com.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.models_entitys.Cliente;
import com.web.app.models_entitys.Prestamo;
import com.web.app.services.IClienteService;
import com.web.app.services.IPrestamoService;



@Controller
@RequestMapping(path = "/app")
public class AdminController {
	
	@Autowired
	private IClienteService clienteS;
	@Autowired
	private IPrestamoService prestamoS;
	
	@GetMapping(path = "admin")
	public String admin(Model model) {
		model.addAttribute("titulo","Bienvenido Administrador!");
		return "Administrador/admin";
	}

	
	@GetMapping(path = "clientesB")
	public String clientesB(Model model) {
		model.addAttribute("titulo","Buesqueda");
		return "Administrador/clientesB";
	}
	
	@GetMapping(path = "prestamosB")
	public String prestamosB(Model model) {
		model.addAttribute("titulo","Busqueda");
		return "Administrador/prestamosB";
	}
	
	@GetMapping(path = "dineroB")
	public String dineroB(Model model) {
		model.addAttribute("titulo","Busqueda");
		return "Administrador/dineroB";
	}
	
	@GetMapping(path = "agregar")
	public String agregar(Model model) {
		model.addAttribute("titulo","Agregar Cliente");
		model.addAttribute("cliente",new Cliente());
		return "Administrador/agregarCliente";
	}
	
	@GetMapping(path = "editar/{id}")
	public String editar(@PathVariable Integer id,Model model) {
		model.addAttribute("titulo","Editar Cliente");
		model.addAttribute("cliente",clienteS.find(id));
		return "Administrador/editarCliente";
	}
	
	@GetMapping(path = "eliminar")
	public String eliminar(Model model) {
		model.addAttribute("titulo","Eliminar Cliente");
		model.addAttribute("cliente",new Cliente());
		return "Administrador/eliminarCliente";
	}
	
	@GetMapping(path = "darPrestamo")
	public String darPrestamo(Model model) {
		model.addAttribute("titulo","Dar Prestamo");
		model.addAttribute("prestamo",new Prestamo());
		return "Administrador/darPrestamo";
	}
	
	@GetMapping(path = "abonarPrestamo/{idCliente}")
	public String abonarPrestamo(@PathVariable Integer idCliente,Model model) {
		model.addAttribute("titulo","Abonar al prestamo");
		model.addAttribute("prestamo",prestamoS.find(idCliente));
		return "Administrador/abonarPrestamo";
	}
	
	
	
	@GetMapping(path = "clientesPorID")
	public String clientesPorID(Model model) {
		model.addAttribute("titulo","Clientes por ID");
		model.addAttribute("cliente", new Cliente());
		return "Administrador/clientesPorID";
	}
	
	@GetMapping(path = "clientesPorNombre")
	public String clientesPorNombre(Model model) {
		model.addAttribute("titulo","Clientes por nombre");
		model.addAttribute("cliente", new Cliente());
		return "Administrador/clientesPorNombre";
	}
	
	@GetMapping(path = "prestamosPorFecha")
	public String prestamosPorFecha(Model model) {
		model.addAttribute("titulo","Prestamos por fecha");
		return "Administrador/prestamosPorFecha";
	}
	
	@GetMapping(path = "prestamosPorIdCliente")
	public String prestamosPorIdCliente(Model model) {
		model.addAttribute("titulo","Prestamos por ID de cliente");
		model.addAttribute("prestamo", new Prestamo());
		return "Administrador/prestamosPorIdCliente";
	}
	
	
	
	
}
