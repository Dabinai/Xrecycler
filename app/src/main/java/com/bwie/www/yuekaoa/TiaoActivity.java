package com.bwie.www.yuekaoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import adapter.contentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import jsonbean.Newbase;
import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

public class TiaoActivity extends AppCompatActivity {
    private String path1 = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=";
    private int page = 0;

    @Bind(R.id.recycler_tiao)
    XRecyclerView recyclerTiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao);
        ButterKnife.bind(this);
        recyclerTiao.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        getData(path1+page);
    }

    //网络请求
    public void getData(String urls) {
        OkHttp3Utils.doGet(urls, new GsonObjectCallback<Newbase>() {
            @Override
            public void onUi(Newbase newbase) {
                List<Newbase.SongListBean> song_list = newbase.getSong_list();
                recyclerTiao.setAdapter(new contentAdapter(TiaoActivity.this, song_list));
            }

            @Override
            public void onFailed(Call call, IOException e) {
            }
        });
    }

}
