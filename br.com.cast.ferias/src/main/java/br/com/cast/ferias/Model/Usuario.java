package br.com.cast.ferias.Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="USUARIO")
@JsonIgnoreProperties({"senha"})
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDUSUARIO")
	private Long codigo;
	
	@Column(name="NMUSUARIO")
	private String nome;

	@Column(name="DSSEMAIL")
	private String email;
	
	@Column(name="DSSENHA")
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="USUARIO_PERMISSAO", joinColumns = @JoinColumn(name = "idusuario"), 
										inverseJoinColumns = @JoinColumn(name = "idpermissao"))
	private List<Permissao> permissoes;
	
}
