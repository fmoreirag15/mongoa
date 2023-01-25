package org.acme.service;

import org.acme.model.Persona;

import java.util.List;

public interface Iservice {
    List<Persona> listaPersona();
    Persona guardarPersona(Persona persona);
    Persona modificarPersona(Persona  persona);
    Persona buscarIde(String id);
}
