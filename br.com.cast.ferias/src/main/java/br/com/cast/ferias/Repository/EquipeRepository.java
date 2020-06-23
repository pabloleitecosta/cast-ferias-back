package br.com.cast.ferias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cast.ferias.Model.Equipe;
import br.com.cast.ferias.Repository.Equipe.EquipeRepositoryQuery;

public interface EquipeRepository extends JpaRepository<Equipe, Long>, EquipeRepositoryQuery {
	
}
