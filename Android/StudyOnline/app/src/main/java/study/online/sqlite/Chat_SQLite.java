package study.online.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Chat_SQLite extends SQLiteOpenHelper {
    public static final String CREATE_DB = "create table Chat_history ("
            + "id text, "
            + "content text, "
            + "time text ,"
            + "type integer)";
    private Context context;
    public Chat_SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
