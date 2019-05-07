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
public class ProfessorDAO {    
    
    private final List<Professor> professores;
    private long proximoId;
    
    public ProfessorDAO() {
        proximoId = 1;
        professores = new ArrayList<>();
        professores.add(new Professor(proximoId++, "Marcos", 11111));
        professores.add(new Professor(proximoId++, "Pedro", 22222));
        professores.add(new Professor(proximoId++, "Joana", 12345));
        professores.add(new Professor(proximoId++, "Joana", 12345));
    }
    
    @POST
    public Professor create(Professor prof) {
        prof.setId(proximoId++);
        this.professores.add(prof);
        return prof;
    }
    
    @GET
    public List<Professor> read() {
        return professores;
    }
    
    @PUT
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam idParam, Professor prof) {
        long id = idParam.get();
        
        for(Professor p: professores) {
            if(p.getId() == id) {
                p.setMatricula(prof.getMatricula());
                p.setNome(prof.getNome());
                return Response.ok().build();
            }
        }
        throw new WebApplicationException("Professor com id=" + id 
                                          + " não encontrado!", 404);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam idParam) {
        long id = idParam.get();
        
        for(Professor p: professores) {
            if(p.getId() == id) {
                professores.remove(p);
                return Response.ok().build();
            }
        }
        throw new WebApplicationException("Professor com id=" + id 
                                          + " não encontrado!", 404);
    }
}

