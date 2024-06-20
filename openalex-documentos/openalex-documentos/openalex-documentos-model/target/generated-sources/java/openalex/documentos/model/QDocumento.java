package openalex.documentos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumento is a Querydsl query type for Documento
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDocumento extends BeanPath<Documento> {

    private static final long serialVersionUID = 1526242470L;

    public static final QDocumento documento = new QDocumento("documento");

    public final StringPath abstractInvertedIndex = createString("abstractInvertedIndex");

    public final SimplePath<Apc> apcList = createSimple("apcList", Apc.class);

    public final SimplePath<Apc> apcPaid = createSimple("apcPaid", Apc.class);

    public final ListPath<Authorship, SimplePath<Authorship>> authorships = this.<Authorship, SimplePath<Authorship>>createList("authorships", Authorship.class, SimplePath.class, PathInits.DIRECT2);

    public final SimplePath<Location> bestOaLocation = createSimple("bestOaLocation", Location.class);

    public final SimplePath<Biblio> biblio = createSimple("biblio", Biblio.class);

    public final StringPath citedByApiUrl = createString("citedByApiUrl");

    public final NumberPath<Integer> citedByCount = createNumber("citedByCount", Integer.class);

    public final SimplePath<CitedByPercentileYear> citedByPercentileYear = createSimple("citedByPercentileYear", CitedByPercentileYear.class);

    public final ListPath<Concept, SimplePath<Concept>> concepts = this.<Concept, SimplePath<Concept>>createList("concepts", Concept.class, SimplePath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> correspondingAuthorIds = this.<String, StringPath>createList("correspondingAuthorIds", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> correspondingInstitutionIds = this.<String, StringPath>createList("correspondingInstitutionIds", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> countriesDistinctCount = createNumber("countriesDistinctCount", Integer.class);

    public final ListPath<CountsByYear, SimplePath<CountsByYear>> countsByYear = this.<CountsByYear, SimplePath<CountsByYear>>createList("countsByYear", CountsByYear.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath createdDate = createString("createdDate");

    public final ListPath<String, StringPath> datasets = this.<String, StringPath>createList("datasets", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath displayName = createString("displayName");

    public final StringPath doi = createString("doi");

    public final StringPath fulltextOrigin = createString("fulltextOrigin");

    public final ListPath<Grant, SimplePath<Grant>> grants = this.<Grant, SimplePath<Grant>>createList("grants", Grant.class, SimplePath.class, PathInits.DIRECT2);

    public final BooleanPath hasFulltext = createBoolean("hasFulltext");

    public final StringPath id = createString("id");

    public final SimplePath<Ids> ids = createSimple("ids", Ids.class);

    public final ListPath<String, StringPath> indexedIn = this.<String, StringPath>createList("indexedIn", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> institutionsDistinctCount = createNumber("institutionsDistinctCount", Integer.class);

    public final BooleanPath isParatext = createBoolean("isParatext");

    public final BooleanPath isRetracted = createBoolean("isRetracted");

    public final ListPath<Keyword, SimplePath<Keyword>> keywords = this.<Keyword, SimplePath<Keyword>>createList("keywords", Keyword.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath language = createString("language");

    public final ListPath<Location, SimplePath<Location>> locations = this.<Location, SimplePath<Location>>createList("locations", Location.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> locationsCount = createNumber("locationsCount", Integer.class);

    public final ListPath<Mesh, SimplePath<Mesh>> mesh = this.<Mesh, SimplePath<Mesh>>createList("mesh", Mesh.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath ngramsUrl = createString("ngramsUrl");

    public final SimplePath<OpenAccess> openAccess = createSimple("openAccess", OpenAccess.class);

    public final SimplePath<Location> primaryLocation = createSimple("primaryLocation", Location.class);

    public final SimplePath<Topic> primaryTopic = createSimple("primaryTopic", Topic.class);

    public final StringPath publicationDate = createString("publicationDate");

    public final NumberPath<Integer> publicationYear = createNumber("publicationYear", Integer.class);

    public final ListPath<String, StringPath> referencedWorks = this.<String, StringPath>createList("referencedWorks", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> referencedWorksCount = createNumber("referencedWorksCount", Integer.class);

    public final ListPath<String, StringPath> relatedWorks = this.<String, StringPath>createList("relatedWorks", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<SustainableDevelopmentGoal, SimplePath<SustainableDevelopmentGoal>> sustainableDevelopmentGoals = this.<SustainableDevelopmentGoal, SimplePath<SustainableDevelopmentGoal>>createList("sustainableDevelopmentGoals", SustainableDevelopmentGoal.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final ListPath<Topic, SimplePath<Topic>> topics = this.<Topic, SimplePath<Topic>>createList("topics", Topic.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public final StringPath typeCrossref = createString("typeCrossref");

    public final StringPath updatedDate = createString("updatedDate");

    public final ListPath<String, StringPath> versions = this.<String, StringPath>createList("versions", String.class, StringPath.class, PathInits.DIRECT2);

    public QDocumento(String variable) {
        super(Documento.class, forVariable(variable));
    }

    public QDocumento(Path<? extends Documento> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumento(PathMetadata metadata) {
        super(Documento.class, metadata);
    }

}

