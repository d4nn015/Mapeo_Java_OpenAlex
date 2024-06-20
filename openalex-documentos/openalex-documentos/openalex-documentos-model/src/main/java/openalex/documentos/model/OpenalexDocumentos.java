/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package openalex.documentos.model;

import com.mongodb.MongoClientSettings;
import com.querydsl.core.annotations.QueryEntities;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;
import static openalex.documentos.model.OpenalexDocumentos.OPENALEX_MONGO_TEMPLATE_NAME;
import openalex.documentos.model.OpenalexDocumentos.OpenalexMongoProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ClassUtils;

/**
 *
 * @author Javier Hernáez Hurtado
 */
@Configuration
@EnableConfigurationProperties(OpenalexMongoProperties.class)
@EnableMongoRepositories(basePackageClasses = OpenalexDocumentos.class, mongoTemplateRef = OPENALEX_MONGO_TEMPLATE_NAME, considerNestedRepositories = true)
public class OpenalexDocumentos {

    public static final String OPENALEX_MONGO_TEMPLATE_NAME = "openalexMongoTemplate";
    public static final String OPENALEX_MONGO_DBFACTORY_NAME = "openalexMongoDatabaseFactory";
    public static final String OPENALEX_MONGO_CONVERTER_NAME = "openalexMongoConverter";
    public static final String OPENALEX_MONGO_CUSTOMCONVERSIONS_NAME = "openalexMongoCustomConversions";
    public static final String OPENALEX_MONGO_MAPPINGCONTEXT_NAME = "openalexMongoMappingContext";
    public static final String OPENALEX_GRIDFS_TEMPLATE_NAME = "openalexGridFsTemplate";

    @Bean(name = OPENALEX_MONGO_TEMPLATE_NAME)
    MongoTemplate mongoTemplate(@Qualifier(OPENALEX_MONGO_DBFACTORY_NAME) MongoDatabaseFactory MongoDatabaseFactory, @Qualifier(OPENALEX_MONGO_CONVERTER_NAME) MongoConverter mongoConverter) {
        return new MongoTemplate(MongoDatabaseFactory, mongoConverter);
    }

    @Bean(name = OPENALEX_MONGO_CONVERTER_NAME)
    MongoConverter mongoConverter(@Qualifier(OPENALEX_MONGO_DBFACTORY_NAME) MongoDatabaseFactory MongoDatabaseFactory, @Qualifier(OPENALEX_MONGO_MAPPINGCONTEXT_NAME) MongoMappingContext mappingContext, @Qualifier(OPENALEX_MONGO_CUSTOMCONVERSIONS_NAME) MongoCustomConversions mongoCustomConversions) {
        MappingMongoConverter mongoConverter = new MappingMongoConverter(new DefaultDbRefResolver(MongoDatabaseFactory), mappingContext);
        mongoConverter.setCustomConversions(mongoCustomConversions);
        return mongoConverter;
    }

    @Bean(name = OPENALEX_MONGO_DBFACTORY_NAME)
    MongoDatabaseFactory MongoDatabaseFactory(OpenalexMongoProperties properties, Environment env) {
        com.mongodb.client.MongoClient mongoClient = new MongoClientFactory(List.of(new MongoPropertiesClientSettingsBuilderCustomizer(properties, env))).createMongoClient(MongoClientSettings.builder().build());
        return new SimpleMongoClientDatabaseFactory(mongoClient, properties.getMongoClientDatabase());
    }

    @Bean(name = OPENALEX_GRIDFS_TEMPLATE_NAME) // TODO: copiar comportamiento de GridFsMongoDatabaseFactory para respetar el valor de 'gridFsDatabase'
    GridFsTemplate gridFsTemplate(@Qualifier(OPENALEX_MONGO_DBFACTORY_NAME) MongoDatabaseFactory MongoDatabaseFactory, @Qualifier(OPENALEX_MONGO_TEMPLATE_NAME) MongoTemplate mongoTemplate) {
        return new GridFsTemplate(MongoDatabaseFactory, mongoTemplate.getConverter());
    }

    @Bean(name = OPENALEX_MONGO_MAPPINGCONTEXT_NAME)
    MongoMappingContext mappingContext(@Qualifier(OPENALEX_MONGO_CUSTOMCONVERSIONS_NAME) MongoCustomConversions mongoCustomConversions) throws ClassNotFoundException {
        MongoMappingContext context = new MongoMappingContext();
        context.setInitialEntitySet(getMappingContextEntitySet());
        context.setSimpleTypeHolder(mongoCustomConversions.getSimpleTypeHolder());
        return context;
    }

    @Bean(name = OPENALEX_MONGO_CUSTOMCONVERSIONS_NAME)
    MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.emptyList());
    }

    private Set<Class<?>> getMappingContextEntitySet() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Document.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Persistent.class));

        QueryEntities packageInfoQueryEntities = this.getClass().getPackage().getAnnotation(QueryEntities.class);

        return Stream.concat(
                packageInfoQueryEntities != null ? Arrays.stream(packageInfoQueryEntities.value()) : Stream.empty(),
                scanner.findCandidateComponents(this.getClass().getPackage().getName()).stream()
                        .map(beanDefinition -> ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader()))
        ).collect(toSet());
    }

    @ConfigurationProperties(prefix = "openalex.mongodb")
    static class OpenalexMongoProperties extends MongoProperties {
    }

}
