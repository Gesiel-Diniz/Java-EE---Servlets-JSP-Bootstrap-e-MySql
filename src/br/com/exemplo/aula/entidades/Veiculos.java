package br.com.exemplo.aula.entidades;

public class Veiculos extends AbstractId{
	
	private Modelos idModelo;
	private AnoModelos idAnoModelo;
	private Marcas idMarca;
	private String placa;
	private String anoFabricacao;
	private String tipo;
	private String dataCadastro;
	
	
	public Modelos getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(Modelos idModelo) {
		this.idModelo = idModelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public AnoModelos getIdAnoModelo() {
		return idAnoModelo;
	}
	public void setIdAnoModelo(AnoModelos idAnoModelo) {
		this.idAnoModelo = idAnoModelo;
	}
	public Marcas getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Marcas idMarca) {
		this.idMarca = idMarca;
	}

}