package com.example.byg.exam_0120.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

public class AsyncTaskActivity extends AppCompatActivity {
private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mTextView = (TextView) findViewById(R.id.text_view);

        // 제약 사항
        // 1. AsyncTask 클래스는 반드시 UI 스레드에서 로드 해야 한다.
        // 2. AsyncTask 인스턴스는 반드시 UI 스레드에서 생성해야 된다.(메인스레드에서 생성해야함)
        // 3. execute() 도 반드시 UI 스레드에서 호출해야 한다.
        // 4. 모든 콜백들은 직접 호출하면 안된다.
        // 5. 태스크 인스턴스는 한번만 실행할 수 있다.

//        MyAsyncTask task = new MyAsyncTask();
//        task.execute(0);
//        // 캔슬가능
//        task.cancel(true);

        // 일반 적인 사용법(캔슬 안할때)
        // 순차적으로 실행
        new MyAsyncTask().execute(0);
       // new MyAsyncTask().execute(0);

        // 병렬로 수행되는 AsyncTask
       // new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);
       // new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);

    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        // 시작했을때 처리
        @Override
        protected void onPreExecute() {
            // 최초 실행되는 부분
        }

        @WorkerThread
        @Override
        protected Integer doInBackground(Integer... params) {
            // 오래 걸리는 처리
            // 스레드로 동작하는 메서드
            int number = params[0];
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number++;
                // UI 갱신
                publishProgress(number); // onProgressUpdate 로 넘어감
            }
            // 마지막 숫자 리턴
            return number; // onPostExecute 로 넘어감
        }

        @UiThread
        @Override
        protected void onProgressUpdate(Integer... values) {
            // UI 갱신
            mTextView.setText(values[0]);
        }

        // 끝났을때 처리
        @UiThread
        @Override
        protected void onPostExecute(Integer integer) {
            Log.d("AsyncTask", "onPostExecute: " + integer);
        }

        // 취소 처리
//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//        }
    }
}
