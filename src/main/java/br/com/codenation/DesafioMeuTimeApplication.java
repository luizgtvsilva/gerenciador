package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	
	public TreeMap<Long, Time> times = new TreeMap<Long, Time>();
	public TreeMap<Long, Jogador> jogadores = new TreeMap<Long, Jogador>();
	
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		
		if(times.containsKey(time.getTimeId())) {
		throw new IdentificadorUtilizadoException();
	} else {
		times.put(time.getTimeId(), time);
	}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		
		if(jogadores.containsKey(jogador.getId())) {
		throw new IdentificadorUtilizadoException();
		}
		else if (!(times.containsKey(jogador.getIdTime()))) {
			throw new TimeNaoEncontradoException();
		}
		
		else {
			jogadores.put(jogador.getId(), jogador);
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Long idTime = jogadores.get(idJogador).getIdTime();
		times.get(idTime).setCapitaoTime(idJogador);
		
		if (!(jogadores.containsKey(idJogador))) {
		throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if(!(jogadores.containsKey(idTime))) {
		throw new TimeNaoEncontradoException();
		} else if (times.get(idTime).getCapitaoTime() == null) {
			throw new CapitaoNaoInformadoException();
		}
		
		return times.get(idTime).getCapitaoTime();
	}
	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if (!(jogadores.containsKey(idJogador))) {
		throw new JogadorNaoEncontradoException();
		}
		
		return jogadores.get(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if (!(times.containsKey(idTime))) {
		throw new TimeNaoEncontradoException();
		}
		
		return times.get(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadoresTime = new ArrayList<Long>();
		
		if (!(times.containsKey(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		for (Entry<Long, Jogador> jogador : jogadores.entrySet()) {
			if (jogador.getValue().getIdTime().equals(idTime))
				jogadoresTime.add(jogador.getKey());
		}
		return jogadoresTime;

	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (!(times.containsKey(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		Long id = null;
		Integer nivelHabilidade = -1;
		for (Entry<Long, Jogador> jogador : jogadores.entrySet()) {
			if (jogador.getValue().getIdTime().equals(idTime) && jogador.getValue().getNivelHabilidade() > nivelHabilidade ) {
				id = jogador.getValue().getId();
				nivelHabilidade = jogador.getValue().getNivelHabilidade();
			}
		}
		return id;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (!(times.containsKey(idTime))) {
			throw new TimeNaoEncontradoException();
		}
		LocalDate data = LocalDate.now();
		Long maisVelho = null;
		
		for (Entry<Long, Jogador> jogador : jogadores.entrySet()) {
			if ((jogador.getValue().getIdTime().equals(idTime)) && ((data).isAfter(jogador.getValue().getDataNascimento()))) {
				maisVelho = jogador.getValue().getId();
				data = jogador.getValue().getDataNascimento();
			}
		}
		return maisVelho;
	}
	

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> timesx = new ArrayList<>(times.keySet());
		return timesx;
	
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (!(times.containsKey(idTime))) {
			throw new TimeNaoEncontradoException();
		}
		BigDecimal sal = BigDecimal.ZERO;
		Long maisRico = null;
		
		for (Entry<Long, Jogador> jogador : jogadores.entrySet()) {
			if (sal.compareTo(jogador.getValue().getSalario()) < 0) {
				maisRico = jogador.getValue().getId();
				sal = jogador.getValue().getSalario();
			}
		}
		return maisRico;
		
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (!(jogadores.containsKey(idJogador))) {
		throw new JogadorNaoEncontradoException();
		}
		BigDecimal salarios = jogadores.get(idJogador).getSalario();
		return salarios;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Comparator<Jogador> comp = new Comparator<Jogador>() {
			@Override
			public int compare(Jogador jogador1, Jogador jogador2) {
				if(jogador1.getNivelHabilidade() != jogador2.getNivelHabilidade()) {
					return jogador2.getNivelHabilidade()-jogador1.getNivelHabilidade();
				} else {
					return (int) (jogador1.getId()-jogador2.getId());
				}
			}
		};
		Stream <Jogador> ordem = jogadores.values().stream()
				.sorted(comp).limit(top);
		
		return ordem.map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (!(times.containsKey(timeDaCasa)) || !(times.containsKey(timeDeFora))) {
			throw new TimeNaoEncontradoException();
		}
		return (times.get(timeDaCasa).getCorUniformePrincipal().equals(times.get(timeDeFora).getCorUniformePrincipal())) ? 
				times.get(timeDeFora).getCorUniformeSecundario() : times.get(timeDeFora).getCorUniformePrincipal();
	}

}
