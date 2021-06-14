package br.org.serratec.camadas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.camadas.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	public Endereco findByCep(String cep);
}
