package wap.example.findintermediateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import wap.example.findintermediateapp.R;

import java.util.ArrayList;

public class MyMemo extends ChangeStateBar {

    RecyclerView recyclerView;
    MyMemoAdapter adapter;
    GridLayoutManager layoutManager;
    LinearLayout ll_noMemo;

    ArrayList<Item> list = new ArrayList<Item>();

    Uri[] uri ;
    String[] mName;
    String[] mAdress;
    String[] mPhoto;
    String[] mMemoTime;
    String[] mMemox;
    String[] mMemoy;
    String[] mContent;
    int mCount;
    String[] mMemoId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_memo);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_arrow);
        ll_noMemo = findViewById(R.id.myMemo_no_memo);


        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
       mCount = getIntent().getIntExtra("memoCount", 0);

       if(mCount == 0)
       {
         ll_noMemo.setVisibility(View.VISIBLE);
       }

       mName = new String[mCount];
       mAdress = new String[mCount];
       mPhoto = new String[mCount];
       mMemoTime = new String[mCount];
       mMemox = new String[mCount];
       mMemoy = new String[mCount];
       mContent = new String[mCount];
       mMemoId = new String[mCount];


        mName = getIntent().getStringArrayExtra("memoName");
        mAdress = getIntent().getStringArrayExtra("memoAdress");
        mPhoto = getIntent().getStringArrayExtra("memoPhoto");
        mContent = getIntent().getStringArrayExtra("memoContent");
        mMemoTime = getIntent().getStringArrayExtra("memoMemotime");
        mMemox = getIntent().getStringArrayExtra("memoX");
        mMemoy = getIntent().getStringArrayExtra("memoY");
        mMemoId = getIntent().getStringArrayExtra("memoId");

        String[] first_img=new String[mCount];
        String[] all_img = new String[mCount];



        for(int i =0; mPhoto.length>i; i++){
            String[]mString = mPhoto[i].split("\\|");
            first_img[i]=mString[0];
        }

        for(int i = 0; mPhoto.length>i; i++){
            Item item = new Item(first_img[i], mPhoto[i], mName[i], mAdress[i], mContent[i], mMemoTime[i], mMemoId[i], mMemox[i], mMemoy[i]);
            list.add(item);
        }

        recyclerView = (RecyclerView)findViewById(R.id.myMemo_recyclerView);
        adapter = new MyMemoAdapter(getApplicationContext(), list);

       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home: {
                Intent mainIntent = new Intent(MyMemo.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
