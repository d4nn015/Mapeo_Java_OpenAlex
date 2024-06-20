package docserver.importador.openalex;

import dialnet.docserver.model.documentos.Documento;
import dialnet.docserver.model.documentos.DocumentoContent;
import dialnet.docserver.model.documentos.DocumentoIdentificador;
import dialnet.docserver.model.documentos.DocumentoRepository;
import dialnet.docserver.model.documentos.VersionDocumentoId;
import dialnet.docserver.model.versiones.VersionDocumentoFuenteIdentificador;
import docserver.importador.openalex.mapper.DocumentoOpenAlexMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import lombok.extern.slf4j.Slf4j;
import openalex.documentos.model.DocumentoOpenalex;
import openalex.documentos.model.DocumentoOpenalexRepository;
import openalex.documentos.model.documentoV3.DocumentosV3Repository;
import openalex.documentos.model.versiones.ContentVersion;
import openalex.documentos.model.versiones.IdentificadorVersion;
import openalex.documentos.model.versiones.Versiones;
import openalex.documentos.model.versiones.VersionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de pasar los documentos de la base de datos intermedia de
 * OpenAlex (openalex.documentos) a la base de datos
 * docserver(docserver.documentos_v2)
 *
 * @author Javier Hernáez Hurtado
 */
@Slf4j
@Component
public class Importador implements ApplicationRunner {

    @Autowired
    private DocumentoOpenalexRepository documentoOpenalexRepository;

    @Autowired
    private DocumentoRepository documentoRepository;
    
    @Autowired
    private VersionesRepository versionesRepository;
    
    @Autowired
    private DocumentosV3Repository documentoV3Repository;
    
    /**
     * Comprueba si existe el documento en la colección documentos_v3 o versiones_v3
     * @param documento
     * @return 
     */
    private boolean existe(openalex.documentos.model.Documento documento) {
        DocumentoOpenAlexMapper mapper = new DocumentoOpenAlexMapper();
        return Optional.ofNullable(documento)
                .map(mapper::crearIdentificadores).stream()
                .flatMap(identificadores -> identificadores.stream())
                .anyMatch(id
                        -> documentoV3Repository.existsByIdentificador(id.getSistema().name(), id.getId()) || versionesRepository.existsByIdentificador(id.getSistema().name(), id.getId()));
    }
    
    /**
     * Transforma el documento openAlex en el documento a insertar en documentos_v2
     * @param documentoOpenalex
     * @return 
     */
    private Documento map(openalex.documentos.model.DocumentoOpenalex documentoOpenalex) {
        DocumentoOpenAlexMapper mapper = new DocumentoOpenAlexMapper();
        DocumentoContent documentoContent = mapper.map(documentoOpenalex);
        VersionDocumentoId versionDocumentoId = new VersionDocumentoId(documentoOpenalex.getDocumento().getId().replace("https://openalex.org/", "").toUpperCase());

        if (documentoContent != null) {
            VersionDocumentoFuenteIdentificador versionInicialFuenteIdentificador
                    = new VersionDocumentoFuenteIdentificador(
                            VersionDocumentoFuenteIdentificador.Sistema.BIBUR,
                            documentoOpenalex.getId().replace("https://openalex.org/", "").toUpperCase());

            return new Documento(documentoContent, versionDocumentoId, versionInicialFuenteIdentificador);
        } else {
            return null;
        }
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

        documentoOpenalexRepository.findAll().parallelStream()
                .filter(documento -> !existe(documento.getDocumento()))
                .map(this::map) 
                .filter(mappedDocumento -> {return mappedDocumento != null;})
                .forEach(documentoRepository::save);
    }
}
