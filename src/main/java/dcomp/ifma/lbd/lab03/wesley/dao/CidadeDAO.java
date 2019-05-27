package dcomp.ifma.lbd.lab03.wesley.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dcomp.ifma.lbd.lab03.wesley.modelo.Cidade;

public class CidadeDAO {
	
	

	private final Connection connection;
	private static Scanner sc;

	public CidadeDAO(Connection connection) {		
		this.connection = connection;
	}
	
	
	public Cidade salvaCidade(Cidade Cidade) throws SQLException {

		String sql = "insert into cidade (nomeCidade, ufCidade, taxaCidade) values (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, Cidade.getNomeCidade());
			statement.setString(2, Cidade.getUfCidade());
			statement.setDouble(3, Cidade.getTaxaCidade());

			statement.execute();

			try (ResultSet keys = statement.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt(1);
				Cidade.setIdCidade(id);
			}
		}
		return Cidade;

	}
	
	
	public List<Cidade> listaCidades() throws SQLException {

		String sql = "select * from cidade";

		try (PreparedStatement statment = connection.prepareStatement(sql)) {
			
			ResultSet resultSet = statment.executeQuery(sql);
			
			List<Cidade> listaCidades = new ArrayList<>();
			
			while (resultSet.next()) {

				String nomeCidade = resultSet.getString("nomeCidade");
				String ufCidade = resultSet.getString("ufCidade");
				double taxaCidade = resultSet.getDouble("taxaCidade");
				int id = resultSet.getInt("idCidade");
				Cidade Cidade = new Cidade(nomeCidade, ufCidade, taxaCidade);
				Cidade.setIdCidade(id);
				
				listaCidades.add(Cidade);
			}
			
			return listaCidades;
		}

	}
	
	
	public Cidade BuscaCidadeCodigo(int codigo) throws SQLException {
		

		ArrayList<Cidade> listaProcuraCidadeCodigo = (ArrayList<Cidade>) listaCidades();

		for (Cidade c : listaProcuraCidadeCodigo) {

			if (codigo == c.getIdCidade()) {
						
				
				return c;

			}

		}
	
		return null;
	}
	
public Cidade BuscaCidadeNome(String nome) throws SQLException {
		
	

		ArrayList<Cidade> listaProcuraCidadeNome = (ArrayList<Cidade>) listaCidades();
	
		for (Cidade c : listaProcuraCidadeNome) {
	
			if (nome.equalsIgnoreCase(c.getNomeCidade())) {
				
					
				return c;
	
			}
	
		}
	
		
		return null;

	}
	
	
	public Cidade atualizaCidade(int codigo, double novaTaxa) throws SQLException {

            for (Cidade c : listaCidades()) {

                    if (codigo ==  c.getIdCidade() ) {

                    String sql = "update cidade set taxaCidade = ? where idCidade = ? ";

                    try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                            statement.setDouble(1, novaTaxa);
                            statement.setInt(2, c.getIdCidade());

                            statement.executeUpdate();

                            return c;

                        }                   
                    
                    }

            }			
					
		return null;
	}

	
	
	

}
