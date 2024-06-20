
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Location {
    
    @Field("license")
    private String license;
    @Field("is_accepted")
    private Boolean isAccepted;
    @Field("license_id")
    private String licenseId;
    @Field("is_published")
    private Boolean isPublished;
    @Field("is_oa")
    private Boolean isOa;
    @Field("pdf_url")
    private String pdfUrl;
    @Field("source")
    private Source source;
    @Field("landing_page_url")
    private String landingPageUrl;
    @Field("version")
    private String version;

}
