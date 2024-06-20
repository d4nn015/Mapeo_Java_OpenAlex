package docserver.importador.openalex.mapper;

import dialnet.docserver.model.documentos.DocumentoRepository;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import openalex.documentos.model.versiones.IdentificadorVersion;
import openalex.documentos.model.versiones.Versiones;
import openalex.documentos.model.versiones.VersionesRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import openalex.documentos.model.DocumentoOpenalex;
import openalex.documentos.model.DocumentoOpenalexRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test para comprobar el correcto mapeo de los documentos de OpenAlex
 *
 * @author Javier Hernáez Hurtado
 */
@Slf4j
@SpringBootTest
public class DocumentoOpenAlexMapperTest {
    
    @Autowired
    private VersionesRepository versionesRepository;

    @Autowired
    private DocumentoOpenalexRepository documentoOpenalexRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Test
    public void test1(){
        Assertions.assertTrue(versionesRepository.existsByIdentificador("DIALNET_ART", "2419"));
//        if(versiones.isPresent()){
//            Versiones version = versiones.get();
//            for(IdentificadorVersion id : version.getContent().getIdentificadores()){
//                System.out.println(id.getId());
//                System.out.println(id.getSistema());
//                System.out.println("-------------------------------");
//            }
//        }
    }
    
//    @Test
    public void test2() {
        
        DocumentoOpenAlexMapper d = new DocumentoOpenAlexMapper();
        
        Optional <DocumentoOpenalex> docOA = documentoOpenalexRepository.findById("6646f6da2ccf4358bc832477");
        if(docOA.isPresent()){
            DocumentoOpenalex doc = docOA.get();
            Assertions.assertTrue(doc.getFechaCrea().equals(Date.from(OffsetDateTime.parse("2024-05-17T08:13:32.066+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant())));
            Assertions.assertTrue(doc.getFechaModi().equals(Date.from(OffsetDateTime.parse("2024-05-17T11:42:17.250+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant())));
            Assertions.assertTrue(doc.getVersion()==6);
            
            // Id
            Assertions.assertTrue(doc.getDocumento().getId().equals("https://openalex.org/W2160693392"));
            // Doi
            Assertions.assertTrue(doc.getDocumento().getDoi().equals("https://doi.org/10.1186/s12859-015-0703-0"));
            // Title
            Assertions.assertTrue(doc.getDocumento().getTitle().equals("GelJ – a tool for analyzing DNA fingerprint gel images"));
            // DisplayName
            Assertions.assertTrue(doc.getDocumento().getDisplayName().equals("GelJ – a tool for analyzing DNA fingerprint gel images"));
            // PublicationYear
            Assertions.assertTrue(doc.getDocumento().getPublicationYear()==2015);
            // PublicationDate
            Assertions.assertTrue(doc.getDocumento().getPublicationDate().equals("2015-08-26"));
            // Language
            Assertions.assertTrue(doc.getDocumento().getLanguage().equals("en"));
            // Type
            Assertions.assertTrue(doc.getDocumento().getType().equals("article"));
            // TypeCrossref
            Assertions.assertTrue(doc.getDocumento().getTypeCrossref().equals("journal-article"));
            // CountriesDistinctCount
            Assertions.assertTrue(doc.getDocumento().getCountriesDistinctCount()==1);
            // InstitutionsDistinctCount
            Assertions.assertTrue(doc.getDocumento().getInstitutionsDistinctCount()==1);
            // HasFullText
            Assertions.assertTrue(doc.getDocumento().getHasFulltext()==true);
            // FulltextOrigin
            Assertions.assertTrue(doc.getDocumento().getFulltextOrigin().equals("pdf"));
            // CitedByCount
            Assertions.assertTrue(doc.getDocumento().getCitedByCount()==241);
            // IsRetracted
            Assertions.assertTrue(doc.getDocumento().getIsRetracted()==false);
            // IsParatext
            Assertions.assertTrue(doc.getDocumento().getIsParatext()==false);
            // LocationsCount
            Assertions.assertTrue(doc.getDocumento().getLocationsCount()==4);
            // ReferencedWorksCount
            Assertions.assertTrue(doc.getDocumento().getReferencedWorksCount()==10);
            // NgramsUrl
            Assertions.assertTrue(doc.getDocumento().getNgramsUrl().equals("https://api.openalex.org/works/W2160693392/ngrams"));
            // ABstractInvertedIndex
            Assertions.assertTrue(doc.getDocumento().getAbstractInvertedIndex().equals("DNA fingerprinting is a technique for comparing DNA patterns that has applications in a wide variety of contexts. Several commercial and freely-available tools can be used to analyze DNA fingerprint gel images; however, commercial tools are expensive and usually difficult to use; and, free tools support the basic functionality for DNA fingerprint analysis, but lack some instrumental features to obtain accurate results.In this paper, we present GelJ, a feather-weight, user-friendly, platform-independent, open-source and free tool for analyzing DNA fingerprint gel images. Some of the outstanding features of GelJ are mechanisms for accurate lane- and band-detection, several options for computing migration models, a number of band- and curve-based similarity methods, different techniques for generating dendrograms, comparison of banding patterns from different experiments, and database support.GelJ is an easy to use tool for analyzing DNA fingerprint gel images. It combines the best characteristics of both free and commercial tools: GelJ is light and simple to use (as free programs), but it also includes the necessary features to obtain precise results (as commercial programs). In addition, GelJ incorporates new functionality that is not supported by any other tool."));
            // CitedByApiUrl
            Assertions.assertTrue(doc.getDocumento().getCitedByApiUrl().equals("https://api.openalex.org/works?filter=cites:W2160693392"));
            // UpdatedDate
            Assertions.assertTrue(doc.getDocumento().getUpdatedDate().equals("2024-05-16T22:58:44.774799"));
            // CreatedDate
            Assertions.assertTrue(doc.getDocumento().getCreatedDate().equals("2016-06-24"));
            
            // Ids
            Assertions.assertTrue(doc.getDocumento().getIds().getOpenalex().equals("https://openalex.org/W2160693392"));
            Assertions.assertTrue(doc.getDocumento().getIds().getDoi().equals("https://doi.org/10.1186/s12859-015-0703-0"));
            Assertions.assertTrue(doc.getDocumento().getIds().getMag().equals("2160693392"));
            Assertions.assertTrue(doc.getDocumento().getIds().getPmid().equals("https://pubmed.ncbi.nlm.nih.gov/26307353"));
            Assertions.assertTrue(doc.getDocumento().getIds().getPmcid().equals("https://www.ncbi.nlm.nih.gov/pmc/articles/4549892"));
          
            // Primary location
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getIsOa()==true);
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getLandingPageUrl().equals("https://doi.org/10.1186/s12859-015-0703-0"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getPdfUrl().equals("https://bmcbioinformatics.biomedcentral.com/track/pdf/10.1186/s12859-015-0703-0"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getLicense().equals("cc-by"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getLicenseId().equals("https://openalex.org/licenses/cc-by"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getVersion().equals("publishedVersion"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getIsAccepted()==true);
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getIsPublished()==true);
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getId().equals("https://openalex.org/S19032547"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getDisplayName().equals("BMC bioinformatics"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getIssnL().equals("1471-2105"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getIssn().get(0).equals("1471-2105"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getIsOa()==true);
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getIsInDoaj()==true);
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getHostOrganization().equals("https://openalex.org/P4310320256"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getHostOrganizationName().equals("BioMed Central"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getType().equals("journal"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getHostOrganizationLineage().get(0).equals("https://openalex.org/P4310319965"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryLocation().getSource().getHostOrganizationLineageNames().get(0).equals("Springer Nature"));
            
            // OpenAccess
            Assertions.assertTrue(doc.getDocumento().getOpenAccess().getIsOa()==true);
            Assertions.assertTrue(doc.getDocumento().getOpenAccess().getOaStatus().equals("gold"));
            Assertions.assertTrue(doc.getDocumento().getOpenAccess().getOaUrl().equals("https://bmcbioinformatics.biomedcentral.com/track/pdf/10.1186/s12859-015-0703-0"));
            Assertions.assertTrue(doc.getDocumento().getOpenAccess().getAnyRepositoryHasFulltext()==true);
            
            // CorrespondingAuthorIds
            Assertions.assertTrue(doc.getDocumento().getCorrespondingAuthorIds().get(0).equals("https://openalex.org/A5008590671"));
            
            // CorrespondingInstitutionIds
            Assertions.assertTrue(doc.getDocumento().getCorrespondingInstitutionIds().get(0).equals("https://openalex.org/I168974976"));
            
            // ApcList
            Assertions.assertTrue(doc.getDocumento().getApcList().getValue()==1690);
            Assertions.assertTrue(doc.getDocumento().getApcList().getValueUsd()==2072);
            Assertions.assertTrue(doc.getDocumento().getApcList().getCurrency().equals("GBP"));
            Assertions.assertTrue(doc.getDocumento().getApcList().getProvenance().equals("doaj"));
            
            // CitedByPercentileYear
            Assertions.assertTrue(doc.getDocumento().getCitedByPercentileYear().getMax()==100);
            Assertions.assertTrue(doc.getDocumento().getCitedByPercentileYear().getMin()==99);
            
            // Biblio
            Assertions.assertTrue(doc.getDocumento().getBiblio().getVolume().equals("16"));
            Assertions.assertTrue(doc.getDocumento().getBiblio().getIssue().equals("1"));
            Assertions.assertTrue(doc.getDocumento().getBiblio().getFirstPage()==null);
            Assertions.assertTrue(doc.getDocumento().getBiblio().getLastPage()==null);
            
            // Authorships
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getAuthorPosition().equals("first"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getIsCorresponding()==true);
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getRawAuthorName().equals("Jónathan Heras"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getRawAffiliationStrings().get(0).equals("Department of Mathematics and Computer Science, University of La Rioja, Ed. Vives. C/ Luís de Ulloa 2, Logroño, 26004, Spain"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getCountries().get(0).equals("ES"));
            
            // Authorships.Author
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getAuthor().getId().equals("https://openalex.org/A5008590671"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getAuthor().getDisplayName().equals("Jónathan Heras")); // NO LO MAPEA
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getAuthor().getOrcid().equals("https://orcid.org/0000-0003-4775-1306"));
            
            // Authorships.Institutions
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getId().equals("https://openalex.org/I168974976"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getDisplayName().equals("Universidad de La Rioja"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getRor().equals("https://ror.org/0553yr311"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getCountryCode().equals("ES"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getType().equals("education"));
            Assertions.assertTrue(doc.getDocumento().getAuthorships().get(0).getInstitutions().get(0).getLineage().get(0).equals("https://openalex.org/I168974976"));
            
            // IndexedIn
            List<String> IndexedIn = new ArrayList<>();
            IndexedIn.add("crossref");
            IndexedIn.add("pubmed");
            Assertions.assertTrue(doc.getDocumento().getIndexedIn().equals(IndexedIn));

            // PrimaryTopic
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getId().equals("https://openalex.org/T10751"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getDisplayName().equals("Genomic Analysis of Ancient DNA"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getScore()==0.9955);
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getSubfield().getId().equals("https://openalex.org/subfields/1311"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getSubfield().getDisplayName().equals("Genetics"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getField().getId().equals("https://openalex.org/fields/13"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getField().getDisplayName().equals("Biochemistry, Genetics and Molecular Biology"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getDomain().getId().equals("https://openalex.org/domains/1"));
            Assertions.assertTrue(doc.getDocumento().getPrimaryTopic().getDomain().getDisplayName().equals("Life Sciences"));

            // Keywords
            Assertions.assertTrue(doc.getDocumento().getKeywords().get(0).getId().equals("https://openalex.org/keywords/genomic-analysis"));
            Assertions.assertTrue(doc.getDocumento().getKeywords().get(0).getDisplayName().equals("Genomic Analysis"));
            Assertions.assertTrue(doc.getDocumento().getKeywords().get(0).getScore()==0.466595);
            
            // Concepts
            Assertions.assertTrue(doc.getDocumento().getConcepts().get(0).getId().equals("https://openalex.org/C2777826928"));
            Assertions.assertTrue(doc.getDocumento().getConcepts().get(0).getWikidata().equals("https://www.wikidata.org/wiki/Q3745713"));
            Assertions.assertTrue(doc.getDocumento().getConcepts().get(0).getDisplayName().equals("Fingerprint (computing)"));
            Assertions.assertTrue(doc.getDocumento().getConcepts().get(0).getLevel()==2);
            Assertions.assertTrue(doc.getDocumento().getConcepts().get(0).getScore()==0.75256824);
            
            // Mesh
            Assertions.assertTrue(doc.getDocumento().getMesh().get(0).getDescriptorName().equals("DNA Fingerprinting"));
            Assertions.assertTrue(doc.getDocumento().getMesh().get(0).getDescriptorUi().equals("D016172"));
            Assertions.assertTrue(doc.getDocumento().getMesh().get(0).getQualifierUi().equals("Q000379"));
            Assertions.assertTrue(doc.getDocumento().getMesh().get(0).getQualifierName().equals("methods"));
            Assertions.assertTrue(doc.getDocumento().getMesh().get(0).getIsMajorTopic()==true);
            
            // SustainableDevelopmentGoals
            Assertions.assertTrue(doc.getDocumento().getSustainableDevelopmentGoals().get(0).getId().equals("https://metadata.un.org/sdg/10"));
            Assertions.assertTrue(doc.getDocumento().getSustainableDevelopmentGoals().get(0).getDisplayName().equals("Reduced inequalities"));
            Assertions.assertTrue(doc.getDocumento().getSustainableDevelopmentGoals().get(0).getScore()==0.66);
            
            // Grants
            Assertions.assertTrue(doc.getDocumento().getGrants().get(0).getFunder().equals("https://openalex.org/F4320321837"));
            Assertions.assertTrue(doc.getDocumento().getGrants().get(0).getFunderDisplayName().equals("Ministerio de Economía y Competitividad"));
            Assertions.assertTrue(doc.getDocumento().getGrants().get(0).getAwardId().equals("MTM2014-54151-P"));
            
            // Datasets
            Assertions.assertTrue(doc.getDocumento().getDatasets().get(0).equals("https://openalex.org/W4394562779"));
            
            // Versions
            Assertions.assertTrue(doc.getDocumento().getVersions().equals(new ArrayList<>()));
            
            // ReferencedWorks
            Assertions.assertTrue(doc.getDocumento().getReferencedWorks().get(0).equals("https://openalex.org/W143577276"));
            
            // RelatedWorks
            Assertions.assertTrue(doc.getDocumento().getRelatedWorks().get(0).equals("https://openalex.org/W4310225030"));
            
            // CountsByYear
            Assertions.assertTrue(doc.getDocumento().getCountsByYear().get(0).getYear()==2024);
            Assertions.assertTrue(doc.getDocumento().getCountsByYear().get(0).getCitedByCount()==20);
            
        }
    }
}
