package study.online.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import study.online.Activity.msg_item;


public class Chat_SQLite_Tools {
    public void Add(SQLiteDatabase db, msg_item msg){
        ContentValues values = new ContentValues();
        values.put("id",msg.getId());
        values.put("content",msg.getText());
        values.put("time",msg.getTime());
        values.put("type",msg.getType());
        db.insert("Chat_history",null,values);
        values.clear();
    }
}
