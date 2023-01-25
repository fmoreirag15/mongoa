package org.acme.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.acme.model.Persona;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class personaRespository implements PanacheMongoRepository<Persona> {

    public boolean findByIdPersona(String DNI){
        if(find("DNI",DNI).firstResult()==null)
        {
            return true;
        }
        return false;
    }
}
