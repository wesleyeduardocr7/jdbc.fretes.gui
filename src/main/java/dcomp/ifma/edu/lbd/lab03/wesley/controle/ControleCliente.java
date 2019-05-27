
package dcomp.ifma.edu.lbd.lab03.wesley.controle;

import dcomp.ifma.lbd.lab03.wesley.dao.ClienteDAO;
import dcomp.ifma.lbd.lab03.wesley.infra.Database;
import dcomp.ifma.lbd.lab03.wesley.modelo.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WtiDev
 */
public class ControleCliente {
 
    Connection conexao = Database.getConnection();
    ClienteDAO clienteDAO = new ClienteDAO(conexao);
 
    public void insereCliente(JTextField jTextField2, JTextField jTextField3, JTextField jTextField4){
      
        if(jTextField2.getText().equals("") ||  jTextField3.getText().equals("") || jTextField4.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Preencha todos os Dados");
            
        }else{
                
            String nome  = jTextField2.getText();
            String endereco = jTextField3.getText();
            String contato = jTextField4.getText();
            
            Cliente cliente = new Cliente(nome, endereco, contato);
            
            try {
                clienteDAO.salvaCliente(cliente);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            JOptionPane.showMessageDialog(null, "Cliente Inserido Com Sucesso");
            
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            
        }
        
    }
    
    public void retornaTodosOsClientes(JTable jTable1){
        
        try {
                       
             DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
             
             dtm.setNumRows(0);
                          
             for (Cliente cliente : clienteDAO.listaClientes()) {
              
                 dtm.addRow(new String[]{Integer.toString(cliente.getIdCliente()),cliente.getNomeCliente(),cliente.getEnderecoCliente(),cliente.getContatoCliente()});                    
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
       
    
    public void limpaTelaClientes(JTable jTable1){
        
         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
         dtm.setNumRows(0);
        
    }
    
    
    public void buscaCliente(JTextField jTextField5, JTable jTable2){
        
        
         try {
        
            if(jTextField5.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo Código Vazio!!");
                 jTextField5.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField5.getText());
                        
                 Cliente cliente = clienteDAO.buscaClienteCodigo(codigo);


                if(cliente==null){               
                    JOptionPane.showMessageDialog(null, "Cliente Não Encontrado!");                 
                }else{


                    DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                    
                     dtm.setNumRows(0);

                    dtm.addRow(new String[]{Integer.toString(cliente.getIdCliente()),cliente.getNomeCliente(),cliente.getEnderecoCliente(),cliente.getContatoCliente()});                    


                }
             
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    
    public void limpaTelaTodosOsClientes(JTable jTable2, JTextField jTextField5){
        
         DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
         dtm.setNumRows(0);
         jTextField5.setText("");
        
    }
        
}
