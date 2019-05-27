package dcomp.ifma.lbd.lab03.wesley.modelo;

public class Cliente {
	
	
	private int idCliente;
	private final String nomeCliente;
	private final String enderecoCliente;
	private final String contatoCliente;
	

	public Cliente(String nomeCliente, String enderecoCliente, String contatoCliente) {		
			
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.contatoCliente = contatoCliente;
		
	}
	
	
	public int getIdCliente() {
		return this.idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNomeCliente() {
		return this.nomeCliente;
	}
	
	
	public String getEnderecoCliente() {
		return this.enderecoCliente;
	}
	
	
	public String getContatoCliente() {
		return this.contatoCliente;
	}
	
	
	@Override
	public String toString() {
		
		return "\n\nIdCliente: " + getIdCliente() + "\nNome: " + getNomeCliente() + "\nEndereço: " + getEnderecoCliente() + "\nContato: " + getContatoCliente() + "\n\n" ;
	
	}
	

}
