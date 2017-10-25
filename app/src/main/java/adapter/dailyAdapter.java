package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.www.yuekaoa.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import jsonbean.Newbase;

/**
 * Created by Dabin on 2017/10/25.
 */

public class dailyAdapter extends XRecyclerView.Adapter{
    private Context context;
    private List<Newbase.SongListBean> song_list;

    public dailyAdapter(Context context, List<Newbase.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dailyAdapter.MyViewHoldre holder = new dailyAdapter.MyViewHoldre(LayoutInflater.from(context).inflate(R.layout.recycler_itema, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoldre myViewHoldre = (MyViewHoldre) holder;
        myViewHoldre.name.setText(song_list.get(position).getAuthor().toString());
        myViewHoldre.song.setText(song_list.get(position).getTitle().toString());
        ImageLoader.getInstance().displayImage(song_list.get(position).getPic_small(),myViewHoldre.imageView);
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
}
