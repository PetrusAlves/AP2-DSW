package entidade;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "jogo")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome_jogador")
	private String nomeJogador;
	
	@Column(name="numero_apostado")
	private Integer numeroAposta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column(name = "numero_secreto")
	private Integer numeroSecreto;
	
	public Jogo() {
	}
	
	public Jogo(Integer id, String nomeJogador, Integer numeroAposta, Date dataCadastro, Integer numeroSecreto) {
		this.id = id;
		this.nomeJogador = nomeJogador;
		this.numeroAposta = numeroAposta;
		this.dataCadastro = dataCadastro;
		this.numeroSecreto = numeroSecreto;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public Integer getNumeroAposta() {
		return numeroAposta;
	}

	public void setNumeroAposta(Integer numeroAposta) {
		this.numeroAposta = numeroAposta;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getNumeroSecreto() {
		return numeroSecreto;
	}

	public void setNumeroSecreto(Integer numeroSecreto) {
		this.numeroSecreto = numeroSecreto;
	}
}