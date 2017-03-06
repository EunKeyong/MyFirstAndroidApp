package com.example.byg.exam_0120.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.MemoRecyclerAdapter;
import com.example.byg.exam_0120.db.MemoContract;
import com.example.byg.exam_0120.db.MemoFacade;
import com.example.byg.exam_0120.models.Memo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MemoActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_NEW_MEMO = 1000;
    public static final int REQUEST_CODE_UPDATE_MEMO = 1001;

    //private MemoDbHelper mDbHelper;
    //private ListView mListView;
    private RecyclerView mRecyclerView;
    private MemoRecyclerAdapter mRecyclerAdapter;
    //private MemoAdapter mAdapter;

    private List<Memo> mMemoList;

    private MemoFacade mMemoFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 완료 눌렀을때 처리
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 글자 변경될때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                // 새로운 쿼리의 결과 뿌리기
                // 내가 입력하는 글자 포함되어있으면 뿌리기
                List<Memo> newMemoList = mMemoFacade.getMemoList(
                        MemoContract.MemoEntry.COLUMN_NAME_TITLE + " LIKE '%" + newText + "%'",
                        null,
                        null,
                        null,
                        null
                );
                mRecyclerAdapter.swap(newMemoList);
                //  mAdapter.swap(newMemoList);

                return true;
            }
        });

        // DB 헬퍼
        // mDbHelper = new MemoDbHelper(this);

        // 메모 퍼사드
        mMemoFacade = new MemoFacade(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.memo_list);

        // 애니메이션 커스터마이징
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(1000);
        mRecyclerView.setItemAnimator(animator);

        // mListView = (ListView) findViewById(R.id.list_view);

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
        // 데이터
        mMemoList = mMemoFacade.getMemoList();
        mRecyclerAdapter = new MemoRecyclerAdapter(mMemoList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //mAdapter = new MemoAdapter(this, mMemoList);
        //mListView.setAdapter(mAdapter);

        //이벤트
        // mListView.setOnItemClickListener(this);
        //registerForContextMenu(mListView);

        // ContextMenu
        registerForContextMenu(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String message = data.getStringExtra("message1");

//            long id = data.getLongExtra("id", -1);

            // 새 메모
            if (requestCode == REQUEST_CODE_NEW_MEMO) {
                //mMemoList.add(new Memo(title, content));
                // db에 정보 삽입
                long newRowId = mMemoFacade.insert(title, content);
                if (newRowId == -1) {
                    // 에러
                    Toast.makeText(this, "저장이 실패하였습니다", Toast.LENGTH_SHORT).show();
                } else {
                    // 성공
                    // 리스트 갱신
                    mMemoList = mMemoFacade.getMemoList();
                    //mAdapter.notifyDataSetChanged();
                }
                mRecyclerAdapter.insert(mMemoList);
                // mRecyclerAdapter.swap(mMemoList);
                // 메모 수정
            } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
//                Memo memo = mMemoList.get((int) id);
//                memo.setTitle(title);
//                memo.setContent(content);

                long id = data.getLongExtra("id", -1);
                int position = data.getIntExtra("position", -1);

                // 수정
                if (mMemoFacade.update(id, title, content) > 0) {
                    mMemoList = mMemoFacade.getMemoList();
                }
                mRecyclerAdapter.update(mMemoList, position);
            }
            //mAdapter.notifyDataSetChanged();


            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 클릭하면 메모내용이 보이게
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        Memo memo = mMemoList.get(position);
//        Intent intent = new Intent(this, MemoWriteActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("memo", memo);
//        startActivityForResult(intent, REQUEST_CODE_UPDATE_MEMO);
//    }


    // 보낸이 : MemoRecyclerAdapter
    @Subscribe
    public void onItemClick(MemoRecyclerAdapter.ItemClickEvent event) {
        Memo memo = mMemoList.get(event.position);
        Intent intent = new Intent(this, MemoWriteActivity.class);
        intent.putExtra("id", event.id);
        intent.putExtra("memo", memo);
        intent.putExtra("position", event.position);

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
                String id = idEditText.getText().toString();
                String pass = passWordEditText.getText().toString();
                Toast.makeText(MemoActivity.this, id + " " + pass, Toast.LENGTH_SHORT).show();
            }
        });
        // 부정 버튼
        builder.setNegativeButton("취소", null);
        builder.setView(view);
        builder.show();

    }

    private void deleteMemo(long id) {
        //mMemoList.remove((int) id);
        int deleted = mMemoFacade.delete(id);
        if (deleted != 0) {
            mMemoList = mMemoFacade.getMemoList();
            //mAdapter.notifyDataSetChanged();
            mRecyclerAdapter.swap(mMemoList);
        }
    }
}
