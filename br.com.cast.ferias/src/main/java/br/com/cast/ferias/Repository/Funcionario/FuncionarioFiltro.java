package br.com.cast.ferias.Repository.Funcionario;

import java.io.Serializable;
import java.util.Date;

import br.com.cast.ferias.Model.Equipe;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioFiltro implements Serializable{
	
	private long codigo;
	private String nome;
	private Date datanascimento;	
	private Date datacontracacao;
	private Equipe equipe;
	
}
