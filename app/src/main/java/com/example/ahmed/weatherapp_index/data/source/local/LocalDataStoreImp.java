package com.example.ahmed.weatherapp_index.data.source.local;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ahmed.weatherapp_index.R;
import com.example.ahmed.weatherapp_index.data.models.City;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

/**
 * Created by ahmed on 2/6/18.
 */

public class LocalDataStoreImp {


//    public static void importFromJson(final Resources resources) {
//        final Realm realm = Realm.getDefaultInstance();
//
//        //transaction timer
//        final long start = System.currentTimeMillis();
//        final long[] end = new long[1];
//
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(@NonNull Realm realm) {
//
//                Log.d("realm", "start " + System.currentTimeMillis());
//                InputStream inputStream = resources.openRawResource(R.raw.citylist);
//                try {
//
//                    realm.createOrUpdateAllFromJson(City.class, inputStream);
//                    end[0] = System.currentTimeMillis();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//
//                Log.d("realm", "end " + System.currentTimeMillis());
//                Log.d("Realm", "createAllFromJson Task completed in " + (start- end[0]) + "ms");
//                realm.close();
//            }
//        });
//    }

}
