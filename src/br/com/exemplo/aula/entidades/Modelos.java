package br.com.exemplo.aula.entidades;

public class Modelos{
	
	//id tipo String pois o padrão da tabela FIPE tem hifem. 
	private String id;
	private String nome;
	private int marca;
	private String tipo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}