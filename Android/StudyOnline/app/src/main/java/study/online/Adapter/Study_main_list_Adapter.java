package study.online.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import study.online.Activity.Study_Main_item;
import study.online.Activity.Study_Video;
import study.online.R;

public class Study_main_list_Adapter extends RecyclerView.Adapter<Study_main_list_Adapter.ViewHolder> {
    private List<Study_Main_item> list;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.smitem);
            text = (TextView) view.findViewById(R.id.study_main_item);
        }
    }

    public Study_main_list_Adapter(List<Study_Main_item> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.study_main_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Study_Main_item item = list.get(i);
        viewHolder.text.setText(item.getName());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Study_Video.class);
                intent.putExtra("url",item.getUrl());
                Log.d("Adapter", item.getUrl());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return list.size();
    }
}
