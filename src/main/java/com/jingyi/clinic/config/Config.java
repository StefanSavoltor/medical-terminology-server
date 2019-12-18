package com.jingyi.clinic.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StringUtils;

import java.net.InetAddress;

@SpringBootApplication(
        exclude = {
                ElasticsearchAutoConfiguration.class,
                ElasticsearchDataAutoConfiguration.class,
        })
//@Configuration
@EnableElasticsearchRepositories(
        basePackages = {
                "com.jingyi.clinic.core.repositories"
        })
@EnableConfigurationProperties
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@EnableAsync
public class Config {
    private static final String CLUSTER_NODES_SPLIT_SYMBOL = ",";
    private static final String HOST_PORT_SPLIT_SYMBOL = ":";

    @Value("${elasticsearch.cluster-name}")
    private String elasticsearchClusterName;

    @Value("${elasticsearch.cluster-nodes}")
    private String elasticsearchClusterNodes;

    @Value("${elasticsearch.username}")
    private String elasticsearchUsername;

    @Value("${elasticsearch.password}")
    private String elasticsearchPassword;

    @Value("${elasticsearch.index.prefix}")
    private String indexNamePrefix;

    @Value("${elasticsearch.index.shards}")
    private short indexShards;

    @Value("${elasticsearch.index.replicas}")
    private short indexReplicas;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Client  transportClient() {
        logger.info("elasticsearch init.");
        String clusterName = elasticsearchClusterName;
        if (StringUtils.isEmpty(clusterName)) {
            throw new RuntimeException("elasticsearch.cluster-name is empty.");
        }
        String clusterNodes = elasticsearchClusterNodes;
        if (StringUtils.isEmpty(clusterNodes)) {
            throw new RuntimeException("elasticsearch.cluster-nodes is empty.");
        }
        try {
            Settings settings = Settings.builder().put("cluster.name", clusterName.trim())
                    .put("client.transport.sniff", false).build();
            TransportClient transportClient = new PreBuiltTransportClient(settings);
            String[] clusterNodeArray = clusterNodes.trim().split(CLUSTER_NODES_SPLIT_SYMBOL);
            for (String clusterNode : clusterNodeArray) {logger.info("elasticsearch init success.");
                String[] clusterNodeInfoArray = clusterNode.trim().split(HOST_PORT_SPLIT_SYMBOL);
                TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(clusterNodeInfoArray[0]),
                        Integer.parseInt(clusterNodeInfoArray[1]));
                transportClient.addTransportAddress(transportAddress);
            }

            return transportClient;
        } catch (Exception e) {
            throw new RuntimeException("elasticsearch init fail.");
        }
    }

    @Bean
    public ElasticsearchProperties elasticsearchProperties() {
        return new ElasticsearchProperties();
    }
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(transportClient());
    }
}
