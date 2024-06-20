
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Topic {

    @Field("score")
    private Double score;
    @Field("subfield")
    private DomainFieldSubfield subfield;
    @Field("field")
    private DomainFieldSubfield field;
    @Field("domain")
    private DomainFieldSubfield domain;
    @Field("id")
    private String id;
    @Field("display_name")
    private String displayName;
    
}
