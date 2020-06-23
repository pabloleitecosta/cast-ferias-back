package br.com.cast.ferias.Resource;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cast.ferias.Model.Funcionario;
import br.com.cast.ferias.Repository.FuncionarioRepository;
import br.com.cast.ferias.Service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/v1/listar")
	public Page<?> listar(Pageable paginacao){
		return funcionarioService.listar(paginacao);
	}
	
	@GetMapping("/v1/list")
	public List<Funcionario> listarTodosList() {
		return funcionarioService.list();
	}	
	
	@PostMapping("/v1/adicionar")
	public ResponseEntity<Funcionario> adicionar(@Valid @RequestBody Funcionario funcionario){
		funcionario.setNumeroMatricula(new Date().getTime());
		Funcionario funcionariosalvo = funcionarioService.salvar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionariosalvo);
	}
	
	@PutMapping("/v1/atualizar/{codigo}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Funcionario categoria){		
		try {
			Funcionario categoriaSalvo = funcionarioService.atualizar(codigo, categoria);
		 return ResponseEntity.ok(categoriaSalvo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
	}
	
	@DeleteMapping("/v1/excluir/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable  Long codigo) {
		try {
			Funcionario categoria = funcionarioService.porId(codigo);
			if(categoria != null) {
				funcionarioService.deletar(categoria);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();					
		}
	}	
	
	@PostMapping("/v1/anexo")
	public String uploadImagem(@RequestParam MultipartFile anexo) {		
		try {			
			OutputStream out = new FileOutputStream("c:\\Temp\\"+anexo.getOriginalFilename());
			out.write(anexo.getBytes());
			out.close();
			return "OK";
			
		} catch (Exception e) {
			return "ERRO";
		}		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarporID(@PathVariable Long id){
		return funcionarioRepository.findById(id)
				.map(funcionario -> ResponseEntity.ok(funcionario))
				.orElse(ResponseEntity.notFound().build());				
	}

}
