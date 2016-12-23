package br.com.corridaKart.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.negocio.CalculaResultadoCorrida;

public class TestCalculaResultadoCorrida {

	private CalculaResultadoCorrida calculaResultadoCorrida = new CalculaResultadoCorrida();
	
	@Test
	public void testResultadoClassificacaoCorridaTempoTotal() {
		Corrida corrida = calculaResultadoCorrida.resultadoClassificacaoCorrida();
		assertTrue(corrida.getTempoTotal().equals("06:52.332"));
	}
	
	@Test
	public void testResultadoClassificacaoCorridaPrimeiroColocado() {
		Corrida corrida = calculaResultadoCorrida.resultadoClassificacaoCorrida();
		assertEquals(corrida.getPilotos().get(0).getCodigoPiloto(), "038");
	}
	
}
