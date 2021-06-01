package study.online.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import study.online.Activity.Chat_Main;
import study.online.R;
import study.online.fragment.Chat.list_item;

public class Chat_list_Adapter extends RecyclerView.Adapter<Chat_list_Adapter.ViewHolder> {
    private List<list_item> list;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout list_item;
        ImageView img;
        TextView name;
        TextView time;
        TextView content;
        public ViewHolder(View view){
            super(view);
            list_item = view.findViewById(R.id.chat_list_item);
            name = view.findViewById(R.id.chat_list_name);
            time = view.findViewById(R.id.chat_list_time);
            img = view.findViewById(R.id.chat_list_img);
            content= view.findViewById(R.id.chat_list_msg);
        }
    }
    public Chat_list_Adapter(List<list_item> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_list_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final list_item item = list.get(i);
        viewHolder.name.setText(item.getName());
        viewHolder.content.setText(item.getContent());
        viewHolder.img.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.time.setText(item.getTime());
        viewHolder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat_Main.class);
                intent.putExtra("username",item.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
