package dcomp.ifma.lbd.lab03.wesley.modelo;


public class Frete {
	
	private int idFrete;
	private final Cidade cidade;
	private final Cliente cliente;
	private final String descricaoFrete;
	private final double pesoFrete;
	private final double valorFrete;
	
	
	public Frete( Cidade cidade, Cliente cliente, String descricaoFrete,double pesoFrete, double valorFrete) {
	
		this.cidade = cidade;
		this.cliente = cliente;
		this.descricaoFrete = descricaoFrete;
		this.pesoFrete = pesoFrete;
		this.valorFrete = valorFrete;
		
	}
	
	
	public int getIdFrete() {
		return this.idFrete;
	}
	
	public String getDescricaoFrete() {
		return this.descricaoFrete;
	}
	
	public double getPesoFrete() {
		return this.pesoFrete;
	}
	
	public void setIdFrete(int idFrete) {
		this.idFrete = idFrete;
	}
	
	
	public double getValorFrete() {
		return this.valorFrete;
	}
	



	public Cidade getCidade() {
		return this.cidade;
	}



	public Cliente getCliente() {
		return this.cliente;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFrete;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frete other = (Frete) obj;
		if (idFrete != other.idFrete)
			return false;
		return true;
	}

	
	@Override
	public String toString() {		
		
		return "\nidFrete: " + getIdFrete() + "\nidCidade: " + getCidade().getIdCidade() + "\nidCliente: " 
		+ getCliente().getIdCliente() + "\nDescrição: " + getDescricaoFrete() + "\nPeso: " + getPesoFrete() +  "\nValor: " + getValorFrete() + "\n\n";
	
	}
	

}
