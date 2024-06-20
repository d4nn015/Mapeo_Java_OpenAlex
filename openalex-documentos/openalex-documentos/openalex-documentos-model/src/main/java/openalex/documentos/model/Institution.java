
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
public class Institution {

    @Field("lineage")
    private List<String> lineage = new ArrayList<>();
    @Field("country_code")
    private String countryCode;
    @Field("ror")
    private String ror;
    @Field("id")
    private String id;
    @Field("display_name")
    private String displayName;
    @Field("type")
    private String type;

}
