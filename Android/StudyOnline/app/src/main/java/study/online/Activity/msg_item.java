package study.online.Activity;

import android.graphics.Bitmap;

public class msg_item {
    private String id;
    private String text;
    private String time;
    private Bitmap img;
    private int type;

    public msg_item(String id,String text,String time,int type){
        this.id = id;
        this.text=text;
        this.time=time;
        this.type=type;
    }
    public msg_item(String id,Bitmap img,String time,int type){
        this.id = id;
        this.img=img;
        this.time=time;
        this.type=type;
    }

    public int getType(){
        return type;
    } // 0=GetText 1=SendText 2=GetImg 3=SendImg
    public String getText(){
        return text;
    }
    public String getTime(){
        return time;
    }
    public Bitmap getImg(){
        return img;
    }
    public String getId() {
        return id;
    }
}
