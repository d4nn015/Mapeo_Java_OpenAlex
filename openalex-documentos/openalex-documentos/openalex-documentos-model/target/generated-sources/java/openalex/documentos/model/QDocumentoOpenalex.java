package openalex.documentos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumentoOpenalex is a Querydsl query type for DocumentoOpenalex
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocumentoOpenalex extends EntityPathBase<DocumentoOpenalex> {

    private static final long serialVersionUID = -1597462994L;

    private static final PathInits INITS = new PathInits("*", "documento.*.*.*.*.*.*");

    public static final QDocumentoOpenalex documentoOpenalex = new QDocumentoOpenalex("documentoOpenalex");

    public final QDocumento documento;

    public final DateTimePath<java.util.Date> fechaCrea = createDateTime("fechaCrea", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModi = createDateTime("fechaModi", java.util.Date.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDocumentoOpenalex(String variable) {
        this(DocumentoOpenalex.class, forVariable(variable), INITS);
    }

    public QDocumentoOpenalex(Path<? extends DocumentoOpenalex> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocumentoOpenalex(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocumentoOpenalex(PathMetadata metadata, PathInits inits) {
        this(DocumentoOpenalex.class, metadata, inits);
    }

    public QDocumentoOpenalex(Class<? extends DocumentoOpenalex> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.documento = inits.isInitialized("documento") ? new QDocumento(forProperty("documento")) : null;
    }

}

