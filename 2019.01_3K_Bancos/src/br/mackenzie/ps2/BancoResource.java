package br.mackenzie.ps2;

import io.dropwizard.jersey.params.IntParam;
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
 * @author Joaquim Pessôa Filho
 */
@Path("/bancos")
@Produces(MediaType.APPLICATION_JSON)
public class BancoResource {
    private BancoDAO dao;
    
    public BancoResource(BancoDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<Banco> get() {
        return dao.lerTodos();
    }
    
    @POST
    public Banco create(Banco banco) {
        return dao.criar(banco);
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") IntParam id, Banco banco) {
        banco.setId(id.get());
        
        if (dao.atualizar(banco)) {
            return Response.ok().build();
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
