package com.example.byg.exam_0120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.byg.exam_0120.models.Memo;

public class MemoWriteActivity extends AppCompatActivity {

    private EditText mtitleEditText;
    private EditText mcontentEditText;
    private long mId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 메모 추가
        setContentView(R.layout.activity_memo_write);
        mtitleEditText = (EditText) findViewById(R.id.title_edit_text);
        mcontentEditText = (EditText) findViewById(R.id.content_edit_text);

        if (getIntent() != null) {
            // 보여주기
            if (getIntent().hasExtra("memo")) {
                mId = getIntent().getLongExtra("id", -1);
                Memo memo = (Memo) getIntent().getSerializableExtra("memo");
                mtitleEditText.setText(memo.getTitle());
                mcontentEditText.setText(memo.getContent());
                //새메모
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_memo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel_settings:
                cancel();
                return true;
            case R.id.save_settings:
                // 새메모
                save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void save() {
        Intent intent = new Intent();
        intent.putExtra("id", mId);
        intent.putExtra("message1", "저장");
        intent.putExtra("title", mtitleEditText.getText().toString());
        intent.putExtra("content", mcontentEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
