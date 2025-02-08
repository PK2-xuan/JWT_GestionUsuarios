package example.com.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.com.jwt.entity.JwtContacto;
import example.com.jwt.repository.ContactoRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/contactos")

public class ContactoController {

	private final ContactoRepository contac;
	
	public ContactoController(ContactoRepository contac) {
		super();
		this.contac = contac;
	}


	@GetMapping
	public List<JwtContacto> listContacto(){
		return contac.findAll();
	}
	
	

}
