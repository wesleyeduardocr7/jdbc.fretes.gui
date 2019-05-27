
package dcomp.ifma.edu.lbd.lab03.wesley.controle;

import dcomp.ifma.lbd.lab03.wesley.dao.CidadeDAO;
import dcomp.ifma.lbd.lab03.wesley.dao.ClienteDAO;
import dcomp.ifma.lbd.lab03.wesley.dao.FreteDAO;
import dcomp.ifma.lbd.lab03.wesley.infra.Database;
import dcomp.ifma.lbd.lab03.wesley.modelo.Cidade;
import dcomp.ifma.lbd.lab03.wesley.modelo.Cliente;
import dcomp.ifma.lbd.lab03.wesley.modelo.Frete;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WtiDev
 */
public class ControleFrete {
    
    
     Connection conexao = Database.getConnection();
     FreteDAO freteDAO = new FreteDAO(conexao);
     
     
     public void InsereFrete(JTextField jTextField16, JTextField jTextField17, JTextField jTextField13,
        JTextField jTextField15, ClienteDAO clienteDAO, CidadeDAO cidadeDAO, JTextField jTextField19, JTextField jTextField20, JTable jTable6){
         
           
        if(jTextField16.getText().equals("")){
            
             JOptionPane.showMessageDialog(null, "Id Cidade Vazio!");
             jTextField16.requestFocus();
            
        }else if( jTextField17.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Id Cliente Vazio!");
            jTextField17.requestFocus();
            
        }else if( jTextField13.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Descrição Vazio!");
            jTextField13.requestFocus();
            
        }else if( jTextField15.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Peso Vazio!");
            jTextField15.requestFocus();
            
        }else{
            
            final double taxaFrete = 10;
            int idCidade = Integer.parseInt(jTextField16.getText());
            int idCliente = Integer.parseInt(jTextField17.getText());
            String descricao = jTextField13.getText();
            double peso = Double.parseDouble(jTextField15.getText());

            try {
                Cidade cidade = cidadeDAO.BuscaCidadeCodigo(idCidade);
                Cliente cliente = clienteDAO.buscaClienteCodigo(idCliente);
                
                if(cidade != null && cliente != null){
                    
                    double valorTotalFrete = (peso*taxaFrete + (cidade.getTaxaCidade()));
                    
                    Frete frete = new Frete(cidade, cliente, descricao, peso, valorTotalFrete);
                    
                    freteDAO.salvaFrete(frete);
                    
                    JOptionPane.showMessageDialog(null, "Frete Adicionado Com Sucesso!");
                    
                    DefaultTableModel dtm = (DefaultTableModel) jTable6.getModel();
                    dtm.addRow(new String[]{
                     Integer.toString(frete.getIdFrete()),
                     Integer.toString(frete.getCidade().getIdCidade()),
                     frete.getCidade().getNomeCidade(),
                      Integer.toString(frete.getCliente().getIdCliente()),
                      frete.getCliente().getNomeCliente(),
                      frete.getDescricaoFrete(),
                      Double.toString(frete.getPesoFrete()),
                      Double.toString(frete.getValorFrete())
                            
                    });                    
                    
                    
                    jTextField16.setText("");
                    jTextField17.setText("");
                    jTextField13.setText("");
                    jTextField19.setText("");
                    jTextField20.setText("");
                    jTextField15.setText("");
                    
                }else{
                    
                     JOptionPane.showMessageDialog(null, "Parametros de Ids Inconsistentes!");
                    
                }
                
               
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
           
            
        }
         
     }
     
     public void cancela(JTextField jTextField16, JTextField jTextField17,JTextField jTextField13,JTextField jTextField19,
     JTextField jTextField20,JTextField jTextField15, JTable jTable6){
         
         
        
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField13.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField15.setText("");
        
         DefaultTableModel dtm = (DefaultTableModel) jTable6.getModel();

        dtm.setNumRows(0);
         
     }
     
     public void buscaCidade(JTextField jTextField16, CidadeDAO cidadeDAO, JTextField jTextField19){
         
         try {
        
            if(jTextField16.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo iDCidade Vazio!!");
                 jTextField16.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField16.getText());
                        
                 Cidade cidade = cidadeDAO.BuscaCidadeCodigo(codigo);


                if(cidade==null){               
                    JOptionPane.showMessageDialog(null, "Cidade Não Encontrado!");                 
                }else{

                    jTextField19.setText(cidade.getNomeCidade());

                }
             
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
     
     
     public void buscaCliente(JTextField jTextField17, ClienteDAO clienteDAO, JTextField jTextField20){
         
          try {
        
            if(jTextField17.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo Código Vazio!!");
                 jTextField17.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField17.getText());
                        
                 Cliente cliente = clienteDAO.buscaClienteCodigo(codigo);


                if(cliente==null){               
                    JOptionPane.showMessageDialog(null, "Cliente Não Encontrado!");                 
                }else{

                    jTextField20.setText(cliente.getNomeCliente());

                }
             
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
     }
     
     public void buscaFrete(JTextField jTextField14, JTable jTable8){
         
           try {
        
            if(jTextField14.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo Código Vazio!!");
                 jTextField14.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField14.getText());
                        
                 Frete frete = freteDAO.buscaFreteCodigo(codigo);

                if(frete==null){               
                    JOptionPane.showMessageDialog(null, "Frete Não Encontrado!");                 
                }else{

                     DefaultTableModel dtmAux = (DefaultTableModel) jTable8.getModel();
                    
                     dtmAux.setNumRows(0);

                     DefaultTableModel dtm = (DefaultTableModel) jTable8.getModel();
                      
                      dtm.addRow(new String[]{
                      Integer.toString(frete.getIdFrete()),
                      Integer.toString(frete.getCidade().getIdCidade()),
                      frete.getCidade().getNomeCidade(),
                      Integer.toString(frete.getCliente().getIdCliente()),
                      frete.getCliente().getNomeCliente(),
                      frete.getDescricaoFrete(),
                      Double.toString(frete.getPesoFrete()),
                      Double.toString(frete.getValorFrete())
                            
                    }); 
                }            
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
         
     }
     
     public void limpaFrete(JTable jTable8, JTextField jTextField14){
          DefaultTableModel dtmAux = (DefaultTableModel) jTable8.getModel();                    
          dtmAux.setNumRows(0);
          jTextField14.setText("");
     }
     
     public void buscaFresteDeUmCliente(JTextField jTextField18, ClienteDAO clienteDAO, JTable jTable9){
         
        
        try {
        
            if(jTextField18.getText().equals("")){
                
                 JOptionPane.showMessageDialog(null, "Campo Código Vazio!!");
                 jTextField18.requestFocus();
                
            }else{
                
                
                 int codigo = Integer.parseInt(jTextField18.getText());
                        
                 Cliente cliente = clienteDAO.buscaClienteCodigo(codigo);


                if(cliente==null){               
                    JOptionPane.showMessageDialog(null, "Cliente Não Encontrado!");                 
                }else{

                     ArrayList<Frete> resultado = (ArrayList<Frete>) freteDAO.listaFretesDeClientes(codigo);
                     
                     DefaultTableModel dtmAux = (DefaultTableModel) jTable9.getModel();
                    
                     dtmAux.setNumRows(0);                     
                   
                     DefaultTableModel dtm = (DefaultTableModel) jTable9.getModel();
                     
                     resultado.forEach((frete) -> {
                         dtm.addRow(new String[]{
                             Integer.toString(frete.getIdFrete()),
                             Integer.toString(frete.getCidade().getIdCidade()),
                             frete.getCidade().getNomeCidade(),
                             Integer.toString(frete.getCliente().getIdCliente()),
                             frete.getCliente().getNomeCliente(),
                             frete.getDescricaoFrete(),
                             Double.toString(frete.getPesoFrete()),
                             Double.toString(frete.getValorFrete())
                                 
                         });
                     });
                     
                   
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
     }
     
     public void retornaFreteComMaiorValor(JTable jTable10){
         
            DefaultTableModel dtm = (DefaultTableModel) jTable10.getModel();

             dtm.setNumRows(0);

        try {
            
            Frete frete = freteDAO.recuperaFreteDeMaiorVaor();
            
            System.out.println(frete.toString());
            
           dtm.addRow(new String[]{Integer.toString(frete.getIdFrete()),Integer.toString(frete.getCidade().getIdCidade()),
                frete.getCidade().getNomeCidade(),
                    
             Integer.toString(frete.getCliente().getIdCliente()),frete.getCliente().getNomeCliente(),
             
             frete.getDescricaoFrete(),Double.toString(frete.getPesoFrete()),Double.toString(frete.getValorFrete())});
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          
         
     }
     
     public void limpaTelaFreteComMaiorValor(JTable jTable10){
         DefaultTableModel dtm = (DefaultTableModel) jTable10.getModel();
         dtm.setNumRows(0);
     }
     
     public void retornaCidadeComMaiorQuantidadeDeFretes(JTable jTable11){
         
          DefaultTableModel dtm = (DefaultTableModel) jTable11.getModel();

            dtm.setNumRows(0);

        try {
            
           Cidade cidade = freteDAO.retornaCidadeComMaiorQuantidadeDeFretes();
            
            dtm.addRow(new String[]{Integer.toString(cidade.getIdCidade()),cidade.getNomeCidade(),cidade.getUfCidade(),Double.toString(cidade.getTaxaCidade()),Integer.toString(freteDAO.retornaQuantidadeDeFretesDeUmaCidade())});
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
             
         
     }
     
     public void retornaTodosOsFretes(JTable jTable12){
         
           DefaultTableModel dtmAux = (DefaultTableModel) jTable12.getModel();

        dtmAux.setNumRows(0);                     

        DefaultTableModel dtm = (DefaultTableModel) jTable12.getModel();
                     
        try {
            freteDAO.listaFretes().forEach((frete) -> {
                dtm.addRow(new String[]{
                    Integer.toString(frete.getIdFrete()),
                    Integer.toString(frete.getCidade().getIdCidade()),
                    frete.getCidade().getNomeCidade(),
                    Integer.toString(frete.getCliente().getIdCliente()),
                    frete.getCliente().getNomeCliente(),
                    frete.getDescricaoFrete(),
                    Double.toString(frete.getPesoFrete()),
                    Double.toString(frete.getValorFrete())
                        
                });
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                     
        
         
     }
     
     
     public void limpaTelaTodosOsFretes(JTable jTable12){
         
          DefaultTableModel dtmAux = (DefaultTableModel) jTable12.getModel();
        dtmAux.setNumRows(0);   
         
     }
    
}
