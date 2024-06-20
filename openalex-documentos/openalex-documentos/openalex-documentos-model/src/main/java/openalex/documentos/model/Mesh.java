
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Mesh{

    @Field("is_major_topic")
    private Boolean isMajorTopic;
    @Field("qualifier_ui")
    private String qualifierUi;
    @Field("descriptor_name")
    private String descriptorName;
    @Field("descriptor_ui")
    private String descriptorUi;
    @Field("qualifier_name")
    private String qualifierName;

}
