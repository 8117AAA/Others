package study.online.Service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import study.online.Activity.msg_item;
import study.online.R;
import study.online.sqlite.Chat_SQLite;
import study.online.sqlite.Chat_SQLite_Tools;

public class Chat_Socket_Service extends Service {
    private Socket s;
    private BufferedWriter bufferedWriter;
    private Intent status;
    private SQLiteDatabase db;
    private Chat_SQLite_Tools dbt;

    public Chat_Socket_Service() {
    }

    private ChatBinder binder = new ChatBinder();
    public class ChatBinder extends Binder {
        private String t;
        public void SendMsg(String text){
            t = text;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        bufferedWriter.write(t);
                        bufferedWriter.flush();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final String userid = intent.getStringExtra("id").toString();
        status = new Intent("server.connect.status");
        db = new Chat_SQLite(this,"Chat_history.db",null,1).getWritableDatabase();
        dbt = new Chat_SQLite_Tools();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    s = new Socket(getResources().getString(R.string.server_ip),getResources().getInteger(R.integer.server_port));
                    status.putExtra("status","OK");
                    sendBroadcast(status);
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bufferedWriter.write("id "+userid); // change id
                    bufferedWriter.flush();
                    while (true){
                        byte[] bytes = new byte[1024];
                        int n=0;
                        n = s.getInputStream().read(bytes);
                        String str = new String (bytes,0,n);
                        Log.d("Service", "get new msg:"+str);
                        String getmsg = str.substring(str.indexOf(" ")+1);
                        msg_item m = new msg_item(userid,getmsg,"00:00AM",0);
                        dbt.Add(db,m);
                        Intent intent1 = new Intent("chat.getnewmsg");
                        intent1.putExtra("msg",str);
                        sendBroadcast(intent1);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                    status.putExtra("status","Error");
                    sendBroadcast(status);
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
