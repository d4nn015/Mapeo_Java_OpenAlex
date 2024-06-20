
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
public class Source {

    @Field("host_organization_lineage")
    private List<String> hostOrganizationLineage = new ArrayList<>();
    @Field("is_in_doaj")
    private Boolean isInDoaj;
    @Field("host_organization_lineage_names")
    private List<String> hostOrganizationLineageNames = new ArrayList<>();
    @Field("issn")
    private List<String> issn = new ArrayList<>();
    @Field("issn_l")
    private String issnL;
    @Field("is_oa")
    private Boolean isOa;
    @Field("id")
    private String id;
    @Field("display_name")
    private String displayName;
    @Field("type")
    private String type;
    @Field("host_organization_name")
    private String hostOrganizationName;
    @Field("host_organization")
    private String hostOrganization;

}
