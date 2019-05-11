package br.mackenzie.ps2.filmes;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
public class FilmeDAO {
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    public FilmeDAO() {
        try{
            String url = "jdbc:mysql://localhost:3306/ps2";
            String usuario = "root";
            String senha = "";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,usuario,senha);
            
            String sqlCreate = "INSERT INTO filmes(titulo, genero, minutos) VALUES (?,?,?)";
            String sqlRead = "SELECT * FROM filmes";
            String sqlUpdate = "UPDATE filmes SET titulo=?, genero=?, minutos=? WHERE id=?";
            String sqlDelete = "DELETE FROM filmes WHERE id=?";
            
            this.stmtC = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlRead);
            this.stmtU = conn.prepareStatement(sqlUpdate);
            this.stmtD = conn.prepareStatement(sqlDelete);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Filme> lerTudo() {
        try{
            List<Filme> filmes = new ArrayList<>();
            ResultSet rs = this.stmtR.executeQuery();
            
            while(rs.next()) {
                Filme f = new Filme();
                f.setId(rs.getInt("id"));
                f.setTitulo(rs.getString("titulo"));
                f.setGenero(rs.getString("genero"));
                f.setMinutos(rs.getInt("minutos"));
                filmes.add(f);
            }
            return filmes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Filme inserir(Filme filme) {
        try{
            //Informar os valores para o PreparedStatement
            this.stmtC.setString(1,filme.getTitulo());
            this.stmtC.setString(2, filme.getGenero());
            this.stmtC.setInt(3, filme.getMinutos());
            
            if (this.stmtC.executeUpdate() > 0) {
                // Conseguiu inserir
                ResultSet rs = this.stmtC.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                filme.setId(id);
                return filme;
            } else {
                // Não inseriu
                return null;
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean atualizar(Filme filme) {
        try{
            this.stmtU.setString(1,filme.getTitulo());
            this.stmtU.setString(2, filme.getGenero());
            this.stmtU.setInt(3, filme.getMinutos());
            this.stmtU.setInt(4, filme.getId());
            
            return this.stmtU.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean apagar(int id) {
        try{
            this.stmtD.setInt(1, id);
            return this.stmtD.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String args[]) {
        
        Filme teste = new Filme(99999,"E o vento levou", "comédia", 1);
        
        
        FilmeDAO dao = new FilmeDAO();
        
        Filme filmeInserido = dao.inserir(teste);
        dao.apagar(filmeInserido.getId());
        
        
        List<Filme> fs = dao.lerTudo();
        fs.get(0).setGenero("Musical");
        dao.atualizar(fs.get(0));
        
        System.out.println(fs.size());
    }
}
