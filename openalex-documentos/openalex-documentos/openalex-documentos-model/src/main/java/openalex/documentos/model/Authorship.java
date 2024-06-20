
package openalex.documentos.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Authorship {
    
    @Field("institutions")
    private List<Institution> institutions = new ArrayList<>();
    @Field("raw_affiliation_strings")
    private List<String> rawAffiliationStrings = new ArrayList<>();
    @Field("author")
    private Author author;
    @Field("is_corresponding")
    private Boolean isCorresponding;
    @Field("raw_author_name")
    private String rawAuthorName;
    private List<String> countries = new ArrayList<>();
    @Field("author_position")
    private String authorPosition;

}
