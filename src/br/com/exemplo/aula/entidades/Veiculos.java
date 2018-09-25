package br.com.exemplo.aula.entidades;

public class Veiculos extends AbstractId{
	
	private Long idModelo;
	private Long idAnoModelo;
	private Long idMarca;
	private String placa;
	private String anoFabricacao;
	private String tipo;
	private String dataCadastro;
	
	
	public Long getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(Long idModelo) {
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
	public Long getIdAnoModelo() {
		return idAnoModelo;
	}
	public void setIdAnoModelo(Long idAnoModelo) {
		this.idAnoModelo = idAnoModelo;
	}
	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	
	
}
