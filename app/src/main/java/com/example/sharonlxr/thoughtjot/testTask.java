package com.example.sharonlxr.thoughtjot;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.Condition;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class testTask extends AsyncTask<String, Void, String> {
    Context mContext;
    public testTask(Context ct){
        mContext= ct;

    }
    @Override
    protected String doInBackground(String... params) {
        System.out.println("haha");
        String poorId = "us-east-1_msv738quu";
        String access_id_key="AKIAIE57ZEWULGAK4IUA";
//        access_id_key="AKIAIUJ7UGBTJSYJ6ZEQ";
        String access_key = "9ukYEQzb7VrpAvtmmDC1iZ9Lp3ITpsDK6m5dbGkr";
//        access_key= "RToIDPf7cKNwLYUZaLmWoiJTWSuCpTIIy8kRYHWl";
        BasicAWSCredentials bc = new BasicAWSCredentials(access_id_key,access_key);
        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(bc);


        return ddbClient.describeTable("Entries").toString();
//        testHelper tk = new testHelper(mContext);
//        return "Done";
    }
    @Override
    protected void onPostExecute(String result){
        System.out.println(result);

    }
}
