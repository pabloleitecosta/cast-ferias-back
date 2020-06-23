package br.com.cast.ferias.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cast.ferias.Model.Funcionario;
import br.com.cast.ferias.Repository.FuncionarioRepository;
import br.com.cast.ferias.Repository.Funcionario.FuncionarioFiltro;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	public Funcionario porId(Long id) {
		return funcionarioRepository.findById(id).get();
	}
	
	public Funcionario salvar(Funcionario funcionario) {		
		funcionarioRepository.save(funcionario);		
		return funcionario;		
	}
	
	public Page<?> listar(Pageable paginacao){		
		FuncionarioFiltro funcionarioFilter = new FuncionarioFiltro();
		return funcionarioRepository.filtrar(funcionarioFilter, paginacao);
	}
	
	public Funcionario atualizar(Long codigo, @Valid Funcionario funcionario) {
		Funcionario funcionarioSalvo = porId(codigo);
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "codigo");
		return funcionarioRepository.save(funcionarioSalvo);
	}
	
	
	public void deletar(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
	
	public List<Funcionario> list() {
		return funcionarioRepository.findAll();
	}


}
