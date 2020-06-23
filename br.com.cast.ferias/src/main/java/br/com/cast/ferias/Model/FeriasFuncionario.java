package br.com.cast.ferias.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FERIASFUNCIONARIO")
@Getter
@Setter
public class FeriasFuncionario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDFERIASFUNCIONARIO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "IDFUNCIONARIO")
	private Funcionario funcionario;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column (name="DTINICIO")
	private Date dataInicio;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column (name="DTTERMINO")
	private Date dataTermino;
	

}
