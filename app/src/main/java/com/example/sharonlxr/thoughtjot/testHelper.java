package com.example.sharonlxr.thoughtjot;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class testHelper {
    //
    AmazonDynamoDBClient ddbClient;
    Context context;
    String tname = "Entries";
    //    AmazonDynamoDB client;
    public testHelper(Context ct) {
//        context = ct;
        String poorId = "us-east-1_msv738quu";
        String access_id_key="AKIAIE57ZEWULGAK4IUA";
        String access_key = "9ukYEQzb7VrpAvtmmDC1iZ9Lp3ITpsDK6m5dbGkr";
        BasicAWSCredentials bc = new BasicAWSCredentials(access_id_key,access_key);


//            String poorArn = " arn:aws:cognito-idp:us-east-1:612224096706:userpool/us-east-1_msv738quu";
//


//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(ct,poorId, Regions.US_EAST_1);
        ddbClient = new AmazonDynamoDBClient(bc);
//        Regions rs = Regions.US_EAST_1;
        ddbClient.setRegion(Region.getRegion(Regions.US_EAST_1));

//        ddbClient.setRegion(rs);
//        System.out.println( ;);
//        System.out.println("done");

        System.out.println(ddbClient.describeTable(tname).toString());

//        AmazonDynamoDB client = new AmazonDynamoDBClient(bc);

    }
}
