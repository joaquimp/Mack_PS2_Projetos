package br.mackenzie.ps2.ws;

import br.mackenzie.ps2.avioes.AvioesDAO;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author Joaquim Pessôa Filho
 */
public class Servidor extends Application<Configuration>{

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        //Mapeia a pasta "src/web" para a url "http://localhost:8080/" e
        // por padrao abre o arquivo index.html quando um recurso especifico
        // nao for informado
        bootstrap.addBundle(new AssetsBundle("/web", "/", "index.html"));
    }
    
    @Override
    public void run(Configuration t, Environment e) throws Exception {
        AvioesResource avioesR = new AvioesResource(new AvioesDAO());
        
        e.jersey().register(avioesR);
        e.jersey().setUrlPattern("/api/v1/*");
    }
}
