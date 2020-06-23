package br.com.cast.ferias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cast.ferias.Model.Permissao;

public  interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}

