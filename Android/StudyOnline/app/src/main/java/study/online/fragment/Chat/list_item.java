package study.online.fragment.Chat;

public class list_item {
    private String id;
    private String img;
    private String name;
    private String time;
    private String content;

    public list_item(String id,String img,String name,String time,String content){
        this.id = id;
        this.img = img;
        this.content = content;
        this.time = time;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
