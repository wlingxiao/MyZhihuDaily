package me.xiaoye.daily.app.model;


import java.util.List;

public class ThemesModel {
    private int limit;


    private List<Others> others ;

    public void setLimit(int limit){
        this.limit = limit;
    }
    public int getLimit(){
        return this.limit;
    }
    public void setOthers(List<Others> others){
        this.others = others;
    }
    public List<Others> getOthers(){
        return this.others;
    }

    public static class Others {
        private int color;

        private String thumbnail;

        private String description;

        private int id;

        private String name;

        public void setColor(int color){
            this.color = color;
        }
        public int getColor(){
            return this.color;
        }
        public void setThumbnail(String thumbnail){
            this.thumbnail = thumbnail;
        }
        public String getThumbnail(){
            return this.thumbnail;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }

    }
}
