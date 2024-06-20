package docserver.importador.openalex.mapper;

import openalex.documentos.model.*;
import dialnet.docserver.model.documentos.DocumentoContent;
import dialnet.docserver.model.documentos.*;
import dialnet.docserver.model.documentos.Acceso.AccesoTipo;
import dialnet.docserver.model.documentos.Afiliacion.AfiliacionIdentificador;
import dialnet.docserver.model.documentos.Autor.AutorIdentificador;
import dialnet.docserver.model.documentos.DocumentoIdentificador.DocumentoIdentificadorSistema;
import dialnet.docserver.model.documentos.DocumentoSource.*;
import dialnet.docserver.model.documentos.Enlace.EnlaceTipoAcceso;
import dialnet.docserver.model.documentos.SourceIdentificador.SourceIdentificadorSistema;
import org.springframework.stereotype.Component;

import java.util.*;
import static java.util.stream.Collectors.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * Esta clase convierte un documento de Scopus que proviene de la respuesta de
 * su API en un DocumentoContent del docserver
 *
 * @author
 */
@Component
@Slf4j
public class DocumentoOpenAlexMapper {
    
    private final List<AfiliacionCodigoAf> listaAfiliacionCodigoAf = new ArrayList<>();

    public DocumentoContent map(DocumentoOpenalex response) {

        log.debug("Convirtiendo el documento {} al modelo de docserver", response.getId());

        openalex.documentos.model.Documento documento = response.getDocumento();

        DocumentoType docType = mapDocumentoType(documento);

        SourceType sourceType = mapSourceType(documento);

        // No continuo si no son tipos de documento o source contemplados en docserver
        if (docType == null || sourceType == null) {
            return null;
        }

        Titulo titulo = crearTitulo(documento); // Sin idioma

        Idioma idioma = null;
        if (documento.getLanguage() != null) {
            idioma = Idioma.of(documento.getLanguage());
        }

        List<String> keywords = crearKeywords(documento);

        Acceso acceso = crearAcceso(documento);

        List<Referencia> referencias = crearReferencias(documento);

        List<Enlace> enlaces = crearEnlaces(documento);

        List<Resumen> resumenes = crearResumenes(documento); //Sin idioma

        List<Afiliacion> afiliaciones = crearAfiliaciones(documento); // Solo afiliacionId, literal e identificadores
        
        List<Autor> autores = crearAutores(documento); // Solo literal, afiliaciones e identificadores

        DocumentoSource source = crearSource(documento);

        List<DocumentoIdentificador> identificadores = crearIdentificadores(documento);

        return new DocumentoContent(docType, source, titulo, null, idioma, autores, afiliaciones, resumenes, identificadores, enlaces, referencias, keywords, null, acceso, null, null, DocumentoContent.FasePublicacion.DEFINITIVO);
    }

    
    private DocumentoSource crearSource(openalex.documentos.model.Documento documento) {

        SourceType sourceType = mapSourceType(documento);

        String titulo = documento.getPrimaryLocation().getSource().getDisplayName();

        List<ISSN> issn = crearSourceIssn(documento);

        List<ISBN> isbn = new ArrayList<>(); // No viene en OpenAlex

        List<Autor> autores = new ArrayList<>(); // No viene en OpenAlex

        PublicationDate publicationDate = crearSourcePublicationDate(documento);

        PublicationYear publicationYear = new PublicationYear(documento.getPublicationYear(), null);

        VolumeIssue volumeIssue = crearSourceVolumeIssue(documento);

        Pages pages = null;
        if (documento.getBiblio().getFirstPage() != null) {
            pages = new Pages(documento.getBiblio().getFirstPage(), documento.getBiblio().getLastPage());
        }

        List<Editor> editores = new ArrayList<>();
        if (documento.getPrimaryLocation().getSource().getHostOrganizationName() != null) {
            editores.add(new Editor(documento.getPrimaryLocation().getSource().getHostOrganizationName()));
        }

        List<SourceIdentificador> identificadores = new ArrayList<>();
        identificadores.add(new SourceIdentificador(SourceIdentificadorSistema.OPENALEX, quitarUrlId(documento.getPrimaryLocation().getSource().getId(), "openalex")));

        return new DocumentoSource(null, sourceType, titulo, issn, isbn, publicationYear, null, volumeIssue, pages, publicationDate, null, editores, autores, identificadores);
    }

