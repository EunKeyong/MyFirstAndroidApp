package com.example.byg.exam_0120.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.MemoAdapter;
import com.example.byg.exam_0120.models.Memo;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final int REQUEST_CODE_NEW_MEMO = 1000;
    public static final int REQUEST_CODE_UPDATE_MEMO = 1001;

    private ListView mListView;
    private MemoAdapter mAdapter;
    private List<Memo> mMemoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mListView = (ListView) findViewById(R.id.list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoActivity.this, MemoWriteActivity.class);

                startActivityForResult(intent, REQUEST_CODE_NEW_MEMO);
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        mMemoList = new ArrayList<>();
        mAdapter = new MemoAdapter(this, mMemoList);

        mListView.setAdapter(mAdapter);
        //이벤트
        mListView.setOnItemClickListener(this);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String message = data.getStringExtra("message1");

            long id = data.getLongExtra("id", -1);

            // 새 메모
            if (requestCode == REQUEST_CODE_NEW_MEMO) {
                mMemoList.add(new Memo(title, content));

                // 메모 수정
            } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
                Memo memo = mMemoList.get((int) id);
                memo.setTitle(title);
                memo.setContent(content);
            }
            mAdapter.notifyDataSetChanged();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 클릭하면 메모내용이 보이게
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Memo memo = mMemoList.get(position);
        Intent intent = new Intent(this, MemoWriteActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("memo", memo);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_MEMO);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_memo, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:
                // 삭제 누르면 확인 받기
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("확인");
                builder.setMessage("정말 삭제 하시겠습니까?");
                builder.setIcon(R.mipmap.ic_launcher);

                // 확인 버튼
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteMemo(info.id);
                    }
                });
                // 부정 버튼
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            case R.id.action_custom:
                showCustomDialog();
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showCustomDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null, false);
        final EditText idEditText = (EditText) view.findViewById(R.id.id_edit);
        final EditText passWordEditText = (EditText) view.findViewById(R.id.password_edit);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("확인");
        builder.setMessage("정말 삭제 하시겠습니까?");
        builder.setIcon(R.mipmap.ic_launcher);

        // 확인 버튼
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        // 부정 버튼
        builder.setNegativeButton("취소", null);
        builder.setView(view);
        builder.show();

    }

    private void deleteMemo(long id) {
        mMemoList.remove((int) id);
        mAdapter.notifyDataSetChanged();
    }
}
