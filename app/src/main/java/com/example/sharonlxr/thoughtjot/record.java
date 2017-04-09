package com.example.sharonlxr.thoughtjot;


import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.model.*;
@DynamoDBTable(tableName = "Records")
public class record {
    private String title;

    private String time;
    private String tags;
    private String content;
    @DynamoDBIndexHashKey(attributeName = "title")
    public String getTitle(){
        return title;

    }
    @DynamoDBRangeKey(attributeName = "time")
    public String getTime(){
        return time;
    }
    @DynamoDBAttribute(attributeName = "tags")
    public String getTags(){
        return tags;
    }

    @DynamoDBAttribute(attributeName = "contents")
    public String getContent(){
        return content;
    }
    public void setContent(String newC){
        content = newC;
    }
}
