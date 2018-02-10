package com.example.ahmed.weatherapp_index.data.source.remote.api;

import android.support.annotation.NonNull;

import com.example.ahmed.weatherapp_index.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmed on 2/5/18.
 */

public class WeatherApiClient {

    private static Retrofit sRetrofit = null;

    public WeatherApiClient() {
    }

    public static Retrofit getClient() {

        if (sRetrofit == null) {
            synchronized (Retrofit.class) {
                if (sRetrofit == null) {

                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    if (BuildConfig.DEBUG)
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

                    Interceptor apiKeyInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();

                            //TODO add weather api key
                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("APPID", "your api key")
                                    .build();


                            Request.Builder requestBuilder = original.newBuilder().url(url);

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    };

                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .addInterceptor(apiKeyInterceptor)
                            .build();

                    sRetrofit = new Retrofit.Builder()
                            .baseUrl("http://api.openweathermap.org/data/2.5/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }

            }
        }
        return sRetrofit;
    }

}
