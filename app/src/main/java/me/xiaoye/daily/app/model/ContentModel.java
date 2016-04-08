package me.xiaoye.daily.app.model;

/**
 * Created by VAIO on 2016/4/8.
 */
public class ContentModel {
    private String body;

    private String image_source;

    private String title;

    private String image;

    private String share_url;


    private String ga_prefix;


    private int type;

    private int id;

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
    public void setImage_source(String image_source){
        this.image_source = image_source;
    }
    public String getImage_source(){
        return this.image_source;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setShare_url(String share_url){
        this.share_url = share_url;
    }
    public String getShare_url(){
        return this.share_url;
    }
    public void setGa_prefix(String ga_prefix){
        this.ga_prefix = ga_prefix;
    }
    public String getGa_prefix(){
        return this.ga_prefix;
    }

    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
