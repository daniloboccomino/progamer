/**
 * Digital Business Enablement
 * FIAP - Faculdade de Informática e Administração Paulista
 * Professor Joao Carlos Lima e Silva
 *
 * @class SetupEndpoint.java
 * @description:
 * @author daniloboccomino - RM85473
 * @since May 4, 2021
 */

package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.SetupDao;
import br.com.fiap.model.Setup;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SetupEndpoint {
	
	private SetupDao dao = new SetupDao();	
	
	@GET
	public List<Setup> index() {
		return dao.getAll();
	}
	
	@POST
	public Response create(Setup setup) {
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		
		try {
			dao.save(setup);
			return Response.status(Response.Status.CREATED).entity(setup).build(); // 201
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // 500
		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		
		Setup setup = dao.findByid(id);
		
		if (setup == null) {
			return Response.status(Response.Status.NOT_FOUND).build(); // 404
		}
		
		return Response.status(Response.Status.OK).entity(setup).build(); // 200
	}
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Setup setup) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		if (dao.findByid(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build(); // 404
		}
		
		setup.setId(id);
		
		try {
			dao.update(setup);
			return Response.status(Response.Status.OK).entity(setup).build(); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // 500
		}		
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id, Setup setup) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); // 400
		}
		if (dao.findByid(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build(); // 404
		}
		
		setup.setId(id);
		
		try {
			dao.delete(setup);
			return Response.status(Response.Status.OK).entity(setup).build(); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // 500
		}
		
	}

}
