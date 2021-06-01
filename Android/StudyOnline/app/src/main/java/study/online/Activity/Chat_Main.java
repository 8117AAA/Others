package study.online.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import study.online.Adapter.Chat_msg_Adapter;
import study.online.R;
import study.online.Service.Chat_Socket_Service;
import study.online.sqlite.Chat_SQLite;
import study.online.sqlite.Chat_SQLite_Tools;

public class Chat_Main extends AppCompatActivity implements View.OnClickListener {
    private MsgReceiver msgReceiver;
    private IntentFilter intentFilter;
    private EditText editText;
    private String edit;
    private Button picture;
    private Button camera;
    private Button microphone;
    private Button plane;
    private Button chevron_left;
    private Button user;
    private Intent GetIntent;
    private String userid;
    private String msgtype;
    private RecyclerView recyclerView;
    private Chat_msg_Adapter adapter;
    private List<msg_item> list = new ArrayList<>();
    private Uri imguri;
    private Chat_Socket_Service.ChatBinder chatBinder;
    private SQLiteDatabase db;
    private Chat_SQLite_Tools dbt;
    private Intent serviceintent;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            chatBinder = (Chat_Socket_Service.ChatBinder) service;
            chatBinder.SendMsg(msgtype+userid+" "+edit);
            unbindService(connection);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void init(){
        serviceintent = new Intent(this,Chat_Socket_Service.class);
        db = new Chat_SQLite(this,"Chat_history.db",null,1).getWritableDatabase();
        dbt = new Chat_SQLite_Tools();

        editText = findViewById(R.id.chat_edit);
        plane = findViewById(R.id.chat_plane);
        chevron_left = findViewById(R.id.chat_chevron_left);
        user = findViewById(R.id.chat_user);
        microphone = findViewById(R.id.chat_microphone);
        picture = findViewById(R.id.chat_image);
        camera = findViewById(R.id.chat_camera);
        TextView username = findViewById(R.id.chat_username);
        GetIntent = this.getIntent();
        username.setText(GetIntent.getStringExtra("username").toString());
        userid = username.getText().toString();

        Typeface font = Typeface.createFromAsset(getAssets(), "fa-solid-900.ttf");
        chevron_left.setTypeface(font);
        user.setTypeface(font);
        microphone.setTypeface(font);
        picture.setTypeface(font);
        camera.setTypeface(font);
        plane.setTypeface(font);

        picture.setOnClickListener(this);
        camera.setOnClickListener(this);
        microphone.setOnClickListener(this);
        plane.setOnClickListener(this);
        chevron_left.setOnClickListener(this);
        user.setOnClickListener(this);

        recyclerView = findViewById(R.id.chat_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Chat_msg_Adapter(list);
        recyclerView.setAdapter(adapter);

        Cursor cursor = db.rawQuery("select * from Chat_history where id = ?",new String[]{userid});
        if (cursor.moveToFirst()){
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time  = cursor.getString(cursor.getColumnIndex("time"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                msg_item m = new msg_item(id,content,time,type);
                list.add(m);
            }while (cursor.moveToNext());
            adapter.notifyItemInserted(list.size()-1);
            recyclerView.scrollToPosition(list.size()-1);
        }

        intentFilter = new IntentFilter();
        msgReceiver = new MsgReceiver();
        intentFilter.addAction("chat.getnewmsg");
        registerReceiver(msgReceiver,intentFilter);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_image:
                if (ContextCompat.checkSelfPermission(Chat_Main.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Chat_Main.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                }else{
                    openAlbum();
                }
                break;
            case R.id.chat_camera:
                File TempPic = new File(getExternalCacheDir(),"Temp.jpg");
                try {
                    if (TempPic.exists()){
                        TempPic.delete();
                    }
                    TempPic.createNewFile();
                } catch (IOException e){
                    e.printStackTrace();
                }
                imguri = FileProvider.getUriForFile(Chat_Main.this,"Study.Online.Chat.fileprovider",TempPic);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imguri);
                startActivityForResult(intent, 2);
                break;
            case R.id.chat_microphone:
                break;
            case R.id.chat_plane:
                edit = editText.getText().toString();
                if (!"".equals(edit)){
                    msgtype="t";
                    bindService(serviceintent,connection,BIND_AUTO_CREATE);
                    msg_item m = new msg_item(userid,edit,"00:00AM",1);
                    list.add(m);
                    dbt.Add(db,m);
                    adapter.notifyItemInserted(list.size()-1);
                    recyclerView.scrollToPosition(list.size()-1);
                    editText.setText("");
                }
                break;
            case R.id.chat_chevron_left:
                finish();
                break;
            case R.id.chat_user:
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openAlbum();
        } else {
            Toast.makeText(this,"permission error!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String imagePath = null;
                    Uri uri = data.getData();
                    if (DocumentsContract.isDocumentUri(this, uri)) {
                        String docId = DocumentsContract.getDocumentId(uri);
                        if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                            String id = docId.split(":")[1];
                            String selection = MediaStore.Images.Media._ID + "=" + id;
                            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                        } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                            imagePath = getImagePath(contentUri, null);
                        }
                    } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                        imagePath = getImagePath(uri, null);
                    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                        imagePath = uri.getPath();
                    }
                    if (imagePath != null) {
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        msg_item m = new msg_item(userid,bitmap,"00:00AM",3);
                        list.add(m);
                        adapter.notifyItemInserted(list.size()-1);
                        recyclerView.scrollToPosition(list.size()-1);
                    } else {
                        Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imguri));
                        msg_item m = new msg_item("22",bitmap,"00:00AM",3);
                        list.add(m);
                        adapter.notifyItemInserted(list.size()-1);
                        recyclerView.scrollToPosition(list.size()-1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.
                        Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    class MsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String getmsg = intent.getStringExtra("msg");
            getmsg = getmsg.substring(getmsg.indexOf(" ")+1);
            msg_item m = new msg_item(userid,getmsg,"00:00AM",0);
            list.add(m);
            adapter.notifyItemInserted(list.size()-1);
            recyclerView.scrollToPosition(list.size()-1);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(msgReceiver);
        super.onDestroy();
    }
}
