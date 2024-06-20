
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Ids {

    @Field("mag")
    private String mag;
    @Field("openalex")
    private String openalex;
    @Field("pmid")
    private String pmid;
    @Field("pmcid")
    private String pmcid;
    @Field("doi")
    private String doi;

    
}
