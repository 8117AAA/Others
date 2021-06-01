package study.online.fragment.Study;


public class Study_video_item {
    private String img;
    private String text;
    private String url;
    public Study_video_item(String img, String text, String url){
        this.img = img;
        this.text = text;
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }
}
