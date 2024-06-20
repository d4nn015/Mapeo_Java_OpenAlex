package openalex.documentos.model;

import com.querydsl.core.annotations.QueryInit;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Esta clase representa al documento
 * descargado de Scopus en la colección MongoDB
 * Incluye el documento del modelo 'documentos-external-scopus' original
 * 
 * @author Javier Hernáez Hurtado
 */
@Data
@CompoundIndex(name = "identificador", def = "{'documento.id' : 1}", unique = true)
@Document(collection = "documentos")
public class DocumentoOpenalex implements Serializable {

    @Id
    private String id;
    
    @QueryInit("*.*.*.*.*.*")
    private Documento documento;

    @CreatedDate
    private Date fechaCrea;

    @LastModifiedDate
    private Date fechaModi;    
    
    @Version
    private Integer version;
    
    
    public DocumentoOpenalex(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return documento;
    }

    public String getId() {
        return id;
    }
    
    

}

