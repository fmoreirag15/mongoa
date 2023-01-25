package org.acme.controller;


import org.acme.model.Persona;
import org.acme.service.Iservice;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/v1")
public class mongoController {
    @Inject
    Iservice iservice;
    @Inject
    Validator validator;
    @GET
    @Path("/list/{edad}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listPersona(@PathParam("edad") Integer edad) {
        return Response.ok().entity(iservice.listaPersona(edad)).build();
    }
    @GET
    @Path("/list/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPersonaId(@PathParam("id") String id) {
        return Response.ok().entity(iservice.buscarIde(id)).build();
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarPersona(Persona persona) {
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        if (violations.isEmpty()) {
            if(iservice.guardarPersona(persona)==null)
            {
                return Response.status(204).tag("No se pudo guardar").entity(persona).build();

            }else {
                return Response.status(201).entity(persona).build();
            }

        }else
        {
            return Response.status(500).entity(violations.stream()
                .map(cv -> cv == null ? "null" : "{" +
                        cv.getPropertyPath() + ": " +
                        cv.getMessage() + "}")
                .collect(Collectors.joining(","))).build();}

    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarPersona(Persona persona) {
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        if (violations.isEmpty()) {
        return Response.status(201).entity(iservice.modificarPersona(persona)).build();}else
        {
            return Response.status(500).entity(violations.stream()
                    .map(cv -> cv == null ? "null" : "{" +
                            cv.getPropertyPath() + ": " +
                            cv.getMessage() + "}")
                    .collect(Collectors.joining(","))).build();}
    }
}