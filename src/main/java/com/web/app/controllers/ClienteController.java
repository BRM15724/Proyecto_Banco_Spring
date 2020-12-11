package com.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ClienteController {
	
	@Autowired
	private IClienteService clienteS;
	@Autowired
	private IPrestamoService prestamoS;
	
	
	@GetMapping(path = "IdCliente")
	public String cliente(Model model) {
		model.addAttribute("titulo","Bienvenido Cliente!");
		model.addAttribute("cliente",new Cliente());
		return "ClienteF/validarCliente";
	}
	
	@GetMapping(path = "informacion")
	public String informacion(Model model) {
		model.addAttribute("titulo","Informacion");
		return "ClienteF/informacion";
	}
	
	@GetMapping(path = "opcionesCliente/{id}")
	public String opcionesCliente(@PathVariable Integer id,Model model) {
		model.addAttribute("titulo","Tu informacion");
		model.addAttribute("clientes", clienteS.find(id));
		return "ClienteF/opcionesCliente";
	}
	
	@GetMapping(path = "solicitarP")
	public String solicitarP(Model model) {
		model.addAttribute("titulo","Solicitar Prestamo");
		return "ClienteF/solicitarPrestamo";
	}
	
	@GetMapping(path = "abonar")
	public String abonar(Model model) {
		model.addAttribute("titulo","Abonar");
		return "ClienteF/abonar";
	}
	
	@GetMapping(path = "monto")
	public String monto(Model model) {
		model.addAttribute("titulo","Monto");
		return "ClienteF/monto";
	}

	@GetMapping(path = "editarCliente/{id}")
	public String editar(@PathVariable Integer id,Model model) {
		model.addAttribute("titulo","Edita tu informacion");
		model.addAttribute("cliente",clienteS.find(id));
		return "ClienteF/editarCliente";
	}
	
	@GetMapping(path = "verMisPrestamos/{idCliente}")
	public String verMisPrestamos(@PathVariable Integer idCliente , Model model) {
		model.addAttribute("titulo","Mis prestamos");
		model.addAttribute("prestamos", prestamoS.findIdCliente(idCliente));
		return "ClienteF/ResultadosDeBusquedaPrestamos";
	}
	
	@GetMapping(path = "SolicitaP/{idCliente}")
	public String SolicitaP(@PathVariable Integer idCliente , Model model) {
		model.addAttribute("titulo","Dar Prestamo");
		model.addAttribute("idFijoC",idCliente);
		model.addAttribute("idFijoP",prestamoS.getIdNewPrestamoId());
		model.addAttribute("prestamo",new Prestamo());
		return "ClienteF/solicitaPrestamo"; 
	}
	
	@GetMapping(path = "abonarPrestamoC/{idPrestamo}")
	public String abonarPrestamoC(@PathVariable Integer idPrestamo , Model model) {
		model.addAttribute("titulo","Abonar a mi prestamo");
		model.addAttribute("prestamo",prestamoS.find(idPrestamo));
		return "ClienteF/abonarPrestamoC"; 
	}
	
	@GetMapping(path = "retirarMonto/{idCliente}")
	public String retirarMonto(@PathVariable Integer idCliente , Model model) {
		model.addAttribute("titulo","Retirar de mi cuenta");
		model.addAttribute("idCliente",idCliente);
		return "ClienteF/retirarMonto";
	}
	
}
