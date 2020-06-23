package br.com.cast.ferias.Resource;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.ferias.Model.FeriasFuncionario;
import br.com.cast.ferias.Repository.FeriasFuncionarioRepository;

@RestController
@RequestMapping("/feriasfuncionarios")
public class FeriasFuncionarioResource {
	
	
	//@Autowired
	//private FeriasFuncionarioService feriasFuncionarioService;
	
	@Autowired
	private FeriasFuncionarioRepository feriasFuncionarioService;
			
	
	@GetMapping("/v1/listar")
	public Page<?> listarTodos(Pageable paginacao) {
		//return feriasFuncionarioService.listarTodos(paginacao);
		return feriasFuncionarioService.findAll(paginacao);
	}
	
	@GetMapping("/v1/list")
	public List<FeriasFuncionario> listarTodosList() {
		return feriasFuncionarioService.findAll();
	}	
	
	@GetMapping("/v1/porid/{id}")
	public ResponseEntity<FeriasFuncionario> porId(@PathVariable Long id){
		try {
			Optional<FeriasFuncionario> categoria = feriasFuncionarioService.findById(id);
			return ResponseEntity.ok(categoria.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
		}
	}
	
	@PostMapping("/v1/adicionar")
	public ResponseEntity<FeriasFuncionario> salvar(@Valid @RequestBody FeriasFuncionario func){
		try {
			FeriasFuncionario categoriaSalvo = feriasFuncionarioService.save(func);
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalvo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
		}		
	}
	
	@PutMapping("/v1/atualizar/{codigo}")
	public ResponseEntity<FeriasFuncionario> atualizar(@PathVariable Long codigo, @Valid @RequestBody FeriasFuncionario categoria){		
		try {
			FeriasFuncionario categoriaSalvo = feriasFuncionarioService.save(categoria);
		 return ResponseEntity.ok(categoriaSalvo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
	}
	
	@DeleteMapping("/v1/excluir/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable  Long codigo) {
		try {
			Optional<FeriasFuncionario> categoria = feriasFuncionarioService.findById(codigo);
			if(categoria != null) {
				feriasFuncionarioService.delete(categoria.get());
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();					
		}
	}
	
	
	
}
