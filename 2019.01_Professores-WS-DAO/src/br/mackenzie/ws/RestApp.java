package br.mackenzie.ws;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
import br.mackenzie.db.ConexaoJavaDB;
import br.mackenzie.professores.ProfessorDAO;
import br.mackenzie.professores.ProfessorResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApp extends Application<Configuration> {
    
    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[] { "server" });
    }
    
    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        //Mapeia a pasta "src/html" para a url "http://localhost:8080/" e
        // por padrao abre o arquivo index.html quando um recurso especifico
        // nao for informado
        bootstrap.addBundle(new AssetsBundle("/html", "/", "index.html"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        ConexaoJavaDB conexao = new ConexaoJavaDB("root", "", "jdbc:mysql://localhost", 3306, "ps2");
        ProfessorDAO professorDao = new ProfessorDAO(conexao);
        environment.jersey().register(new ProfessorResource(professorDao));
        
        // Mapeia todos os WebServices para a rota base 
        // "http://localhost:8080/api/"
        environment.jersey().setUrlPattern("/api/*");
    }
}


