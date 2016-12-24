package br.com.corridaKart.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.corridaKart.model.Corrida;
import br.com.corridaKart.model.Piloto;
import br.com.corridaKart.model.Volta;
import br.com.corridaKart.util.Constante;

public class TestCorrida {

	private Corrida corrida = new Corrida();

	@Test
	public void testAdicionaPilotoListaVazia() {
		corrida.adicionaPiloto(getPilotoFake());
		Assert.assertTrue(!corrida.getPilotos().isEmpty());
	}

	@Test
	public void testAdicionaPilotoExistenteNaLista() {
		corrida.adicionaPiloto(getPilotoFake());
		corrida.adicionaPiloto(getPilotoFake());
		Assert.assertTrue(corrida.getPilotos().size() == 1);
	}

	public Piloto getPilotoFake() {
		return new Piloto().comCodigoPiloto(Constante._038).comNome(Constante.F_MASSA)
				.comHoraChegada(Constante._23_49_08_277).comVolta(new Volta());
	}

}
