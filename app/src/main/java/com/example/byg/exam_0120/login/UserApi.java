package com.example.byg.exam_0120.login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by byg on 2017-03-14.
 */

public interface UserApi {

    String BASE_URL = "http://suwonsmartapp.iptime.org/test/bae/";

    // 로그인
    @GET("login2.php")
    Call<ResultModel> login(@Query("email") String email,
                            @Query("password") String password);

    // 회원가입
    @GET("insert.php")
    Call<ResultModel> signup(@Query("email") String email,
                             @Query("nickname") String nickname,
                             @Query("password") String password);
}
