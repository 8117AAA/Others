package study.online.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import study.online.Activity.msg_item;
import study.online.R;

public class Chat_msg_Adapter extends RecyclerView.Adapter<Chat_msg_Adapter.ViewHolder> {
    private List<msg_item> list;
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout GetLayout;

        LinearLayout SendLayout;

        TextView gtext;
        TextView gtime;
        TextView stext;
        TextView stime;
        ImageView gimg;
        ImageView simg;

        public ViewHolder(View view) {
            super(view);
            GetLayout = (LinearLayout) view.findViewById(R.id.GMSG);
            SendLayout = (LinearLayout) view.findViewById(R.id.SMSG);
            gtext = (TextView) view.findViewById(R.id.gtext);
            gtime = (TextView) view.findViewById(R.id.gtime);
            stext = (TextView) view.findViewById(R.id.stext);
            stime = (TextView) view.findViewById(R.id.stime);
            gimg = view.findViewById(R.id.gimg);
            simg = view.findViewById(R.id.simg);
        }
    }
    public Chat_msg_Adapter(List<msg_item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.chat_msg_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        msg_item item = list.get(i);
        if (item.getType() == 0) {
            viewHolder.GetLayout.setVisibility(View.VISIBLE);
            viewHolder.SendLayout.setVisibility(View.GONE);
            viewHolder.gtext.setText(item.getText());
            viewHolder.gtime.setText(item.getTime());
        } else if(item.getType() == 1) {
            viewHolder.SendLayout.setVisibility(View.VISIBLE);
            viewHolder.GetLayout.setVisibility(View.GONE);
            viewHolder.stext.setText(item.getText());
            viewHolder.stime.setText(item.getTime());
        } else if(item.getType() == 2) {
            viewHolder.GetLayout.setVisibility(View.VISIBLE);
            viewHolder.SendLayout.setVisibility(View.GONE);
            viewHolder.gtext.setVisibility(View.GONE);
            viewHolder.gimg.setVisibility(View.VISIBLE);
            viewHolder.gimg.setImageBitmap(item.getImg());
            viewHolder.gtime.setText(item.getTime());
        } else if(item.getType() == 3) {
            viewHolder.SendLayout.setVisibility(View.VISIBLE);
            viewHolder.GetLayout.setVisibility(View.GONE);
            viewHolder.stext.setVisibility(View.GONE);
            viewHolder.simg.setVisibility(View.VISIBLE);
            viewHolder.simg.setImageBitmap(item.getImg());
            viewHolder.stime.setText(item.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
