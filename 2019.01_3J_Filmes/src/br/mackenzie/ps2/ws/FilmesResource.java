package br.mackenzie.ps2.ws;

import br.mackenzie.ps2.filmes.Filme;
import br.mackenzie.ps2.filmes.FilmeDAO;
import io.dropwizard.jersey.params.IntParam;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
@Path("/filmes")
@Produces(MediaType.APPLICATION_JSON)
public class FilmesResource {
    
    private FilmeDAO dao;
    
    public FilmesResource(FilmeDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Filme create(Filme filme) {
        return dao.inserir(filme);
    }
    
    @GET
    public List<Filme> read() {
        return dao.lerTudo();
    }
    
    @GET
    @Path("{id}")
    public Filme read2(@PathParam("id") IntParam id) {
        // IMPLEMENTAR NO DAO
        // SELECT * FROM filmes WHERE id=?
        
        List<Filme> filmes = dao.lerTudo();
        
        for(Filme f: filmes) {
            if(f.getId() == id.get()) return f;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") IntParam id, Filme filme) {
        filme.setId(id.get());
        
        if (dao.atualizar(filme)) {
            return Response.ok("Meus parabéns").build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        
        if (dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
