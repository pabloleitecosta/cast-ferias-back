package br.com.cast.ferias.Controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.ferias.Model.Permissao;
import br.com.cast.ferias.Model.Usuario;
import br.com.cast.ferias.Repository.PermissaoRepository;
import br.com.cast.ferias.Repository.UsuarioRepository;


@RestController
@RequestMapping("/carga-base-dados")
public class CargaController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@GetMapping("/v1")
	public ResponseEntity<?> cargaTeste(){
		cargaInformacaoUsuario();
		return ResponseEntity.ok("Carga Executada Com Sucesso");
	}	
	
	private void cargaInformacaoUsuario() {
		
		Permissao permissao = new Permissao();
		permissao.setDescricao("ROLE_ADMINISTRADOR");
		permissaoRepository.save(permissao);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("administrador.ferias@castgroup.com.br");
		usuario.setNome("Pablo");
		usuario.setSenha("$2a$10$1hyMFqkcL6wrafFlh4kl5.Citm52/W2SxFTLqUsbY70iFLSvRrIHy"); //123a
		usuario.setPermissoes(Arrays.asList(permissao));
		usuarioRepository.save(usuario);
		
	}

}
