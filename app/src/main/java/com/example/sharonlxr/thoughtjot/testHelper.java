package com.example.sharonlxr.thoughtjot;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class testHelper {
    //
    AmazonDynamoDBClient ddbClient;
    Context context;
    String tname = "Entries";
    //    AmazonDynamoDB client;
    public testHelper() {
//        context = ct;
//        String poorId = "us-east-1_msv738quu";
        String access_id_key="AKIAIE57ZEWULGAK4IUA";
        String access_key = "9ukYEQzb7VrpAvtmmDC1iZ9Lp3ITpsDK6m5dbGkr";
        BasicAWSCredentials bc = new BasicAWSCredentials(access_id_key,access_key);
//            String poorArn = " arn:aws:cognito-idp:us-east-1:612224096706:userpool/us-east-1_msv738quu";
//


//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(ct,poorId, Regions.US_EAST_1);
        ddbClient = new AmazonDynamoDBClient(bc);
        System.out.println(ddbClient.describeTable(tname));

//        AmazonDynamoDB client = new AmazonDynamoDBClient(bc);

    }
}
