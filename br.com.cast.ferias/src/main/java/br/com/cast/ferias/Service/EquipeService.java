package br.com.cast.ferias.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cast.ferias.Model.Equipe;
import br.com.cast.ferias.Repository.EquipeRepository;
import br.com.cast.ferias.Repository.Equipe.EquipeFiltro;

@Service
public class EquipeService {
	
	@Autowired
	private EquipeRepository equipeRepository;
	
	public Equipe savar(Equipe equipe) {
		equipeRepository.save(equipe);
		return equipe;
	}
	
	public Page<?> listarTodos(Pageable paginacao) {
		EquipeFiltro equipeFilter = new EquipeFiltro();
		return equipeRepository.filtrar(equipeFilter, paginacao);
	}
	

	public List<Equipe> list() {
		return equipeRepository.findAll();
	}
	
	public void deletarEquipe(Long id) {
		Equipe equipeDeletar = equipeRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		if(equipeDeletar != null) {
			equipeRepository.delete(equipeDeletar);
		}		
	}
	
	public Equipe BuscarEquipeID(Long id) {
		Equipe equipeBuscar = equipeRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		if(equipeBuscar == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		return equipeBuscar;
	}

	public void excluir(Equipe equipe) {
		equipeRepository.delete(equipe);		
	}

	
}
