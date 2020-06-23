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
@Table(name="FUNCIONARIO")
@Getter
@Setter
public class Funcionario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDFUNCIONARIO")
	private Long codigo;
	
	@Column(name = "NMFUNCIONARIO")
	private String nome;
	
	@Column(name = "NUMATRICULA")
	private long numeroMatricula;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column (name="DATANASCIMENTO")
	private Date dataNascimento;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column (name="DATACONTRATACAO")
	private Date dataContratacao;
	
	@Column(name="FOTO")
	private byte[] foto;
	
	@Column(name ="NMRUA")
	private String rua;
	
	@Column(name ="NUMERO")
	private String numero;
	
	@Column(name ="COMPLEMENTO")
	private String complemento;
	
	@Column(name ="BAIRRO")
	private String bairro;
	
	@Column(name ="CIDADE")
	private String cidade;
	
	@Column(name ="ESTADO")
	private String estado;
	
	
	@ManyToOne
	@JoinColumn(name = "IDEQUIPE")
	private Equipe equipe;

}
