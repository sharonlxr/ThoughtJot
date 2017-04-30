package com.example.sharonlxr.thoughtjot;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharonlxr on 4/29/17.
 */

public class accountDB {
    //
    AmazonDynamoDBClient ddbClient;
//    Context context;
    String tname = "Accounts";
    //    AmazonDynamoDB client;
    public accountDB() {

//        String poorId = "us-east-1_msv738quu";
        System.out.println("Create");
        String access_id_key="AKIAIE57ZEWULGAK4IUA";
        String access_key = "9ukYEQzb7VrpAvtmmDC1iZ9Lp3ITpsDK6m5dbGkr";
        BasicAWSCredentials bc = new BasicAWSCredentials(access_id_key,access_key);
//            String poorArn = " arn:aws:cognito-idp:us-east-1:612224096706:userpool/us-east-1_msv738quu";
//


//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(ct,poorId, Regions.US_EAST_1);
        ddbClient = new AmazonDynamoDBClient(bc);
        ddbClient.setRegion(Region.getRegion(Regions.US_EAST_1));
//        System.out.println(ddbClient.listTables().getTableNames());
//        System.out.println(ddbClient.describeTable(tname));

//        AmazonDynamoDB client = new AmazonDynamoDBClient(bc);

    }
    public boolean login(int id, String pw){
        AttributeValue ast = new AttributeValue().withN(String.valueOf(id));
        Condition cd = new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(ast);
        HashMap<String,Condition> conds = new HashMap<>();
        conds.put("ID",cd);
        ScanRequest queryRequest = new ScanRequest().withTableName(tname).withScanFilter(conds);
        ScanResult sr =  ddbClient.scan(queryRequest);
        if(sr.getCount()==0){
            return  false;
        }
        if(sr.getCount()!=1){
            return  false;
        }
        String pws = sr.getItems().get(0).get("password").getS().toString();
        if(pw.compareTo(pws)==0){
            return  true;
        }


        return false;
    }
    public boolean signup(int id,String name,String pw){
        AttributeValue ast = new AttributeValue().withN(String.valueOf(id));
        Condition cd = new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(ast);
        HashMap<String,Condition> conds = new HashMap<>();
        conds.put("ID",cd);
        ScanRequest queryRequest = new ScanRequest().withTableName(tname).withScanFilter(conds);
        ScanResult sr =  ddbClient.scan(queryRequest);
        if(sr.getCount()>=1){
            return false;
        }
        HashMap<String,AttributeValue> keys= new HashMap<>();
        keys.put("ID",new AttributeValue().withN(String.valueOf(id)));
//        keys.put("Tags",new AttributeValue().withS(oldtags));
        HashMap<String,AttributeValueUpdate> updates= new HashMap<>();
        AttributeValueUpdate nm=  new AttributeValueUpdate();
        nm.setAction(AttributeAction.PUT);
        nm.setValue(new AttributeValue().withS(name));
        updates.put("Name",nm);
        AttributeValueUpdate pws=  new AttributeValueUpdate();
        pws.setAction(AttributeAction.PUT);
        pws.setValue(new AttributeValue().withS(pw));
        updates.put("password",pws);

        ddbClient.updateItem(tname,keys,updates);
        return true;
//        System.out.println("Succeed");

    }
    public void createNewTable(int id){

    }
}
