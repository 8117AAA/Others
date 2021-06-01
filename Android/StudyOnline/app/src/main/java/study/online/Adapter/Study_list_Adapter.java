package study.online.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import study.online.Activity.Study_Main;
import study.online.fragment.Study.Study_video_item;
import study.online.R;

public class Study_list_Adapter extends RecyclerView.Adapter<Study_list_Adapter.ViewHolder> {
    private List<Study_video_item> video_list;
    private int id;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView videoimage;
        TextView videotext;

        public ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.sitem);
            videoimage = (ImageView) view.findViewById(R.id.study_item_video);
            videotext = (TextView) view.findViewById(R.id.study_item_text);
        }
    }

    public Study_list_Adapter(List<Study_video_item> video_list, int id, Context context){
        this.video_list = video_list;
        this.id = id;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(id,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Study_video_item item = video_list.get(i);
        Glide.with(context).load(item.getImg()).into(viewHolder.videoimage);
        viewHolder.videotext.setText(item.getText());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Study_Main.class);
                intent.putExtra("url",item.getUrl());
                intent.putExtra("name",item.getText());
                Log.d("Adapter", item.getUrl());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return video_list.size();
    }
}
