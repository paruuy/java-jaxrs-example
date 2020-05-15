package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	private String nome;
	private long id;
	private int anoDeInicio;
	
	public Projeto(long id, String nome, int anoDeInicio) {
		this.nome = nome;
		this.id = id;
		this.anoDeInicio = anoDeInicio;
	}
	public Projeto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getAnoDeInicio() {
		return anoDeInicio;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}
	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
