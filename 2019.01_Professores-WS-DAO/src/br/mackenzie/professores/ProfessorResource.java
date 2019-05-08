package br.mackenzie.professores;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResource {    
    
    private ProfessorDAO dao;
    
    public ProfessorResource(ProfessorDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Professor create(Professor prof) {
        Professor p = dao.criar(prof);
        return p;
    }
    
    @GET
    public List<Professor> read() {
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response upadate(@PathParam("id") LongParam idParam, Professor prof) {
        prof.setId(idParam.get());
        if (dao.atualizar(prof)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Professor com id=" + idParam.get()
                                          + " não encontrado!", 404);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam idParam) {
        long id = idParam.get();
        
        if (dao.apagar(id)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Professor com id=" + id 
                                          + " não encontrado!", 404);
    }
}

