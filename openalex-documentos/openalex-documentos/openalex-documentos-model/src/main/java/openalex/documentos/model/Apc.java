
package openalex.documentos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
public class Apc {

    @Field("provenance")
    private String provenance;
    @Field("value_usd")
    private Integer valueUsd;
    @Field("currency")
    private String currency;
    @Field("value")
    private Integer value;

}
