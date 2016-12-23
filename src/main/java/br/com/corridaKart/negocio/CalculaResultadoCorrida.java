package br.com.corridaKart.negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.model.Piloto;
import br.com.corridaKart.model.Volta;

public class CalculaResultadoCorrida {

	/**
	 * Método para ler arquivo com resultados da corrida e calcular os dados.
	 * 
	 * @return Corrida
	 */
	public Corrida resultadoClassificacaoCorrida() {
		String fileName = obterCaminhoArquivoLog();
		List<String> list = new ArrayList<>();
		Corrida corrida = new Corrida();

		try (BufferedReader arquivo = Files.newBufferedReader(Paths.get(fileName))) {

			list = arquivo.lines().collect(Collectors.toList());
			calculaTempoTotalProva(corrida, list);

			list.forEach(n -> {

				Piloto piloto = new Piloto();
				if (!n.startsWith("Hora")) {
					String codigoPiloto = n.substring(18, 22).trim();
					String tempoVolta = n.substring(48, 61).trim();
					Double velocidadeMediaVolta = Double
							.parseDouble(n.substring(70, n.length()).trim().replace(",", "."));
					int numeroVolta = Integer.parseInt(n.substring(39, 49).trim());

					criarPiloto(piloto, n);
					if (corrida.getPilotos().isEmpty()) {
						corrida.getPilotos().add(piloto);
					} else {
						List<Piloto> pilotos = corrida.getPilotos();
						if (pilotos.contains(piloto)) {
							for (Piloto pilotoInserido : pilotos) {
								if (pilotoInserido.getCodigoPiloto().equals(codigoPiloto)) {
									Volta volta = new Volta();
									volta.comNumeroVolta(numeroVolta).comTempoVolta(tempoVolta)
											.comVelocidadeMediaVolta(velocidadeMediaVolta);
									pilotoInserido.getVoltas().add(volta);
								}
							}
						} else {
							corrida.getPilotos().add(piloto);
						}
					}
				}
			});

			calculaPosicao(corrida);
			calculaMelhorVoltaCorrida(corrida, list);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return corrida;
	}

	private void calculaMelhorVoltaCorrida(Corrida corrida, List<String> lista) {
		String melhorVoltaCorrida = null;
		melhorVoltaCorrida = pegarMelhorVoltaCorrida(lista);
		
		for (Piloto piloto : corrida.getPilotos()) {
			if (melhorVoltaCorrida.equals(piloto.getMelhorTempo())) {
				corrida.setPilotoMelhorVolta(piloto);
			}
		}
		
	}

	private String pegarMelhorVoltaCorrida(List<String> lista) {
		String melhorVoltaCorrida;
		List<String> listaVoltas = new ArrayList<>();
		
		for (String n : lista) {
			listaVoltas.add(n.substring(48, 61).trim());
		}
		
		Collections.sort(listaVoltas);
		melhorVoltaCorrida = listaVoltas.get(0);
		return melhorVoltaCorrida;
	}
	
	private void calculaTempoTotalProva(Corrida corrida, List<String> lista) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
		SimpleDateFormat format2 = new SimpleDateFormat("mm:ss.SSS");

		try {
			Long valor = format.parse(lista.get(1).substring(0, 13).trim()).getTime();
			Long valor2 = format2.parse(lista.get(1).substring(48, 61).trim()).getTime();
			Long horaFim = format.parse(lista.get(lista.size() - 1).substring(0, 13).trim()).getTime();
			Long horaInicio = valor - valor2;
			Long resultado = horaFim - horaInicio;

			corrida.setTempoTotal(format2.format(new Date(resultado)));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void calculaPosicao(Corrida corrida) {
		Integer posicaoChegada = 1;
		List<Piloto> pilotos = corrida.getPilotos();
		Collections.sort(pilotos);

		for (Piloto piloto : pilotos) {
			piloto.setPosicaoChegada(posicaoChegada++);
		}
	}

	/**
	 * Método para criar piloto usando DSL.
	 * 
	 * @param piloto
	 * @param n
	 */
	private void criarPiloto(Piloto piloto, String n) {
		String codigoPiloto = n.substring(18, 22).trim();
		String nomePiloto = n.substring(24, 38).trim();
		String horaChegada = n.substring(1, 13).trim();
		String tempoVolta = n.substring(48, 61).trim();
		int numeroVolta = Integer.parseInt(n.substring(39, 49).trim());
		Double velocidadeMediaVolta = Double.parseDouble(n.substring(70, n.length()).trim().replace(",", "."));

		Volta volta = new Volta().comNumeroVolta(numeroVolta).comTempoVolta(tempoVolta)
				.comVelocidadeMediaVolta(velocidadeMediaVolta);

		piloto.comCodigoPiloto(codigoPiloto).comNome(nomePiloto).comHoraChegada(horaChegada).comVolta(volta);
	}

	private String obterCaminhoArquivoLog() {
		return CalculaResultadoCorrida.class.getClassLoader().getResource("corrida_de_kart.log").getPath().substring(1);
	}

}
