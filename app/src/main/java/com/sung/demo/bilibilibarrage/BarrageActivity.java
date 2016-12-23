package com.sung.demo.bilibilibarrage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sung.demo.bilibilibarrage.danmu.DanmuControl;
import com.sung.demo.bilibilibarrage.model.Danmu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import master.flame.danmaku.controller.IDanmakuView;

public class BarrageActivity extends AppCompatActivity implements View.OnClickListener{
    private IDanmakuView mDanmakuView;
    private DanmuControl mDanmuControl;

    private Button btnAddDanmu,btnAddTextDanmu;
    private EditText editAddTextDanmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barrage_main);
        getSupportActionBar().hide();

        mDanmuControl = new DanmuControl(this);
        initView();
    }

    private void initView() {
        mDanmakuView = (IDanmakuView) findViewById(R.id.danmakuView);
        btnAddDanmu = (Button) findViewById(R.id.btnAddDanmu);
        btnAddTextDanmu = (Button) findViewById(R.id.btnAddTextDanmu);
        editAddTextDanmu = (EditText) findViewById(R.id.editAddTextDanmu);
        mDanmuControl.setDanmakuView(mDanmakuView);
        btnAddDanmu.setOnClickListener(this);
        btnAddTextDanmu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddDanmu:
                setData();
                break;
            case R.id.btnAddTextDanmu:
                if (editAddTextDanmu.getText()==null||
                        editAddTextDanmu.getText().toString().length()==0)
                    return;

                mDanmuControl.addCommentDanmu(
                        editAddTextDanmu.getText().toString().trim());
                editAddTextDanmu.setText("");
                break;
        }

    }

    private void setData() {
        List<Danmu> danmus = new ArrayList<>();
        Danmu danmu1 = new Danmu(0, 1, "Comment", R.mipmap.ic_launcher, "这是一条弹幕啦啦啦");
        Danmu danmu2 = new Danmu(0, 2, "Like", R.mipmap.ic_launcher, "");
        danmus.add(danmu1);
        danmus.add(danmu2);
        Collections.shuffle(danmus);
        mDanmuControl.addDanmuList(danmus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDanmuControl.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDanmuControl.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDanmuControl.destroy();
    }
}
