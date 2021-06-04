package br.org.serratec.camadas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.camadas.dto.UsuarioDTO;
import br.org.serratec.camadas.dto.UsuarioInserirDTO;
import br.org.serratec.camadas.exception.EmailException;
import br.org.serratec.camadas.model.Usuario;
import br.org.serratec.camadas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<UsuarioDTO> listar() {
		//return usuarioRepository.findAll();
		List<UsuarioDTO> usuarioDTOs 	= new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios 			= usuarioRepository.findAll();
		
		for (Usuario usuario : usuarios) {
			UsuarioDTO dto = new UsuarioDTO(usuario);
			usuarioDTOs.add(dto);
		}
		return usuarioDTOs;
	}
	
	/*public Usuario inserir (Usuario usuario) throws EmailException {
		Usuario u = usuarioRepository.findByEmail(usuario.getEmail());
		if(u != null) {
			throw new EmailException("#### Email Existente! Insira outro ####");
		}
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}*/
	public UsuarioDTO inserir (UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		Usuario u = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if(u != null) {
			throw new EmailException("#### Email Existente! Insira outro ####");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setPerfil("### Usuário Padrão ###");
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}


}
