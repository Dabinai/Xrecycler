package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.www.yuekaoa.R;
import com.bwie.www.yuekaoa.TiaoActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import app.GlideImageLoader;
import jsonbean.Newbase;

/**
 * Created by Dabin on 2017/10/25.
 * 多条目加载
 */

public class contentAdapter extends XRecyclerView.Adapter{

    private int TY_ONE = 0;
    private int TY_TWO = 1;

    private Context context;
    private List<Newbase.SongListBean> song_list;
    private List mylist = new ArrayList();
    public contentAdapter(Context context, List<Newbase.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(getItemViewType(viewType) == TY_ONE){
            MyBannerViewHolder myBannerViewHolder = new MyBannerViewHolder(LayoutInflater.from(context).inflate(R.layout.banner_itema, parent, false));
            return myBannerViewHolder;
        }else{
            MyViewHoldre holder = new MyViewHoldre(LayoutInflater.from(context).inflate(R.layout.recycler_itema, parent,
                    false));
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == TY_ONE){  //轮播图
            MyBannerViewHolder myBannerViewHolder = (MyBannerViewHolder) holder;
            for (int i = 0; i < song_list.size(); i++) {
                String pic_big = song_list.get(i).getPic_big();
                mylist.add(pic_big);
            }
            myBannerViewHolder.banner.setImageLoader(new GlideImageLoader());
            myBannerViewHolder.banner.setImages(mylist);
            myBannerViewHolder.banner.start();
            myBannerViewHolder.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    context.startActivity(new Intent(context, TiaoActivity.class));
                }
            });

        }else{
            MyViewHoldre myViewHoldre = (MyViewHoldre) holder;
            myViewHoldre.name.setText(song_list.get(position).getAuthor().toString());
            myViewHoldre.song.setText(song_list.get(position).getTitle().toString());
            ImageLoader.getInstance().displayImage(song_list.get(position).getPic_small(),myViewHoldre.imageView);
            //点击跳转
            myViewHoldre.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, TiaoActivity.class));
                }
            });
            //点击跳转
            myViewHoldre.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, TiaoActivity.class));
                }
            });
            //点击跳转
            myViewHoldre.song.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, TiaoActivity.class));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return song_list.size();
    }

    class MyViewHoldre extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView song;
        public MyViewHoldre(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_itema);
            name = itemView.findViewById(R.id.name_itema);
            song = itemView.findViewById(R.id.song_itema);
        }
    }

    class MyBannerViewHolder extends RecyclerView.ViewHolder{
        Banner banner;

        public MyBannerViewHolder(View itemView) {
            super(itemView);
            banner =itemView.findViewById(R.id.banner_itema);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TY_ONE;
        }else{
            return TY_TWO;
        }

    }
}
