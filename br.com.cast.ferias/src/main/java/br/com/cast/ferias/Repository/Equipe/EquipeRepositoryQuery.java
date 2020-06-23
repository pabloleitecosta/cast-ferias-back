package br.com.cast.ferias.Repository.Equipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipeRepositoryQuery {

	public Page<?> filtrar(EquipeFiltro equipeFiltro, Pageable paginacao);
}
