package br.com.codenation;

import java.time.LocalDate;

public class Time {
	
	private Long timeId;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long capitaoTime;
	
	public Time() {
		
	}

	public Time(Long timeId, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		super();
		this.timeId = timeId;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Long getCapitaoTime() {
		return capitaoTime;
	}

	public void setCapitaoTime(Long idJogador) {
		this.capitaoTime = idJogador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeId == null) ? 0 : timeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (timeId == null) {
			if (other.timeId != null)
				return false;
		} else if (!timeId.equals(other.timeId))
			return false;
		return true;
	}
	
	
	
	
}