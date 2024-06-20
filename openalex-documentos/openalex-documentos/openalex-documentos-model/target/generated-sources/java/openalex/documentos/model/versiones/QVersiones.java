package openalex.documentos.model.versiones;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVersiones is a Querydsl query type for Versiones
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVersiones extends EntityPathBase<Versiones> {

    private static final long serialVersionUID = -1228790128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVersiones versiones = new QVersiones("versiones");

    public final QContentVersion content;

    public final StringPath documentoId = createString("documentoId");

    public final StringPath id = createString("id");

    public QVersiones(String variable) {
        this(Versiones.class, forVariable(variable), INITS);
    }

    public QVersiones(Path<? extends Versiones> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVersiones(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVersiones(PathMetadata metadata, PathInits inits) {
        this(Versiones.class, metadata, inits);
    }

    public QVersiones(Class<? extends Versiones> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = inits.isInitialized("content") ? new QContentVersion(forProperty("content")) : null;
    }

}

