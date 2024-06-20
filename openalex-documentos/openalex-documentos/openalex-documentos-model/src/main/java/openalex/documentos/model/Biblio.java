
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Biblio {

    @Field("volume")
    private String volume;
    @Field("issue")
    private String issue;
    @Field("first_page")
    private String firstPage;
    @Field("last_page")
    private String lastPage;

}
