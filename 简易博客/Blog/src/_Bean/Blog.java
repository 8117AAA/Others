package _Bean;

import java.util.Date;

public class Blog {
    private String author ;
    private String title ;
    private String string ;
    private String blogtime;
    
    public void setAuthor(String author){
        this.author = author ;
    }
    public String getAuthor(){
        return author ;
    }

    public void setTitle(String title){
        this.title = title ;
    }
    public String getTitle(){
        return title ;
    }

    public void setString(String string){
        this.string = string ;
    }
    public String getString(){
        return string ;
    }

    public String getBlogtime(){
        return blogtime ;
    }
    public void setBlogTime(String blogtime) {
    	this.blogtime = blogtime ;
    }

}
