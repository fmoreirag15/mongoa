package org.acme.service.impl;


import org.acme.model.Persona;
import org.acme.repository.personaRespository;
import org.acme.service.Iservice;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
@ApplicationScoped
public class serviceImpl   implements Iservice {
    @Inject
    personaRespository respository;
    @Override
    public List<Persona> listaPersona() {
        return respository.listAll();
    }

    @Override
    public Persona guardarPersona(Persona persona) {

        respository.persist(persona);
        return persona;
    }

    @Override
    public Persona modificarPersona(Persona persona) {

        respository.update(persona);
        return persona;
    }

    @Override
    public Persona buscarIde(String id) {
        return (Persona) respository.findById(new ObjectId(id));
    }
}
