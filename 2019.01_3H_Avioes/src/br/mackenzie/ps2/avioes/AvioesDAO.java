/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.ps2.avioes;

import br.mackenzie.ps2.db.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaquim
 */
public class AvioesDAO {
    
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    public AvioesDAO() {
        try {
            Connection conn = ConexaoJDBC.getInstance().getConnection();
            
            String sqlCreate = "INSERT INTO avioes(modelo,preco) VALUES (?,?)";
            String sqlRead   = "SELECT * FROM avioes";
            String sqlUpdate = "UPDATE avioes SET modelo=?, preco=? WHERE id=?";
            String sqlDelete = "DELETE FROM avioes WHERE id=?";
            
            this.stmtC = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlRead);
            this.stmtU = conn.prepareStatement(sqlUpdate);
            this.stmtD = conn.prepareStatement(sqlDelete);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Aviao criar(Aviao aviao) {
        try{
            this.stmtC.setString(1, aviao.getModelo());
            this.stmtC.setDouble(2, aviao.getPreco());
            
            if (this.stmtC.executeUpdate() == 0) 
                return null;
            
            ResultSet rs = this.stmtC.getGeneratedKeys();
            
            if (!rs.next())
                return null;
                        
            long id = rs.getLong(1);
            aviao.setId(id);
            return aviao;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Aviao> lerTodos() {
        List<Aviao> avioes = new ArrayList<>();
        
        try{
            ResultSet rs = this.stmtR.executeQuery();

            while (rs.next()) {
                Aviao a = new Aviao();
                
                a.setId(rs.getLong("id"));
                a.setModelo(rs.getString("modelo"));
                a.setPreco(rs.getDouble("preco"));
                
                avioes.add(a);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return avioes;
    }
    
    public boolean atualizar(long id, Aviao aviao) {
        try{
            this.stmtU.setString(1, aviao.getModelo());
            this.stmtU.setDouble(2, aviao.getPreco());
            this.stmtU.setLong(3, id);
            
            return this.stmtU.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean apagar(long id) {
        try{
            this.stmtD.setLong(1, id);
            
            return this.stmtD.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
