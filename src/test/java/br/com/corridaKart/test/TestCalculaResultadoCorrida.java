package br.com.corridaKart.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.negocio.CalculaResultadoCorrida;
import br.com.corridaKart.util.Util;

public class TestCalculaResultadoCorrida {

	private CalculaResultadoCorrida calculaResultadoCorrida = new CalculaResultadoCorrida();
	public List<String> listaLinhas;
	
	@Before
	public void getUp() {
		try {
			listaLinhas = Util.obterArquivoDeLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testResultadoClassificacaoCorridaTempoTotal() {
		Corrida corrida = null;
		try {
			corrida = calculaResultadoCorrida.resultadoClassificacaoCorrida();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(corrida.getTempoTotal().equals("06:52.332"));
	}
	
	@Test
	public void testResultadoClassificacaoCorridaPrimeiroColocado() {
		Corrida corrida = null;
		try {
			corrida = calculaResultadoCorrida.resultadoClassificacaoCorrida();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(corrida.getPilotos().get(0).getCodigoPiloto(), "038");
	}
	
	@Test
	public void testCalculaMelhorVoltaCorridaCorridaNull() {
		Corrida corrida = calculaResultadoCorrida.calculaMelhorVoltaCorrida(null, listaLinhas);
		Assert.assertNull(corrida);
	}
	
	@Test
	public void testCalculaMelhorVoltaCorridaListaNull() {
		Corrida corrida = calculaResultadoCorrida.calculaMelhorVoltaCorrida(new Corrida(), null);
		Assert.assertNull(corrida.getTempoTotal());
	}
	
	@Test
	public void testCalculaTempoTotalProvaCorridaNull() {
		Corrida corrida = calculaResultadoCorrida.calculaTempoTotalProva(null, listaLinhas);
		Assert.assertNull(corrida);
	}
	
	@Test
	public void testCalculaTempoTotalProvaComRetorno() {
		Corrida corrida = calculaResultadoCorrida.calculaTempoTotalProva(new Corrida(), listaLinhas);
		Assert.assertNotNull(corrida.getTempoTotal());
	}
	
	@Test
	public void testCalculaPosicaoCorridaNull() {
		Corrida corrida = calculaResultadoCorrida.calculaPosicao(null);
		Assert.assertNull(corrida);
	}
	
}
