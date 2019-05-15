package br.mackenzie.ps2.ws;

import br.mackenzie.ps2.avioes.Aviao;
import br.mackenzie.ps2.avioes.AvioesDAO;
import io.dropwizard.jersey.params.LongParam;
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
 * @author Joaquim Pesssôa Filho
 * 
 */
@Path("/avioes")
@Produces(MediaType.APPLICATION_JSON)
public class AvioesResource {
    private AvioesDAO dao;
    
    public AvioesResource(AvioesDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Aviao create(Aviao aviao) {
        return dao.criar(aviao);
    }
    
    @GET
    public List<Aviao> read() {
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{banana}")
    public Response update(@PathParam("banana") LongParam id, Aviao aviao) {
        if(dao.atualizar(id.get(), aviao)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Não rolou alterar", Response.Status.NOT_FOUND);
    }
    
    @DELETE
    @Path("{maca}")
    public Response delete(@PathParam("maca") LongParam id, Aviao aviao) {
        if(dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
