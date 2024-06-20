package openalex.documentos.model;

import openalex.documentos.model.DocumentoOpenalex;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Javier Hernáez Hurtado
 */
public interface DocumentoOpenalexRepository extends MongoRepository<DocumentoOpenalex, String>{
    
}
