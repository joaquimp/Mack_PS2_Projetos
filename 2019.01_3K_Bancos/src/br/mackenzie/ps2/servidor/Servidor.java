package br.mackenzie.ps2.servidor;

import br.mackenzie.ps2.BancoDAO;
import br.mackenzie.ps2.BancoResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 *
 * @author Joaquim Pessôa Filho
 */
public class Servidor extends Application<Configuration> {

    @Override
    public void run(Configuration t, Environment e) throws Exception {
        BancoDAO dao = new BancoDAO();
        e.jersey().register(new BancoResource(dao));
    }
    
    public static void main(String args[]) throws Exception {
        Servidor s = new Servidor();
        s.run(new String[]{ "server" });
    }
}
