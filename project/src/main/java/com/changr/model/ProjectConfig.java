package com.changr.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class ProjectConfig {

    private String type;

    @DynamoDBAttribute(attributeName = "type")
    public String getType() { return type; }
    public void setType(String t) { type = t; }
}
