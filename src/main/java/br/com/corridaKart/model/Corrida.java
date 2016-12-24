package br.com.corridaKart.model;

import java.util.ArrayList;
import java.util.List;

public class Corrida {

	public Corrida() {
		pilotos = new ArrayList<>();
	}
	
	private Piloto pilotoMelhorVolta;
	
	private List<Piloto> pilotos;

	private String tempoTotal;

	public Piloto getPilotoMelhorVolta() {
		return pilotoMelhorVolta;
	}

	public void setPilotoMelhorVolta(Piloto pilotoMelhorVolta) {
		this.pilotoMelhorVolta = pilotoMelhorVolta;
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public String getTempoTotal() {
		return tempoTotal;
	}

	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	
	public Piloto adicionaPiloto(Piloto piloto) {
		if (pilotos.isEmpty()) {
			pilotos.add(piloto);
		} else {
			if (pilotos.contains(piloto)) {
				for (Piloto pilotoInserido : pilotos) {
					if (pilotoInserido.getCodigoPiloto().equals(piloto.getCodigoPiloto())) {
						pilotoInserido.setHoraChegada(piloto.getHoraChegada());
						pilotoInserido.getVoltas().add(piloto.getVoltas().get(0));
					}
				}
			} else {
				pilotos.add(piloto);
			}
		}
		return piloto;
	}
	
}
