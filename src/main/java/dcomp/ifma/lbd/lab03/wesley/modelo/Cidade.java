package dcomp.ifma.lbd.lab03.wesley.modelo;

public class Cidade {
	
	private int idCidade;
	private final String nomeCidade;
	private final String ufCidade;
	private double taxaCidade;
	
	
	public Cidade(String nomeCidade, String ufCidade, double taxaCidade) {
	
		this.nomeCidade = nomeCidade;
		this.ufCidade = ufCidade;
		this.taxaCidade = taxaCidade;
		
	}
	
	
	public int getIdCidade() {
		return this.idCidade;
	}
	
	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}
	
	
	public String getNomeCidade() {
		return this.nomeCidade;
	}
	
	
	public String getUfCidade() {
		return this.ufCidade;
	}
	
	
	public double getTaxaCidade() {
		return this.taxaCidade;
	}
	
	public void setTaxaCidade(double taxaCidade) {
		this.taxaCidade = taxaCidade;
	}
	

	@Override
	public String toString() {
		
		
		return "\n\nIdCidade: " + getIdCidade() + "\nNome: " + getNomeCidade() + "\nUfCidade: " + getUfCidade() + "\nTaxa: " + getTaxaCidade() + "\n\n" ;
	
			
	}
	
	
	

}
