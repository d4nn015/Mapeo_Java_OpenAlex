
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class OpenAccess {

    @Field("oa_status")
    private String oaStatus;
    @Field("is_oa")
    private Boolean isOa;
    @Field("oa_url")
    private String oaUrl;
    @Field("any_repository_has_fulltext")
    private Boolean anyRepositoryHasFulltext;

}
