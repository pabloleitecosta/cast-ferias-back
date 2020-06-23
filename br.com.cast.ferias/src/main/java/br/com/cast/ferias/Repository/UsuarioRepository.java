package br.com.cast.ferias.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cast.ferias.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {	
	public Optional<Usuario> findByEmail(String email);	
	public List<Usuario>findByPermissoesDescricao(String permissaoDescricao);	
}
