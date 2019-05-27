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
import dcomp.ifma.lbd.lab03.wesley.modelo.Cliente;
import dcomp.ifma.lbd.lab03.wesley.modelo.Frete;


public class FreteDAO {
	

	private final Connection connection;
	private static Scanner sc;
	

	public FreteDAO(Connection connection) {		
		this.connection = connection;
	}
	
	
	public Frete salvaFrete(Frete f) throws SQLException {
		

		String sql = "insert into frete (id_Cidade, id_Cliente, descricaoFrete, pesoFrete, valorFrete) values (?, ?, ?, ?, ?)";
		
		
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setInt(1, f.getCidade().getIdCidade());
			statement.setInt(2, f.getCliente().getIdCliente());
			statement.setString(3, f.getDescricaoFrete());
			statement.setDouble(4, f.getPesoFrete());
			statement.setDouble(5, f.getValorFrete());	
						
			statement.execute();
			
			try (ResultSet keys = statement.getGeneratedKeys()) {
				
				keys.next();
				int id = keys.getInt(1);
				f.setIdFrete(id);
				
			}
		}
		return f;

	}
	
	
	public List<Frete> listaFretes() throws SQLException {
				
		List<Frete> listaFretes = new ArrayList<>();

		String sql = "select * from frete";

		try (PreparedStatement statment = connection.prepareStatement(sql)) {
			
			ClienteDAO clienteDAO = new ClienteDAO(connection);
			CidadeDAO cidadeDAO = new CidadeDAO(connection);
			
			ResultSet resultSet = statment.executeQuery(sql);
			
			
			while (resultSet.next()) {

				int id_Cidade = resultSet.getInt("id_Cidade");
				int id_Cliente = resultSet.getInt("id_Cliente");
				String descricaoFrete = resultSet.getString("descricaoFrete");
				double pesoFrete = resultSet.getDouble("pesoFrete");
				double valorFrete = resultSet.getDouble("valorFrete");
				int id = resultSet.getInt("idFrete");
								
				Cidade ci = cidadeDAO.BuscaCidadeCodigo(id_Cidade);
				Cliente cl = clienteDAO.buscaClienteCodigo(id_Cliente);
				
				if(ci != null && cl != null) {
					
					Frete frete = new Frete(ci, cl, descricaoFrete, pesoFrete, valorFrete);
					
					frete.setIdFrete(id);
					
					listaFretes.add(frete);
					
				}else {
					
					System.out.println("Chaves Incossistentes!\n");
					
					return null;
					
				}
				

			}
			
			
		}
		
		return listaFretes;

	}
	
	
	
	public Frete buscaFreteCodigo(int codigo) throws SQLException {
		
		ArrayList<Frete> listaProcuraFrete = (ArrayList<Frete>) listaFretes();

		for (Frete c : listaProcuraFrete) {

			if (codigo == c.getIdFrete()) {
			
			    return c;

			}

		}

				
		return null;
		
		
	}
	
	
public Frete recuperaFreteDeMaiorVaor() throws SQLException {
				 		
		 String sql = "select idFrete , descricaoFrete, valorFrete from frete where valorFrete = ( select max(valorFrete) from frete )";
		 
                 Frete freteAux = null;
		 
		 try (PreparedStatement statment = connection.prepareStatement(sql)) {
			
												
                    ResultSet resultSet  =  statment.executeQuery(sql);
						
			
                    while (resultSet.next()) {

                            int id = resultSet.getInt("idFrete");

                            freteAux = buscaFreteCodigo(id);
                            
                        }		

                            if(freteAux != null) {

                                    return freteAux;

                            }else {

                                    return null;

                            }	
	  }
		 
		 
}
		
	
	
public ArrayList<Frete> listaFretesDeClientes(int codigo) throws SQLException {
				
		 ArrayList<Frete> listaFretesDeClientes = new ArrayList<>();
					
		 String sql = "select idFrete, id_Cidade, id_Cliente, descricaoFrete, pesoFrete, valorFrete from frete where id_Cliente=" + codigo;			 
		try (PreparedStatement statment = connection.prepareStatement(sql)) {
			
			 CidadeDAO cidadeDAO = new CidadeDAO(connection);
			 ClienteDAO clienteDAO = new ClienteDAO(connection);
			 ResultSet resultSet  =  statment.executeQuery(sql);
                          					
			while (resultSet.next()) {
				
				int id_Cidade = resultSet.getInt("id_Cidade");
				int id_Cliente = resultSet.getInt("id_Cliente");
				String descricaoFrete = resultSet.getString("descricaoFrete");
				double pesoFrete = resultSet.getDouble("pesoFrete");
				double valorFrete = resultSet.getDouble("valorFrete");
				int id = resultSet.getInt("idFrete");
								
				
				Cidade cidade = cidadeDAO.BuscaCidadeCodigo(id_Cidade);
				Cliente cliente = clienteDAO.buscaClienteCodigo(id_Cliente);
				
				if(cidade != null && cliente != null) {
					
					Frete frete = new Frete(cidade, cliente, descricaoFrete, pesoFrete, valorFrete);
					
					frete.setIdFrete(id);
					
					listaFretesDeClientes.add(frete);
					
				}else {
					
					System.out.println("Chaves Incossistentes!\n");
					
					return null;
					
				}				

			}
			
			
		}
             
		
		return listaFretesDeClientes;

	}
	
		
	
	public Cidade retornaCidadeComMaiorQuantidadeDeFretes() throws SQLException {
		
		
		Cidade cidade = null;
 		
		 String sql = "select id_Cidade, count(frete.idFrete) as qtdFretesAssociados from frete frete group by id_Cidade order by qtdFretesAssociados desc limit 1";
		 
		 
		 try (PreparedStatement statment = connection.prepareStatement(sql)) {
				
                        ResultSet resultSet = statment.executeQuery(sql);


                        while (resultSet.next()) {

                            int idCidade = resultSet.getInt("id_Cidade");
                            
                            CidadeDAO cidadeDAOAux = new  CidadeDAO(connection);
                            
                            cidade = cidadeDAOAux.BuscaCidadeCodigo(idCidade);
                                
                        }

                        if(cidade != null) {

                                return cidade;

                        }else {

                                return null;

                        }	
				
		 }
				
	}
	
        
        public int retornaQuantidadeDeFretesDeUmaCidade() throws SQLException {
		
 		
		 String sql = "select count(frete.idFrete) as qtdFretesAssociados from frete frete group by id_Cidade order by qtdFretesAssociados desc limit 1";
		 
                 int qtd = 0;
                 
		 try (PreparedStatement statment = connection.prepareStatement(sql)) {
				
                        ResultSet resultSet = statment.executeQuery(sql);


                        while (resultSet.next()) {

                            qtd = resultSet.getInt("qtdFretesAssociados");
                                                            
                        }
                        
                        return qtd;
				
		 }
				
	}
	


}
