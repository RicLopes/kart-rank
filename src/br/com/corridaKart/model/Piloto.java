package br.com.corridaKart.model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Piloto extends Pessoa implements Comparable<Piloto> {
	
	private int posicaoChegada;
	
	private String codigoPiloto;
	
	private String horaChegada;
	
	private List<Volta> voltas;

	public Piloto comNome(String nome) {
		this.setNome(nome);
		return this;
	}
	
	public Piloto comCodigoPiloto(String codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
		return this;
	}

	public Piloto comPosicaoChegada(int posicaoChegada) {
		this.posicaoChegada = posicaoChegada;
		return this;
	}
	
	public Piloto comHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
		return this;
	}
	
	public Piloto comVolta(Volta volta) {
		if (voltas == null) {
			voltas = new ArrayList<>();
		}
		voltas.add(volta);
		return this;
	}
	
	public int getPosicaoChegada() {
		return posicaoChegada;
	}

	public void setPosicaoChegada(int posicaoChegada) {
		this.posicaoChegada = posicaoChegada;
	}

	public String getCodigoPiloto() {
		return codigoPiloto;
	}

	public void setCodigoPiloto(String codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
	}

	public String getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}

	public List<Volta> getVoltas() {
		return voltas;
	}

	public void setVoltas(List<Volta> voltas) {
		this.voltas = voltas;
	}

	public String getMelhorTempo() {
		Collections.sort(this.voltas);
		Volta volta = this.voltas.get(0);
		return volta.getTempoVolta();
	}
	
	public int getMelhorVolta() {
		Collections.sort(this.voltas);
		Volta volta = this.voltas.get(0);
		return volta.getNumeroVolta();
	}
	
	public String getVelocidadeMedia() {
		DecimalFormat df = new DecimalFormat("#.000");
		Double velocidadeMediaCorrida = 0.0;
		for (Volta volta : voltas) {
			velocidadeMediaCorrida += volta.getVelocidadeMediaVolta();
		}
		return df.format(velocidadeMediaCorrida/voltas.size());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPiloto == null) ? 0 : codigoPiloto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Piloto other = (Piloto) obj;
		if (codigoPiloto == null) {
			if (other.codigoPiloto != null) {
				return false;
			}	
		} else if (!codigoPiloto.equals(other.codigoPiloto)) {
			return false;
		}	
		return true;
	}
	
	@Override
	public int compareTo(Piloto piloto) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
		
		try {
			long tempo1 = format.parse(this.horaChegada).getTime();
			long tempo2 = format.parse(piloto.getHoraChegada()).getTime();
			
			if (tempo1 < tempo2 && this.voltas.size() >= piloto.getVoltas().size()) {
	            return -1;
	        }
	        if (tempo1 > tempo2 && this.voltas.size() <= piloto.getVoltas().size()) {
	            return 1;
	        }
        } catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
