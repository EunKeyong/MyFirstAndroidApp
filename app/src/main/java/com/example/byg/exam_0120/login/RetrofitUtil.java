package com.example.byg.exam_0120.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by byg on 2017-03-14.
 */

public class RetrofitUtil {

    private Retrofit mRetrofit;

    private   UserApi mUserApi;

    public UserApi getmUserApi() {
        return mUserApi;
    }

    public void setmUserApi(UserApi mUserApi) {
        this.mUserApi = mUserApi;
    }

    public RetrofitUtil() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(UserApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mUserApi = mRetrofit.create(UserApi.class);
    }
}
