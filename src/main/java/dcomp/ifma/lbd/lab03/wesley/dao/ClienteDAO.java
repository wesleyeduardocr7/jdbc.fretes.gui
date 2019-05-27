package dcomp.ifma.lbd.lab03.wesley.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dcomp.ifma.lbd.lab03.wesley.modelo.Cliente;


public class ClienteDAO {
	
	
	private final Connection connection;
	private static Scanner sc;
	

	public ClienteDAO(Connection connection) {		
		this.connection = connection;
	}
	
	
	public Cliente salvaCliente(Cliente cliente) throws SQLException {

		String sql = "insert into Cliente (nomeCliente, enderecoCliente, contatoCliente) values (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, cliente.getNomeCliente());
			statement.setString(2, cliente.getEnderecoCliente());
			statement.setString(3, cliente.getContatoCliente());

			statement.execute();

			try (ResultSet keys = statement.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt(1);
				cliente.setIdCliente(id);
			}
		}
		return cliente;

	}
	
	
	public List<Cliente> listaClientes() throws SQLException {

		String sql = "select * from cliente";

		try (PreparedStatement statment = connection.prepareStatement(sql)) {
			
			ResultSet resultSet = statment.executeQuery(sql);
			
			List<Cliente> listaClientes = new ArrayList<>();
			
			while (resultSet.next()) {

				String nomeCliente = resultSet.getString("nomeCliente");
				String enderecoCliente = resultSet.getString("enderecoCliente");
				String contatoCliente = resultSet.getString("contatoCliente");
				int id = resultSet.getInt("idCliente");
				Cliente cliente = new Cliente(nomeCliente, enderecoCliente, contatoCliente);
				cliente.setIdCliente(id);
				
				listaClientes.add(cliente);
			}
			
			return listaClientes;
		}

	}
	
	
	public Cliente buscaClienteCodigo(int codigo) throws SQLException {
			
		
		ArrayList<Cliente> listaProcuraCodigo = (ArrayList<Cliente>) listaClientes();

		for (Cliente c : listaProcuraCodigo) {

			if (codigo == c.getIdCliente()) {
			
				return c;

			}

		}

					
		return null;
		
		
	}
	
		

}
