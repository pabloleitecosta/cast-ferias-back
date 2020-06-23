package br.com.cast.ferias.Resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.ferias.Model.Equipe;
import br.com.cast.ferias.Repository.EquipeRepository;
import br.com.cast.ferias.Service.EquipeService;



@RestController
@RequestMapping("/equipes")
public class EquipeResource {
	

	@Autowired
	public EquipeService equipeService;
	
	@Autowired
	public EquipeRepository equipeRepository;
	
	@GetMapping("/v1/listar")
	public Page<?> listar(Pageable paginacao){
		return equipeService.listarTodos(paginacao);
	}
	
	@GetMapping("/v1/list")
	public List<?> list(){
		return equipeRepository.findAll();
	}	
		
	@PostMapping("/v1/adicionar")
	public ResponseEntity<Equipe> salvar(@Valid @RequestBody Equipe equipe){
		Equipe equipesalva = equipeService.savar(equipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(equipesalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipe> buscarPorId(@PathVariable Long id){
		return equipeRepository.findById(id)
				.map (equipe ->  ResponseEntity.ok(equipe))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/v1/excluir/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable  Long codigo) {
		try {
			Equipe equipe = equipeService.BuscarEquipeID(codigo);
			if(equipe != null) {
				equipeService.excluir(equipe);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();					
		}
	}	

}
