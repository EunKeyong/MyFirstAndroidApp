package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.POST;

public class NaverTranslateApiActivity extends AppCompatActivity implements Callback<NaverTranslateApiActivity.Result> {
    private TextView mResultTextView;
    private EditText mTextEditText;
    private Spinner mStartLanSpinner;
    private Spinner mEndLanSpinner;

    public static final String BASE_URL = "https://openapi.naver.com/v1/language/translate/";

    public static class Result {
        public String translatedText;
    }

    private NaverTranslateApi mNaverTranslateApi;

    interface NaverTranslateApi {
        @POST("/")
        Call<Result> getTranslatedText(@Field("X-Naver-Client-Id") String id,
                                       @Field("X-Naver-Client-Secret") String secret,
                                       @Field("source") String source,
                                       @Field("target") String target,
                                       @Field("text") String text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_translate_api);

        mTextEditText = (EditText) findViewById(R.id.text_edit);
        mStartLanSpinner = (Spinner) findViewById(R.id.start_lang_spinner);
        mEndLanSpinner = (Spinner) findViewById(R.id.end_lang_spinner);
        mResultTextView = (TextView) findViewById(R.id.result_text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mNaverTranslateApi = retrofit.create(NaverTranslateApi.class);
    }

    public void onClick(View view) {
        String text = mTextEditText.getText().toString();
        String startLang = (String) mStartLanSpinner.getSelectedItem();
        String endLang = (String) mEndLanSpinner.getSelectedItem();

        Call<Result> call = mNaverTranslateApi.getTranslatedText("Aht7t1WiFQMHTYW94BoJ", "UtvBkgFytf", startLang, endLang, text);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        mResultTextView.setText(response.body().translatedText);
    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
