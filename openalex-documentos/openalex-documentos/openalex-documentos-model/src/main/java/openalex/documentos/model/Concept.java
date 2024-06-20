
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Concept {

    @Field("score")
    private Double score;
    @Field("level")
    private Integer level;
    @Field("display_name")
    private String displayName;
    @Field("wikidata")
    private String wikidata;
    @Field("id")
    private String id;
   
}
