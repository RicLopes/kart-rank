package br.com.corridaKart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.corridaKart.negocio.CalculaResultadoCorrida;

public class Util {

	public static String obterCaminhoArquivoLog() {
		return CalculaResultadoCorrida.class.getClassLoader().getResource("corrida_de_kart.log").getPath().substring(1);
	}
	
	public static Date converteStringData(String dataString, String formato) {
		Date data = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			data = format.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public static String converteDataString(Date data, String formato) {
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(data);
	}
	
	public static String obterTrecho(String texto, int posicaoInicial, Integer posicaoFinal) {
		String trecho = "";
		if (posicaoFinal == null) {
			trecho = texto.substring(posicaoInicial).trim();
		} else {
			trecho = texto.substring(posicaoInicial, posicaoFinal).trim();
		}
		return trecho;
	}
	
	public static List<String> obterArquivoDeLog() throws IOException {
		BufferedReader arquivo = Files.newBufferedReader(Paths.get(obterCaminhoArquivoLog()));
		return arquivo.lines().collect(Collectors.toList());
	}
	
}
