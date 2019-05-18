package br.mackenzie.ps2.ws;

import br.mackenzie.ps2.filmes.FilmeDAO;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
public class Servidor extends Application<Configuration>{

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/web_static", "/", "index.html"));
    }

    
    
    @Override
    public void run(Configuration t, Environment e) throws Exception {
        FilmeDAO dao = new FilmeDAO();
        e.jersey().register(new FilmesResource(dao));
        e.jersey().setUrlPattern("/cinema/*");
    }
    
}
