package br.com.corridaKart.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.model.Piloto;
import br.com.corridaKart.model.Volta;
import br.com.corridaKart.util.Constante;
import br.com.corridaKart.util.Util;

public class CalculaResultadoCorrida {

	/**
	 * Método para ler arquivo com resultados da corrida e calcular os dados.
	 * 
	 * @return Corrida
	 * @throws IOException
	 */
	public Corrida resultadoClassificacaoCorrida() throws IOException {
		List<String> list = new ArrayList<>();
		Corrida corrida = new Corrida();

		list = Util.obterArquivoDeLog();

		calculaTempoTotalProva(corrida, list);

		list.forEach(linhaArquivo -> {

			Piloto piloto = new Piloto();
			if (!linhaArquivo.startsWith(Constante.HORA)) {
				criarPiloto(piloto, linhaArquivo);
				corrida.adicionaPiloto(piloto);
			}
		});

		calculaPosicao(corrida);
		calculaMelhorVoltaCorrida(corrida, list);

		return corrida;
	}

	public Corrida calculaMelhorVoltaCorrida(Corrida corrida, List<String> lista) {
		if (corrida != null && lista != null) {
			String melhorVoltaCorrida = pegarMelhorVoltaCorrida(lista);

			for (Piloto piloto : corrida.getPilotos()) {
				if (melhorVoltaCorrida.equals(piloto.getMelhorTempo())) {
					corrida.setPilotoMelhorVolta(piloto);
				}
			}
		}
		return corrida;

	}

	private String pegarMelhorVoltaCorrida(List<String> lista) {
		List<String> listaVoltas = new ArrayList<>();

		for (String n : lista) {
			listaVoltas.add(Util.obterTrecho(n, 48, 61).trim());
		}

		Collections.sort(listaVoltas);

		return listaVoltas.get(0);
	}

	public Corrida calculaTempoTotalProva(Corrida corrida, List<String> lista) {
		if (corrida != null && lista != null) {
			Long horaChegada = Util.converteStringData(Util.obterTrecho(lista.get(1), 0, 13), Constante.HH_MM_SS_SSS)
					.getTime();
			Long tempoVolta = Util.converteStringData(Util.obterTrecho(lista.get(1), 48, 61), Constante.MM_SS_SSS)
					.getTime();
	
			Long horaFim = Util
					.converteStringData(Util.obterTrecho(lista.get(lista.size() - 1), 0, 13), Constante.HH_MM_SS_SSS)
					.getTime();
			Long horaInicio = horaChegada - tempoVolta;
	
			Long resultado = horaFim - horaInicio;
	
			corrida.setTempoTotal(Util.converteDataString(new Date(resultado), Constante.MM_SS_SSS));
		}
		return corrida;
	}

	public Corrida calculaPosicao(Corrida corrida) {
		Integer posicaoChegada = 1;
		
		if (corrida != null) {
			List<Piloto> pilotos = corrida.getPilotos();
			Collections.sort(pilotos);
			
			for (Piloto piloto : pilotos) {
				piloto.setPosicaoChegada(posicaoChegada++);
			}
		}
		return corrida;
	}

	/**
	 * Método para criar piloto usando DSL.
	 * 
	 * @param piloto
	 * @param linha
	 */
	private void criarPiloto(Piloto piloto, String linha) {
		String horaChegada = Util.obterTrecho(linha, 0, 13);
		String codigoPiloto = Util.obterTrecho(linha, 18, 22);
		String nomePiloto = Util.obterTrecho(linha, 24, 38);
		String tempoVolta = Util.obterTrecho(linha, 48, 61);

		int numeroVolta = Integer.parseInt(Util.obterTrecho(linha, 39, 49));

		Double velocidadeMediaVolta = Double
				.parseDouble(Util.obterTrecho(linha, 70, null).replace(Constante.VIRGULA, Constante.PONTO));

		Volta volta = new Volta().comNumeroVolta(numeroVolta).comTempoVolta(tempoVolta)
				.comVelocidadeMediaVolta(velocidadeMediaVolta);

		piloto.comCodigoPiloto(codigoPiloto).comNome(nomePiloto).comHoraChegada(horaChegada).comVolta(volta);
	}

}
