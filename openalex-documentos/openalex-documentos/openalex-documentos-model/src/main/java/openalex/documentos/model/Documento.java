
package openalex.documentos.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Documento {


    @Field("apc_list")
    private Apc apcList;
    @Field("is_retracted")
    private Boolean isRetracted;
    @Field("countries_distinct_count")
    private Integer countriesDistinctCount;
    @Field("fulltext_origin")
    private String fulltextOrigin;
    @Field("is_paratext")
    private Boolean isParatext;
    @Field("keywords")
    private List<Keyword> keywords = new ArrayList<>();
    @Field("cited_by_api_url")
    private String citedByApiUrl;
    @Field("counts_by_year")
    private List<CountsByYear> countsByYear = new ArrayList<>();
    @Field("biblio")
    private Biblio biblio;
    @Field("primary_location")
    private Location primaryLocation;
    @Field("primary_topic")
    private Topic primaryTopic;
    @Field("language")
    private String language;
    @Field("related_works")
    private List<String> relatedWorks = new ArrayList<>();
    @Field("datasets")
    private List<String> datasets = new ArrayList<>();
    @Field("title")
    private String title;
    @Field("type")
    private String type;
    @Field("cited_by_percentile_year")
    private CitedByPercentileYear citedByPercentileYear;
    @Field("corresponding_institution_ids")
    private List<String> correspondingInstitutionIds = new ArrayList<>();
    @Field("publication_date")
    private String publicationDate;
    @Field("open_access")
    private OpenAccess openAccess;
    @Field("authorships")
    private List<Authorship> authorships = new ArrayList<>();
    @Field("id")
    @NonNull
    private String id;
    @Field("institutions_distinct_count")
    private Integer institutionsDistinctCount;
    @Field("has_fulltext")
    private Boolean hasFulltext;
    @Field("abstract_inverted_index")
    private String abstractInvertedIndex;
    @Field("sustainable_development_goals")
    private List<SustainableDevelopmentGoal> sustainableDevelopmentGoals = new ArrayList<>();
    @Field("mesh")
    private List<Mesh> mesh = new ArrayList<>();
    @Field("grants")
    private List<Grant> grants = new ArrayList<>();
    @Field("best_oa_location")
    private Location bestOaLocation;
    @Field("cited_by_count")
    private Integer citedByCount;
    @Field("ngrams_url")
    private String ngramsUrl;
    @Field("indexed_in")
    private List<String> indexedIn = new ArrayList<>();
    @Field("topics")
    private List<Topic> topics = new ArrayList<>();
    @Field("referenced_works")
    private List<String> referencedWorks = new ArrayList<>();
    @Field("type_crossref")
    private String typeCrossref;
    @Field("referenced_works_count")
    private Integer referencedWorksCount;
    @Field("display_name")
    private String displayName;
    @Field("publication_year")
    private Integer publicationYear;
    @Field("concepts")
    private List<Concept> concepts = new ArrayList<>();
    @Field("versions")
    private List<String> versions = new ArrayList<>();
    @Field("ids")
    private Ids ids;
    @Field("locations_count")
    private Integer locationsCount;
    @Field("locations")
    private List<Location> locations = new ArrayList<>();
    @Field("corresponding_author_ids")
    private List<String> correspondingAuthorIds = new ArrayList<>();
    @Field("updated_date")
    private String updatedDate;
    @Field("created_date")
    private String createdDate;
    @Field("apc_paid")
    private Apc apcPaid;
    @Field("doi")
    private String doi;
    
}