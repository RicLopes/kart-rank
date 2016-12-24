package br.com.corridaKart.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.corridaKart.model.Piloto;
import br.com.corridaKart.model.Volta;
import br.com.corridaKart.util.Constante;

public class TestPiloto {

	private static final String _1_03_170 = "1:03.170";
	private static final String _1_02_852 = "1:02.852";
	private Piloto piloto = getPilotoFake();
	
	@Test
	public void testMelhorVoltaDoPiloto() {
		piloto.setVoltas(getVoltaFace());
		Assert.assertTrue(piloto.getMelhorVolta() == 1);
	}
	
	@Test
	public void testMelhorTempoDoPiloto() {
		piloto.setVoltas(getVoltaFace());
		Assert.assertTrue(_1_02_852.equals(piloto.getMelhorTempo()));
	}
	
	@Test
	public void testVelocidadeMediaDoPilodo() {
		piloto.setVoltas(getVoltaFace());
		Assert.assertTrue(piloto.getVelocidadeMedia().equals("44,150"));
	}
	
	
	private Piloto getPilotoFake() {
		return new Piloto().comCodigoPiloto(Constante._038).comNome(Constante.F_MASSA)
				.comHoraChegada(Constante._23_49_08_277);
	}
	
	private List<Volta> getVoltaFace() {
		List<Volta> voltas = new ArrayList<>();
			
		voltas.add(new Volta().comNumeroVolta(1).comTempoVolta(_1_02_852).comVelocidadeMediaVolta(44.275));
		voltas.add(new Volta().comNumeroVolta(2).comTempoVolta(_1_03_170).comVelocidadeMediaVolta(44.025));
		
		return voltas;
	}
	
}
