package com.example.sharonlxr.thoughtjot;

/**
 * Created by sharonlxr on 4/25/17.
 */

import android.content.Context;
import android.opengl.GLException;
import android.provider.CalendarContract;
import android.provider.Settings;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import org.w3c.dom.Attr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.regions.Regions;

//import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.auth.CognitoCachingCredentialsProvider;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.dynamodbv2.*;
//import com.amazonaws.services.dynamodbv2.model.*;
//import  com.amazonaws.services.dynamodbv2.*;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
//import com.amazonaws.services.dynamodbv2.model.*;


public class dbHelper {

    //
    AmazonDynamoDBClient ddbClient;
    Context context;
    String tname = "Entries";
//    AmazonDynamoDB client;
    public dbHelper(Context ct) {
        context = ct;
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
    public ArrayList<Map<String,AttributeValue>> searchByTitle(String title){
        System.out.println(title);
        Map<String,AttributeValue> mp = new HashMap<String, AttributeValue>();
        mp.put("Title",new AttributeValue(title));

        GetItemResult re =  ddbClient.getItem("Entries",mp);

        Map<String,AttributeValue> remp = re.getItem();

        ArrayList<Map<String,AttributeValue>> re2 = new ArrayList<Map<String,AttributeValue>>();
                re2.add(remp);
        return re2;

    }
    public List<Map<String,AttributeValue>> searchByRangeAndTitle(String title,int year,int month, int date,int year1,int month1, int date1){

        List<Map<String,AttributeValue>> candidates = searchByRangeDate(year,month,date,year1,month1,date1);
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        for (Map<String,AttributeValue> can : candidates){
            if (can.get("Title").getS()==title){
                re.add(can);
            }
        }
        return re;
    }
    public List<Map<String,AttributeValue>> searchByTag(String tag){
        System.out.println("Tag search "+tag);
        String[] tgs =tag.split(";");
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        for(String tg:tgs){

        Condition cd = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS).withAttributeValueList(new AttributeValue().withS(tg));
            HashMap<String,Condition> conds = new HashMap<>();
            conds.put("Tags",cd);
            QueryRequest queryRequest = new QueryRequest().withTableName(tname).withKeyConditions(conds);
            QueryResult result = ddbClient.query(queryRequest);
            for (Map<String, AttributeValue> item : result.getItems()) {

                if(!re.contains(item)){
                re.add(item);
                }
            }

        }
        return re;


    }
    public List<Map<String,AttributeValue>> searchByKeyAndTag(String key,String tag){
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        ArrayList<Map<String,AttributeValue>> cands = ( ArrayList<Map<String,AttributeValue>>)searchByTag(tag);
        String[] ks= key.split(";");
        for (String k :ks) {
            for (Map<String, AttributeValue> item : cands) {
                if (item.get("Entry").getS().contains(k)) {
                    if(!re.contains(item)){
                        re.add(item);
                    }
                }
            }
        }
        return re;
    }

