package org.acme.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@MongoEntity(collection = "persona")
public class Persona {
    @BsonId
    private ObjectId id;
    @NotEmpty(message = "no puede ser nombre nulo")
    private String nombre;
    @NotEmpty(message = "no puede ser apellido nulo")
    private String apellido;
    @NotEmpty(message = "no puede ser edad nulo")
    private String edad;
    @NotEmpty(message = "pais no puede ser nulo")
    private String country;
    @NotEmpty(message = "DNI no puede ser nulo")
    @Size(min = 0, max = 8, message = "No puede ser mayor a 8dig")
    private String DNI;
}
