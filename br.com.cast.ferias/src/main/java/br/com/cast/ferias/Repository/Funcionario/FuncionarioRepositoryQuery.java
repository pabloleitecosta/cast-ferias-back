package br.com.cast.ferias.Repository.Funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FuncionarioRepositoryQuery {
	
	public Page<?> filtrar(FuncionarioFiltro funcionarioFiltro, Pageable paginacao);

}