    public List<Map<String,AttributeValue>> searchByRangeAndTitleAndTag(String title,String tag, int year,int month, int date,int year1,int month1, int date1){

        List<Map<String,AttributeValue>> candidates = searchByRangeDate(year,month,date,year1,month1,date1);
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        for (Map<String,AttributeValue> can : candidates){
            if (can.get("Title").getS()==title){
                re.add(can);
            }
        }
        ArrayList<Map<String,AttributeValue>> re2= new ArrayList<>();
        String[] tags = tag.split(";");
        for (Map<String,AttributeValue> can : re){

            for(String t: tags){
                if(can.get("Tags").getS().contains(t)){
                    re2.add(can);
                    continue;
                }
            }
        }
        return re2;
    }
    public List<Map<String,AttributeValue>> searchByTitleAndTag(String title,String tag){

        ArrayList<Map<String,AttributeValue>> candidates = searchByTitle(title);

        ArrayList<Map<String,AttributeValue>> re2= new ArrayList<>();
        String[] tags = tag.split(";");
        for (Map<String,AttributeValue> can : candidates){

            for(String t: tags){
                if(can.get("Tags").getS().contains(t)){
                    re2.add(can);
                    continue;
                }
            }
        }
        return re2;
    }
    public List<Map<String,AttributeValue>> searchByRangeAndKeyAndTag(String key,String tag, int year,int month, int date,int year1,int month1, int date1){

        List<Map<String,AttributeValue>> candidates = searchByRangeDate(year,month,date,year1,month1,date1);
        String[] keys = key.split("/");
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        for (Map<String,AttributeValue> can : candidates){
            for(String k:keys) {
                if (can.get("Entry").getS().contains(k)) {
                    re.add(can);
                    continue;
                }
            }
        }

        ArrayList<Map<String,AttributeValue>> re2= new ArrayList<>();
        String[] tags = tag.split(";");
        for (Map<String,AttributeValue> can : candidates){

            for(String t: tags){
                if(can.get("Tags").getS().contains(t)){
                    re2.add(can);
                    continue;
                }
            }
        }
        return re2;
    }
    public List<Map<String,AttributeValue>> searchByRangeAndKey(String key, int year,int month, int date,int year1,int month1, int date1){

        List<Map<String,AttributeValue>> candidates = searchByRangeDate(year,month,date,year1,month1,date1);
        String[] keys = key.split("/");
        ArrayList<Map<String,AttributeValue>> re = new ArrayList<>();
        for (Map<String,AttributeValue> can : candidates){
            for(String k:keys) {
                if (can.get("Entry").getS().contains(k)) {
                    re.add(can);
                    continue;
                }
            }
        }

        return re;
    }
    public List<Map<String,AttributeValue>> searchByRangeAndTag(String tag, int year,int month, int date,int year1,int month1, int date1){

        List<Map<String,AttributeValue>> candidates = searchByRangeDate(year,month,date,year1,month1,date1);


        ArrayList<Map<String,AttributeValue>> re2= new ArrayList<>();
        String[] tags = tag.split(";");
        for (Map<String,AttributeValue> can : candidates){

            for(String t: tags){
                if(can.get("Tags").getS().contains(t)){
                    re2.add(can);
                    continue;
                }
            }
        }
        return re2;
    }
    public List<Map<String,AttributeValue>> searchByDate(int year,int month, int date){
//        queryRequestion
        System.out.println("haha");
//        System.out.println(ddbClient.describeTable(tname).toString());
        Calendar cl =  Calendar.getInstance();
        cl.set(Calendar.YEAR,year);
        cl.set(Calendar.MONTH,month);
        cl.set(Calendar.DATE,date);
        cl.set(Calendar.HOUR,cl.getActualMinimum(Calendar.HOUR) );
        cl.set(Calendar.MINUTE,cl.getActualMinimum(Calendar.MINUTE));
        cl.set(Calendar.SECOND,cl.getActualMinimum(Calendar.SECOND));
        cl.set(Calendar.MILLISECOND,cl.getActualMinimum(Calendar.MILLISECOND));
        Calendar cl2 =  Calendar.getInstance();
        cl2.set(Calendar.YEAR,year);
        cl2.set(Calendar.MONTH,month);
        cl2.set(Calendar.DATE,date);
        cl2.set(Calendar.HOUR,cl.getActualMaximum(Calendar.HOUR) );
        cl2.set(Calendar.MINUTE,cl.getActualMaximum(Calendar.MINUTE));
        cl2.set(Calendar.SECOND,cl.getActualMaximum(Calendar.SECOND));
        cl2.set(Calendar.MILLISECOND,cl.getActualMaximum(Calendar.MILLISECOND));
        Date tm = cl.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        String time1 = df.format(tm);
//        String time2 = df.format(cl2.getTime());
//        System.out.println(time1);
//        System.out.println(time2);

//        ddbClient.query();
        String st = String.valueOf( cl.getTimeInMillis());
        String end = String.valueOf(cl2.getTimeInMillis());
        AttributeValue ast = new AttributeValue().withN(String.valueOf(st));
        ast.setN(end);
        AttributeValue est = new AttributeValue().withN(String.valueOf(end));
        System.out.println("start "+ast.toString()+" end "+est.toString());

//        vls.add(est);

        Condition cd = new Condition().withComparisonOperator(ComparisonOperator.BETWEEN).withAttributeValueList(ast,est);
        HashMap<String,Condition> conds = new HashMap<>();
        conds.put("Date",cd);
        ScanRequest sr= new ScanRequest().withTableName(tname);
        QueryRequest queryRequest = new QueryRequest().withTableName(tname);

        ArrayList<Map<String,AttributeValue>> re = new ArrayList<Map<String,AttributeValue>>(){};

//        QueryResult result = ddbClient.query(queryRequest);
        ScanResult result = ddbClient.scan(sr);
        for (Map<String, AttributeValue> item : result.getItems()) {
            re.add(item);
        }
        return re;
    }
    public List<Map<String,AttributeValue>> searchByRangeDate(int year,int month, int date,int year1,int month1, int date1){
//        queryRequestion
        Calendar cl =  Calendar.getInstance();
        cl.set(Calendar.YEAR,year);
        cl.set(Calendar.MONTH,month);
        cl.set(Calendar.DATE,date);
        cl.set(Calendar.HOUR,cl.getActualMinimum(Calendar.HOUR) );
        cl.set(Calendar.MINUTE,cl.getActualMinimum(Calendar.MINUTE));
        cl.set(Calendar.SECOND,cl.getActualMinimum(Calendar.SECOND));
        cl.set(Calendar.MILLISECOND,cl.getActualMinimum(Calendar.MILLISECOND));
        Calendar cl2 =  Calendar.getInstance();
        cl2.set(Calendar.YEAR,year1);
        cl2.set(Calendar.MONTH,month1);
        cl2.set(Calendar.DATE,date1);
        cl2.set(Calendar.HOUR,cl.getActualMaximum(Calendar.HOUR) );
        cl2.set(Calendar.MINUTE,cl.getActualMaximum(Calendar.MINUTE));
        cl2.set(Calendar.SECOND,cl.getActualMaximum(Calendar.SECOND));
        cl2.set(Calendar.MILLISECOND,cl.getActualMaximum(Calendar.MILLISECOND));
        Date tm = cl.getTime();


//        ddbClient.query();
        String st = String.valueOf( cl.getTimeInMillis());
        String end = String.valueOf(cl2.getTimeInMillis());
        AttributeValue ast = new AttributeValue().withN(String.valueOf(st));
        AttributeValue est = new AttributeValue().withN(String.valueOf(end));
        Collection<AttributeValue> vls = new ArrayList<AttributeValue>();
        vls.add(ast);
        vls.add(est);
        Condition cd = new Condition().withComparisonOperator(ComparisonOperator.BETWEEN).withAttributeValueList(vls);
        HashMap<String,Condition> conds = new HashMap<>();
        conds.put("Time",cd);
        QueryRequest queryRequest = new QueryRequest().withTableName(tname).withKeyConditions(conds);

        ArrayList<Map<String,AttributeValue>> re = new ArrayList<Map<String,AttributeValue>>(){};

        QueryResult result = ddbClient.query(queryRequest);
        for (Map<String, AttributeValue> item : result.getItems()) {
            re.add(item);
        }
        return re;
    }


}
