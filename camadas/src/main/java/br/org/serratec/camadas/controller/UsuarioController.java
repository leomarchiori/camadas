package br.org.serratec.camadas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.camadas.dto.UsuarioDTO;
import br.org.serratec.camadas.dto.UsuarioInserirDTO;
import br.org.serratec.camadas.exception.EmailException;
//import br.org.serratec.camadas.model.Usuario;
import br.org.serratec.camadas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		List<UsuarioDTO> usuarios = usuarioService.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		try {
			UsuarioDTO dto = usuarioService.inserir(usuarioInserirDTO);
			//usuario = usuarioService.inserir(usuario);
			
			// Inserindo o Id(caminho) no GET
			URI uri = 	ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						//.buildAndExpand(usuario.getId())
						.buildAndExpand(dto.getId())
						.toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (EmailException e) {
			// TODO: handle exception
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
