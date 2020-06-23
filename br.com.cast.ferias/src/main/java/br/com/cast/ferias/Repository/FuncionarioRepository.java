package br.com.cast.ferias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cast.ferias.Model.Funcionario;
import br.com.cast.ferias.Repository.Funcionario.FuncionarioRepositoryQuery;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, FuncionarioRepositoryQuery{

}
