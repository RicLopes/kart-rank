package br.com.corridaKart.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Volta implements Comparable<Volta> {

	private int numeroVolta;
	
	private String tempoVolta;
	
	private Double velocidadeMediaVolta;
	
	public Volta comNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
		return this;
	}
	
	public Volta comTempoVolta(String tempoVolta) {
		this.tempoVolta = tempoVolta;
		return this;
	}
	
	public Volta comVelocidadeMediaVolta(Double velocidadeMediaVolta) {
		this.velocidadeMediaVolta = velocidadeMediaVolta;
		return this;
	}
	
	public int getNumeroVolta() {
		return numeroVolta;
	}

	public void setNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
	}

	public String getTempoVolta() {
		return tempoVolta;
	}

	public void setTempoVolta(String tempoVolta) {
		this.tempoVolta = tempoVolta;
	}

	public Double getVelocidadeMediaVolta() {
		return velocidadeMediaVolta;
	}

	public void setVelocidadeMediaVolta(Double velocidadeMediaVolta) {
		this.velocidadeMediaVolta = velocidadeMediaVolta;
	}

	@Override
	public int compareTo(Volta volta) {
		SimpleDateFormat format = new SimpleDateFormat("m:ss.SSS");
		
		try {
			long tempo1 = format.parse(this.tempoVolta).getTime();
			long tempo2 = format.parse(volta.getTempoVolta()).getTime();
			if (tempo1 < tempo2) {
	            return -1;
	        }
	        if (tempo1 > tempo2) {
	            return 1;
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
}
