package org.acme.service.impl;
import org.acme.model.Persona;
import org.acme.repository.personaRespository;
import org.acme.service.Iservice;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class serviceImpl   implements Iservice {
    @Inject
    personaRespository respository;
    @Override
    public List<Persona> listaPersona(Integer edad) {
        List<Persona> personaList=respository.listAll();
        return  personaList.stream()
                .filter(c -> Integer.parseInt(c.getEdad())>edad)
                .collect(Collectors.toList());
    }
    @Override
    public Persona guardarPersona(Persona persona) {
        if(respository.findByIdPersona(persona.getDNI())) {
            respository.persist(persona);
            return persona;
        }else
        {
            return  null;
        }

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
