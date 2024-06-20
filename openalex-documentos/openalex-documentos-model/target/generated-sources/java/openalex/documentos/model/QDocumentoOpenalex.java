package openalex.documentos.model;

import es.unirioja.dialnet.openalex.documentos.model.DocumentoOpenalex;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocumentoOpenalex is a Querydsl query type for DocumentoOpenalex
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocumentoOpenalex extends EntityPathBase<DocumentoOpenalex> {

    private static final long serialVersionUID = -1597462994L;

    public static final QDocumentoOpenalex documentoOpenalex = new QDocumentoOpenalex("documentoOpenalex");

    public final DateTimePath<java.util.Date> fechaCrea = createDateTime("fechaCrea", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModi = createDateTime("fechaModi", java.util.Date.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDocumentoOpenalex(String variable) {
        super(DocumentoOpenalex.class, forVariable(variable));
    }

    public QDocumentoOpenalex(Path<? extends DocumentoOpenalex> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentoOpenalex(PathMetadata metadata) {
        super(DocumentoOpenalex.class, metadata);
    }

}

