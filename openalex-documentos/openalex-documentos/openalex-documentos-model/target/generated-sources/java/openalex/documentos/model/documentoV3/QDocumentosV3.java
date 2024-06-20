package openalex.documentos.model.documentoV3;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumentosV3 is a Querydsl query type for DocumentosV3
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocumentosV3 extends EntityPathBase<DocumentosV3> {

    private static final long serialVersionUID = -1166281369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocumentosV3 documentosV3 = new QDocumentosV3("documentosV3");

    public final QContentDocumentoV3 content;

    public final StringPath id = createString("id");

    public QDocumentosV3(String variable) {
        this(DocumentosV3.class, forVariable(variable), INITS);
    }

    public QDocumentosV3(Path<? extends DocumentosV3> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocumentosV3(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocumentosV3(PathMetadata metadata, PathInits inits) {
        this(DocumentosV3.class, metadata, inits);
    }

    public QDocumentosV3(Class<? extends DocumentosV3> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = inits.isInitialized("content") ? new QContentDocumentoV3(forProperty("content")) : null;
    }

}

