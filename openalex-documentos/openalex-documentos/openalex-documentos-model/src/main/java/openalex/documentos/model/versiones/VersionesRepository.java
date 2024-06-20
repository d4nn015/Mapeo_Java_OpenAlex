/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package openalex.documentos.model.versiones;

import java.util.stream.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author practicas
 */

public interface VersionesRepository extends MongoRepository<Versiones, String>{
    @Query(value = "{'content.identificadores':{'sistema':?0, 'id':?1}}", exists = true)
    boolean existsByIdentificador(String sistema, String id);
}
