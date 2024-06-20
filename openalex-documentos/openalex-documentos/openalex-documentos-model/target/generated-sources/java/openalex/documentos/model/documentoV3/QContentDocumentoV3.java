package openalex.documentos.model.documentoV3;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContentDocumentoV3 is a Querydsl query type for ContentDocumentoV3
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QContentDocumentoV3 extends BeanPath<ContentDocumentoV3> {

    private static final long serialVersionUID = 2049143843L;

    public static final QContentDocumentoV3 contentDocumentoV3 = new QContentDocumentoV3("contentDocumentoV3");

    public final ListPath<IdentificadorDocumentoV3, SimplePath<IdentificadorDocumentoV3>> identificadores = this.<IdentificadorDocumentoV3, SimplePath<IdentificadorDocumentoV3>>createList("identificadores", IdentificadorDocumentoV3.class, SimplePath.class, PathInits.DIRECT2);

    public QContentDocumentoV3(String variable) {
        super(ContentDocumentoV3.class, forVariable(variable));
    }

    public QContentDocumentoV3(Path<? extends ContentDocumentoV3> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContentDocumentoV3(PathMetadata metadata) {
        super(ContentDocumentoV3.class, metadata);
    }

}

