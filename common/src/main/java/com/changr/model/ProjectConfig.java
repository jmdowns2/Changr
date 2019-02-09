package com.changr.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class ProjectConfig {

    private String type;
    private String url;

    @DynamoDBAttribute(attributeName = "type")
    public String getType() { return type; }
    public void setType(String t) { type = t; }

    @DynamoDBAttribute(attributeName = "url")
    public String getUrl() { return url; }
    public void setUrl(String u) { url = u; }

}
