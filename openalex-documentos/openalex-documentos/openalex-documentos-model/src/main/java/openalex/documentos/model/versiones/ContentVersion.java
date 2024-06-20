/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package openalex.documentos.model.versiones;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author practicas
 */

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class ContentVersion {
    
    @Field("identificadores")
    private List<IdentificadorVersion> identificadores;
}
