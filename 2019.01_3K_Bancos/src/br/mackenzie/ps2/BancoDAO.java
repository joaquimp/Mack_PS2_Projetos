package br.mackenzie.ps2;

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
 * @author Joaquim Pessôa Filho
 * 
 */
public class BancoDAO {
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    @SuppressWarnings("CallToPrintStackTrace")
    public BancoDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/ps2";
            String usuario = "root";
            String senha = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            
            String sqlC = "INSERT INTO bancos(nome, cnpj, numero_clientes) VALUES(?,?,?)";
            String sqlR = "SELECT * FROM bancos";
            String sqlU = "UPDATE bancos SET nome=?, cnpj=?, numero_clientes=? WHERE id=?";
            String sqlD = "DELETE FROM bancos WHERE id=?";
            
            // O segundo parametro indica que iremos precisar obter o id
            // gerado automaticamente pelo banco
            this.stmtC = conn.prepareStatement(sqlC,Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlR);
            this.stmtU = conn.prepareStatement(sqlU);
            this.stmtD = conn.prepareStatement(sqlD);
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Banco> lerTodos() {
        try{
            ResultSet rs = this.stmtR.executeQuery();
            List<Banco> bancos = new ArrayList<>();
            
            while(rs.next()) {
                Banco aux = new Banco();
                aux.setId(rs.getInt("id"));
                aux.setNome(rs.getString("nome"));
                aux.setCnpj(rs.getString("cnpj"));
                aux.setNumeroClientes(rs.getInt("numero_clientes"));
                bancos.add(aux);
            }
            return bancos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Banco criar(Banco b) {
        try{
            this.stmtC.setString(1, b.getNome());
            this.stmtC.setString(2, b.getCnpj());
            this.stmtC.setInt(3, b.getNumeroClientes());
            
            this.stmtC.executeUpdate();
            ResultSet rs = this.stmtC.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                b.setId(id);
                return b;
            } 
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean atualizar(Banco b) {
        try{
            this.stmtU.setString(1, b.getNome());
            this.stmtU.setString(2, b.getCnpj());
            this.stmtU.setInt(3, b.getNumeroClientes());
            this.stmtU.setInt(4, b.getId());
            
            return this.stmtU.executeUpdate() > 0;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean apagar(int id) {
        try{
            this.stmtD.setInt(1, id);
            return this.stmtD.executeUpdate() > 0;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
