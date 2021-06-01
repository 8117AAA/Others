package study.online.fragment.Chat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import study.online.Activity.Chat_Main;
import study.online.Adapter.Chat_list_Adapter;
import study.online.R;
import study.online.sqlite.Chat_SQLite;

public class Chat extends Fragment {
    private List<list_item> list = new ArrayList<>();
    private Cursor cursor;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    private Chat_list_Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_fragment,container,false);
        Button add = view.findViewById(R.id.add);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fa-solid-900.ttf");
        add.setTypeface(font);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        init();
        initRecyclerView(view);
        return view;
    }

    private void init(){
        list = new ArrayList<>();
        db = new Chat_SQLite(getActivity(),"Chat_history.db",null,1).getWritableDatabase();
        //
//        list_item item;
//        for(int i=0;i<10;i++){
//            String temp = String.valueOf(i);
//            item = new list_item(temp,"","name",temp+":00","this is "+i+" msg");
//            list.add(item);
//        }
        cursor=db.rawQuery("select id,content,time from Chat_history group by id order by time",null);
        if (cursor.moveToFirst()){
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                list.add(new list_item(id,"",id,"",content));
                Log.d("Chat_list", id+content+time);
            }while (cursor.moveToNext());
        }
    }
    private void initRecyclerView(View view){
        recyclerView = view.findViewById(R.id.chat_fragment_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Chat_list_Adapter(list,getActivity());
        recyclerView.setAdapter(adapter);
    }
    private void showInputDialog(){
        final EditText editText = new EditText(getContext());
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(getContext());
        inputDialog.setTitle("Please input id!").setView(editText);
        inputDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), Chat_Main.class);
                intent.putExtra("username",editText.getText().toString());
                startActivity(intent);
            }
        }).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
