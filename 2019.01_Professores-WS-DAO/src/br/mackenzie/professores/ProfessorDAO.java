/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.professores;

import br.mackenzie.db.ConexaoJavaDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaquim
 */
public class ProfessorDAO {
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    @SuppressWarnings("CallToPrintStackTrace")
    public ProfessorDAO(ConexaoJavaDB conexao) {
        try {
            Connection conn = conexao.getConexao();
            
            String sqlC = "INSERT INTO professores(nome, matricula) VALUES(?,?)";
            String sqlR = "SELECT * FROM professores";
            String sqlU = "UPDATE professores SET nome=?, matricula=? WHERE id=?";
            String sqlD = "DELETE FROM professores WHERE id=?";
            
            // O segundo parametro indica que iremos precisar obter o id
            // gerado automaticamente pelo banco
            this.stmtC = conn.prepareStatement(sqlC,Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlR);
            this.stmtU = conn.prepareStatement(sqlU);
            this.stmtD = conn.prepareStatement(sqlD);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Professor> lerTodos() {
        try{
            ResultSet rs = this.stmtR.executeQuery();
            List<Professor> professores = new ArrayList<>();
            
            while(rs.next()) {
                Professor aux = new Professor();
                aux.setId(rs.getInt("id"));
                aux.setNome(rs.getString("nome"));
                aux.setMatricula(rs.getInt("matricula"));
                professores.add(aux);
            }
            return professores;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Professor criar(Professor p) {
        try{
            this.stmtC.setString(1, p.getNome());
            this.stmtC.setInt(2, p.getMatricula());
            
            this.stmtC.executeUpdate();
            ResultSet rs = this.stmtC.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                p.setId(id);
                return p;
            } 
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean atualizar(Professor p) {
        try{
            this.stmtU.setString(1, p.getNome());
            this.stmtU.setInt(2, p.getMatricula());
            this.stmtU.setLong(3, p.getId());
            
            return this.stmtU.executeUpdate() > 0;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    public boolean apagar(long id) {
        try{
            this.stmtD.setLong(1, id);
            return this.stmtD.executeUpdate() > 0;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
