package br.org.serratec.camadas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.org.serratec.camadas.model.Usuario;

//Não precisa ser declarado, pois é automático
//@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);

}
