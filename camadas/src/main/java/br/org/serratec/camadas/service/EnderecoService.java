package br.org.serratec.camadas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.camadas.dto.EnderecoDTO;
import br.org.serratec.camadas.model.Endereco;
import br.org.serratec.camadas.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
/*	public Endereco buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return endereco.get();
		} else {
			// RestTemplate -- Permite buscar informações de uma API de terceiros e jogar para a minha
			// Classe que vou passar a URI e os dados
			// "Consumir" uma API de terceiros, através da URI e da classe Endereco
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + cep + "/json/";
			// Vai consumir do Viacep e jogar para Endereco (Os atributos precisam ser iguais)
			Optional<Endereco> enderecoViacep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViacep.get().getCep() != null) {
				// Para retirar o traço do CEP
				String cepSemTraco = enderecoViacep.get().getCep().replaceAll("-", "");
				enderecoViacep.get().setCep(cepSemTraco);
				return inserir(enderecoViacep.get());
			} else {
				return null;
			}
		}
	}*/
	
	public EnderecoDTO buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			// RestTemplate -- Permite buscar informações de uma API de terceiros e jogar para a minha
			// Classe que vou passar a URI e os dados
			// "Consumir" uma API de terceiros, através da URI e da classe Endereco
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + cep + "/json/";
			// Vai consumir do Viacep e jogar para Endereco (Os atributos precisam ser iguais)
			Optional<Endereco> enderecoViacep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViacep.get().getCep() != null) {
				// Para retirar o traço do CEP
				String cepSemTraco = enderecoViacep.get().getCep().replaceAll("-", "");
				enderecoViacep.get().setCep(cepSemTraco);
				return inserir(enderecoViacep.get());
			} else {
				return null;
			}
		}
	}

	public EnderecoDTO inserir(Endereco endereco) {
		// return new EnderecoDTO(enderecoRepository.save(endereco));
		endereco = enderecoRepository.save(endereco);
		return new EnderecoDTO(endereco);
	}

}
