package br.com.corridaKart.execult;

import java.io.IOException;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.negocio.CalculaResultadoCorrida;

public class ResultadoCorrida {

	public static void main(String[] args) {
	
		CalculaResultadoCorrida calculaResultadoCorrida = new CalculaResultadoCorrida();
		Corrida corrida = null;
		
		try {
			corrida = calculaResultadoCorrida.resultadoClassificacaoCorrida();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("############################## Podium ###############################");
		System.out.println("                          |"+corrida.getPilotos().get(0).getNome()+"|                         ");
		System.out.println("                        _______________                           ");
		System.out.println("                               1                                  ");
		
		System.out.println(" |"+corrida.getPilotos().get(1).getNome()+"|                               |"+corrida.getPilotos().get(2).getNome()+"| ");
		System.out.println(" _____________                               _______________      ");
		System.out.println("       2                                            3             ");
		System.out.println(" ");
		System.out.println(" ");
		
		
		System.out.println("#################################################### Classificação ################################################");
		corrida.getPilotos().forEach(piloto -> {
			
			StringBuilder resultado = new StringBuilder();
			resultado.append("Posição Chegada - " + piloto.getPosicaoChegada())
					.append(" | Código Piloto - " + piloto.getCodigoPiloto())
					.append(" | Nome Piloto - " + piloto.getNome())
					.append(" | Qtde Voltas Completadas - " + piloto.getVoltas().size())
					.append(" | Melhor volta - " + piloto.getMelhorVolta() + "/" + piloto.getMelhorTempo())
					.append(" | Velocidade média da Corrida - " + piloto.getVelocidadeMedia())
					;

			
			System.out.println(resultado);
		});
		System.out.println("");
		System.out.println("Tempo Total de Prova - " + corrida.getTempoTotal());
		System.out.println("");
		System.out.println(" ######################## Melhor volta da corrida ########################");
		System.out.println(" Piloto - " + corrida.getPilotoMelhorVolta().getNome());
		System.out.println(" Volta - " + corrida.getPilotoMelhorVolta().getMelhorVolta());
		System.out.println(" Tempo - " + corrida.getPilotoMelhorVolta().getMelhorTempo());
	}

}