    private VolumeIssue crearSourceVolumeIssue(openalex.documentos.model.Documento documento) {

        VolumeIssue volumeIssue = null;

        if (documento.getBiblio().getVolume() == null) {
            return volumeIssue;
        }

        Volume volume = new Volume(null, documento.getBiblio().getVolume(), null);

        Issue issue = null;
        if (documento.getBiblio().getIssue() != null) {
            issue = new Issue(null, documento.getBiblio().getIssue(), null);
        }

        return new VolumeIssue(volume, issue, null);
    }

    private PublicationDate crearSourcePublicationDate(openalex.documentos.model.Documento documento) {

        String[] partes = documento.getPublicationDate().split("-");
        int year = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]);
        int day = Integer.parseInt(partes[2]);

        return new PublicationDate(java.time.Year.of(year),
                java.time.Month.of(month),
                day);
    }

    private List<ISSN> crearSourceIssn(openalex.documentos.model.Documento documento){
        
        return Optional.ofNullable(documento.getPrimaryLocation())
        .map(primaryLocation -> primaryLocation.getSource())
        .map(source -> source.getIssn())
        .stream()
        .flatMap(List::stream)
        .map(issnOpenalex -> new ISSN(ISSN.Type.DESCONOCIDO, issnOpenalex))
        .collect(toList());
    }
    
    @Data
    @AllArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@PersistenceConstructor})
    private static class AfiliacionCodigoAf{
        private String codigoAf;
        private String idOpenAlex;
    }
    
    
    private List<Afiliacion> crearAfiliaciones(openalex.documentos.model.Documento documento) {
        
        List<Afiliacion> listaAfiliaciones = new ArrayList<>();
        int contador = 1;
        for(Authorship authorship : documento.getAuthorships()){
            if(!authorship.getInstitutions().isEmpty()){
                for(Institution institution : authorship.getInstitutions()){
                    
                    boolean existe = false;
                    
                    for(AfiliacionCodigoAf afiliacionCodigoAf : listaAfiliacionCodigoAf){
                        if(afiliacionCodigoAf.getIdOpenAlex().equalsIgnoreCase(institution.getId().replace("https://openalex.org/", "").toUpperCase())){
                            existe = true;
                        }
                    }
                    
                    if(!existe){
                        String idOpenalex = quitarUrlId(institution.getId(), "openalex");
                        AfiliacionId afiliacionId = new AfiliacionId("af" + contador);

                        AfiliacionCodigoAf afiliacionCodigoAf = new AfiliacionCodigoAf(afiliacionId.getId(), idOpenalex);
                        listaAfiliacionCodigoAf.add(afiliacionCodigoAf);

                        String literal = institution.getDisplayName();

                        List<AfiliacionIdentificador> identificadores = List.of(
                                new AfiliacionIdentificador(Afiliacion.AfiliacionIdentificadorSistema.OPENALEX, quitarUrlId(institution.getId(), "openalex")),
                                institution.getRor() != null ? new AfiliacionIdentificador(Afiliacion.AfiliacionIdentificadorSistema.ROR, quitarUrlId(institution.getRor(), "ror")) : null
                        ).stream().filter(id -> id != null).collect(toList());


                        Afiliacion afiliacion = new Afiliacion(afiliacionId, literal, null, null, null, null, null, null, null, null, null, null, null, identificadores);
                        listaAfiliaciones.add(afiliacion);

                        contador ++;
                    }
                }
            }
        }
        return listaAfiliaciones;
    }

    
    private List<Autor> crearAutores(openalex.documentos.model.Documento documento) {
        
        List<Autor> listaAutores = new ArrayList<>();
        
        for(Authorship authorship : documento.getAuthorships()){
            String literal = Optional.ofNullable(authorship)
                            .map(Authorship::getRawAuthorName)
                            .orElse(authorship.getAuthor().getDisplayName());
            
            String nombre = authorship.getAuthor().getDisplayName();
            
            List<AfiliacionId> afiliaciones = new ArrayList<>();
            if(!authorship.getInstitutions().isEmpty()){
                for(Institution institution : authorship.getInstitutions()){
                    for(AfiliacionCodigoAf afiliacionCodigoAf : listaAfiliacionCodigoAf){
                        if (afiliacionCodigoAf.getIdOpenAlex().equalsIgnoreCase(institution.getId().replace("https://openalex.org/", "").toUpperCase())){
                            afiliaciones.add(new AfiliacionId(afiliacionCodigoAf.getCodigoAf()));
                        }
                    }
                }
            }
            
            List<AutorIdentificador> identificadores = new ArrayList<>();
            identificadores.add(
                    new AutorIdentificador(Autor.AutorIdentificadorSistema.OPENALEX, quitarUrlId(authorship.getAuthor().getId(), "openalex"))
            );

            if (authorship.getAuthor().getOrcid() != null) {
                identificadores.add(new AutorIdentificador(Autor.AutorIdentificadorSistema.ORCID, quitarUrlId(authorship.getAuthor().getOrcid(), "orcid")));
            }
                
            listaAutores.add(new Autor(null, null, null, literal, nombre, null, null, null, null, afiliaciones, identificadores));
        }
        
        listaAfiliacionCodigoAf.clear();
        return listaAutores;
    }

    private Titulo crearTitulo(openalex.documentos.model.Documento documento) {

        String tituloOpenAlex = documento.getTitle();

        if (tituloOpenAlex == null) {
            tituloOpenAlex = "Sin título en OpenAlex";
        }

        return new Titulo(tituloOpenAlex, null, null);
    }

    private List<String> crearKeywords(openalex.documentos.model.Documento documento) {

        List<String> listaKeywords = new ArrayList<>();

        if (!documento.getKeywords().isEmpty()) {

            listaKeywords = documento.getKeywords()
                    .stream()
                    .map(Keyword::getDisplayName)
                    .collect(toList());
        }

        return listaKeywords;
    }

    private Acceso crearAcceso(openalex.documentos.model.Documento documento) {

        if (documento.getOpenAccess().getIsOa()) {
            return new Acceso(AccesoTipo.openAccess);
        } else {
            return new Acceso(AccesoTipo.closedAccess);
        }
    }

    private List<Enlace> crearEnlaces(openalex.documentos.model.Documento documento) {

        if (documento.getLocations() == null) {
            return Collections.emptyList();
        }

        return documento.getLocations().stream()
                .filter(location -> location.getLandingPageUrl() != null)
                .map(location -> new Enlace(
                location.getLandingPageUrl(),
                Enlace.EnlaceTipo.LANDING_PAGE,
                location.getIsOa() ? EnlaceTipoAcceso.LIBRE : EnlaceTipoAcceso.RESTRINGIDO,
                null))
                .collect(toList());
    }

    private List<Resumen> crearResumenes(openalex.documentos.model.Documento documento) {

        if (documento.getAbstractInvertedIndex() == null) {
            return Collections.emptyList();
        }

        List<Resumen> listaResumen = new ArrayList<>();
        listaResumen.add(new Resumen(documento.getAbstractInvertedIndex(), null));

        return listaResumen;

    }

    
    private List<Referencia> crearReferencias(openalex.documentos.model.Documento documento) {
    if (documento.getRelatedWorks() == null) {
        return new ArrayList<>();
    }

    return documento.getReferencedWorks().stream()
        .map(referencia -> {
            List<DocumentoIdentificador> listaIdentificador = List.of(
                new DocumentoIdentificador(DocumentoIdentificadorSistema.OPENALEX, quitarUrlId(referencia, "openalex"))
            );
            return new Referencia(quitarUrlId(referencia, "openalex"), null, listaIdentificador, null, null, null, null, null, null, null);
        })
        .collect(toList());
    }


    private DocumentoType mapDocumentoType(openalex.documentos.model.Documento documento) {

        DocumentoType documentoType = null;

        if (documento.getType() != null) {

            switch (documento.getType()) {
                case "article":
                    documentoType = DocumentoType.ARTICLE;
                    break;
                case "report":
                    documentoType = DocumentoType.REPORT;
                    break;
                case "book":
                    documentoType = DocumentoType.BOOK;
                    break;
                case "book-chapter":
                    documentoType = DocumentoType.BOOK_CHAPTER;
                    break;
                case "editorial":
                    documentoType = DocumentoType.EDITORIAL;
                    break;
                case "erratum":
                    documentoType = DocumentoType.ERRATUM;
                    break;
                case "review":
                    documentoType = DocumentoType.REVIEW;
                    break;
                case "dissertation":
                    documentoType = DocumentoType.DISSERTATION;
                    break;
                default:
                    return null;
            }
        }

        return documentoType;
    }

    
    public SourceType mapSourceType(openalex.documentos.model.Documento documento) {

        String openalexSourceType = Optional.ofNullable(documento.getPrimaryLocation())
            .map(primaryLocation -> primaryLocation.getSource())
            .map(source -> source.getType())
            .orElse(null);

        SourceType sourceType = null;

        if (openalexSourceType != null) {

            switch (openalexSourceType) {
                case "journal":
                    sourceType = SourceType.JOURNAL;
                    break;
                case "book series":
                    sourceType = SourceType.BOOK_SERIES;
                    break;
                case "conference":
                    sourceType = SourceType.CONFERENCE_PROCEEDING;
                    break;
                default:
                    return null;
            }

        }

        return sourceType;
    }

    private String quitarUrlId(String urlId, String tipo) {

        String prefijo = "";

        switch (tipo) {
            case "openalex":
                prefijo = "https://openalex.org/";
                break;
            case "doi":
                prefijo = "https://doi.org/";
                break;
            case "pmid":
                prefijo = "https://pubmed.ncbi.nlm.nih.gov/";
                break;
            case "pmcid":
                prefijo = "https://www.ncbi.nlm.nih.gov/pmc/articles/";
                break;
            case "ror":
                prefijo = "https://ror.org/";
                break;
            case "orcid":
                prefijo = "https://orcid.org/";
                break;
        }

        return (urlId.substring(prefijo.length()).toUpperCase());
    }

    
    public List<DocumentoIdentificador> crearIdentificadores(openalex.documentos.model.Documento documento) {

        List<DocumentoIdentificador> ids = new ArrayList<>();

        //OpenAlex
        ids.add(new DocumentoIdentificador(DocumentoIdentificadorSistema.OPENALEX,
                quitarUrlId(documento.getId().toUpperCase(), "openalex")));

        //DOI
        if (documento.getDoi() != null) {
            ids.add(new DocumentoIdentificador(DocumentoIdentificadorSistema.DOI,
                    quitarUrlId(documento.getDoi().toUpperCase(), "doi")));
        }

        //PMCID
        if (documento.getIds().getPmcid() != null) {
            ids.add(new DocumentoIdentificador(DocumentoIdentificadorSistema.PMCID,
                    quitarUrlId(documento.getIds().getPmcid().toUpperCase(), "pmcid")));
        }

        //PMID
        if (documento.getIds().getPmid() != null) {
            ids.add(new DocumentoIdentificador(DocumentoIdentificadorSistema.PMID,
                    quitarUrlId(documento.getIds().getPmid(), "pmid")));
        }

        //MAG
        if (documento.getIds().getMag()!= null) {
            ids.add(new DocumentoIdentificador(DocumentoIdentificadorSistema.MAG, documento.getIds().getMag()));
        }
        
        return ids;

    }
}
