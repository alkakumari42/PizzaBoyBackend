package com.pizzaboy.backend.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaboy.backend.model.Item;
import com.pizzaboy.backend.model.Order;
import io.micrometer.common.util.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "com.pizzaboy.backend.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAWSCredentials());

        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }

    //TODO unit test
    @Bean
    public String createTable() throws JsonProcessingException {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB());
            createItemTable(dynamoDBMapper);
            //Create order table
            System.out.println("creating table");
            String orederTableName = createOrderTable(dynamoDBMapper);
            System.out.println("Table created: " + orederTableName);
            System.out.println("Describe table = "+amazonDynamoDB().describeTable("Order").getTable().getTableName());
            return orederTableName;
    }

    private void createItemTable(DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Item.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        try {
            amazonDynamoDB().createTable(tableRequest).getTableDescription().getTableName();
        }
        catch (ResourceInUseException e) {
            System.out.println(e);
        }
    }


    private String createOrderTable(DynamoDBMapper dynamoDBMapper) throws JsonProcessingException {
        System.out.println("Inside createOrderTable method");
        //amazonDynamoDB().deleteTable("Order");
        CreateTableRequest orderTableRequest = dynamoDBMapper
                .generateCreateTableRequest(Order.class);
        orderTableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        GlobalSecondaryIndex emailIndex = new GlobalSecondaryIndex();
        emailIndex.setIndexName("EmailIndex");
        emailIndex.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        emailIndex.setKeySchema(Arrays.asList(
                new KeySchemaElement().withAttributeName("userEmail").withKeyType(KeyType.HASH),
                new KeySchemaElement().withAttributeName("status").withKeyType(KeyType.RANGE))
        );
        emailIndex.setProjection(new Projection().withProjectionType(ProjectionType.ALL));
        GlobalSecondaryIndex statusIndex = new GlobalSecondaryIndex();
        statusIndex.setIndexName("StatusIndex");
        statusIndex.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        statusIndex.setKeySchema(Arrays.asList(
                new KeySchemaElement().withAttributeName("status").withKeyType(KeyType.HASH),
                new KeySchemaElement().withAttributeName("creationTimeStamp").withKeyType(KeyType.RANGE))
        );
        statusIndex.setProjection(new Projection().withProjectionType(ProjectionType.ALL));
        orderTableRequest.setGlobalSecondaryIndexes(Arrays.asList(statusIndex, emailIndex));
        orderTableRequest.getGlobalSecondaryIndexes()
                .forEach(v -> v.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L)));
        try {
            amazonDynamoDB().createTable(orderTableRequest);
        } catch (ResourceInUseException e) {
            System.out.println(e);
        }

        return "Order";
    }
}
