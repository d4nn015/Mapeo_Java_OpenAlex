package openalex.documentos.model.versiones;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContentVersion is a Querydsl query type for ContentVersion
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QContentVersion extends BeanPath<ContentVersion> {

    private static final long serialVersionUID = -484591275L;

    public static final QContentVersion contentVersion = new QContentVersion("contentVersion");

    public final ListPath<IdentificadorVersion, SimplePath<IdentificadorVersion>> identificadores = this.<IdentificadorVersion, SimplePath<IdentificadorVersion>>createList("identificadores", IdentificadorVersion.class, SimplePath.class, PathInits.DIRECT2);

    public QContentVersion(String variable) {
        super(ContentVersion.class, forVariable(variable));
    }

    public QContentVersion(Path<? extends ContentVersion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContentVersion(PathMetadata metadata) {
        super(ContentVersion.class, metadata);
    }

}

