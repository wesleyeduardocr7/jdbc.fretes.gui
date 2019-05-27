package dcomp.ifma.edu.lbd.lab03.wesley.controle;

import dcomp.ifma.lbd.lab03.wesley.dao.CidadeDAO;
import dcomp.ifma.lbd.lab03.wesley.infra.Database;
import dcomp.ifma.lbd.lab03.wesley.modelo.Cidade;
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
public class ControleCidade {
    
    
     Connection conexao = Database.getConnection();
     CidadeDAO cidadeDAO = new CidadeDAO(conexao);
     
     
     public void insereCidade(JTextField jTextField6, JTextField jTextField7, JTextField jTextField8){
         
         
              
        if(jTextField6.getText().equals("") ||  jTextField7.getText().equals("") || jTextField8.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Preencha todos os Dados");
            
        }else{
                
            String nome  =jTextField6.getText();
            String estado = jTextField7.getText();
            double taxa = Double.parseDouble(jTextField8.getText());
            
            Cidade cidade = new Cidade(nome, estado, taxa);
            
            try {
                cidadeDAO.salvaCidade(cidade);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            JOptionPane.showMessageDialog(null, "Cidade Inserido Com Sucesso");
            
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            
        }
         
         
     }
    
     
     public void buscaCidade(JTextField jTextField9,JTextField jTextField10, JTable jTable3 ){
          
        try {
        
            if(!jTextField9.getText().equals("") && jTextField10.getText().equals("")){
                
                     
                    int codigo = Integer.parseInt(jTextField9.getText());

                    Cidade cidade = cidadeDAO.BuscaCidadeCodigo(codigo);

                   if(cidade==null){               
                       JOptionPane.showMessageDialog(null, "Cidade Não Encontrado!");                 
                   }else{


                       DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();

                       dtm.setNumRows(0);
                       
                       dtm.addRow(new String[]{Integer.toString(cidade.getIdCidade()),cidade.getNomeCidade(),cidade.getUfCidade(),Double.toString(cidade.getTaxaCidade())});                    
                                              
                        jTextField9.setText("");
                        jTextField10.setText("");
                    
                } 
                
            }else if(jTextField9.getText().equals("") && !jTextField10.getText().equals("")){
                
                
                   String nome = jTextField10.getText();

                    Cidade cidade = cidadeDAO.BuscaCidadeNome(nome);

                   if(cidade==null){               
                       JOptionPane.showMessageDialog(null, "Cidade Não Encontrado!");                 
                   }else{


                       DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();

                       dtm.setNumRows(0);
                       
                       dtm.addRow(new String[]{Integer.toString(cidade.getIdCidade()),cidade.getNomeCidade(),cidade.getUfCidade(),Double.toString(cidade.getTaxaCidade())});                    
                       
                       
                jTextField9.setText("");
                jTextField10.setText("");

                } 
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Pesquise Apenas por Um dos Campos!"); 
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
     
     
     public void limpaTela(JTable jTable3, JTextField jTextField9, JTextField jTextField10){
         
           DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setNumRows(0);        
            jTextField9.setText("");
            jTextField10.setText("");
         
     }
     
     public void searchCidade(JTextField jTextField11, JTable jTable4){
         
          try {
        
            if(jTextField11.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo Código Vazio!!");
                 jTextField11.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField11.getText());
                        
                 Cidade cidade = cidadeDAO.BuscaCidadeCodigo(codigo);


                if(cidade==null){               
                    JOptionPane.showMessageDialog(null, "Cidade Não Encontrado!");                 
                }else{

                    DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();

                    dtm.setNumRows(0);

                    dtm.addRow(new String[]{Integer.toString(cidade.getIdCidade()),cidade.getNomeCidade(),cidade.getUfCidade(),Double.toString(cidade.getTaxaCidade())});                    
                   
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
         
     }
     
     
     public void uptadeCidade(JTextField jTextField11,JTextField jTextField12, JTable jTable4 ){
         
          
        if(jTextField11.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Id Cidade Está Vazio!"); 
            jTextField11.requestFocus();
        }else{
            
            
            if(jTextField12.getText().equals("")){
            
               JOptionPane.showMessageDialog(null, "Campo Valor da Taxa Vazio!"); 
            
        }else{
            
            
            double taxaUptade = Double.parseDouble(jTextField12.getText());
                       
             int codigo = Integer.parseInt(jTextField11.getText());
                        
            try {
                Cidade cidade = cidadeDAO.BuscaCidadeCodigo(codigo);
                
                
                if(cidade==null){               
                    JOptionPane.showMessageDialog(null, "Cidade Não Encontrado!");                 
                }else{

                    if(jTextField11.getText().equals("")){
                        
                         JOptionPane.showMessageDialog(null, "Infome o Id da Cidade!"); 
                         jTextField11.requestFocus();
                        
                    }else{
                        
                         cidadeDAO.atualizaCidade(codigo,taxaUptade);
                    
                        JOptionPane.showMessageDialog(null, "Tabela Atualizada!"); 

                        jTextField11.setText("");
                        jTextField12.setText("");

                        DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();

                        dtm.setNumRows(0);  

                        atualizaTabelaCidade(jTable4);                   
                        
                    }
                    
                   
                }                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }           
            
        }           
            
            
        }
        
         
         
     }
     
     
    public void atualizaTabelaCidade(JTable table){
                            	            
        try {
                       
             DefaultTableModel dtm = (DefaultTableModel) table.getModel();
             
             dtm.setNumRows(0);
                          
             for (Cidade cidade : cidadeDAO.listaCidades()) {
              
                 dtm.addRow(new String[]{Integer.toString(cidade.getIdCidade()),cidade.getNomeCidade(),cidade.getUfCidade(),Double.toString(cidade.getTaxaCidade())});                    
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
     
    
}
