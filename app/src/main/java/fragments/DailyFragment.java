package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.www.yuekaoa.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import adapter.dailyAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import jsonbean.Newbase;
import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * Created by Dabin on 2017/10/25.
 */

public class DailyFragment extends Fragment {
    @Bind(R.id.recycler_daily)
    XRecyclerView recyclerDaily;
    private String path1 = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=";
    private int page = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_item, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData(path1+page);
    }

    //网络请求
    public void getData(String urls) {
        OkHttp3Utils.doGet(urls, new GsonObjectCallback<Newbase>() {
            @Override
            public void onUi(Newbase newbase) {
                List<Newbase.SongListBean> song_list = newbase.getSong_list();
                recyclerDaily.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                recyclerDaily.setAdapter(new dailyAdapter(getActivity(), song_list));
            }

            @Override
            public void onFailed(Call call, IOException e) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
