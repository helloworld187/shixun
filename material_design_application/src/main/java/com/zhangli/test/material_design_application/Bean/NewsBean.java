package com.zhangli.test.material_design_application.Bean;

public class NewsBean {
    public String title;
    public String time;
    public String src;
    public String category;
    public String pic;
    public String weburl;

    public NewsBean() {
    }

    public NewsBean(String title, String time, String src, String category, String pic, String weburl) {
        this.title = title;
        this.time = time;
        this.src = src;
        this.category = category;
        this.pic = pic;
        this.weburl = weburl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPic(String pic_url) {
        this.pic = pic_url;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getSrc() {
        return src;
    }

    public String getCategory() {
        return category;
    }

    public String getPic() {
        return pic;
    }

    public String getWeburl() {
        return weburl;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", src='" + src + '\'' +
                ", category='" + category + '\'' +
                ", pic='" + pic + '\'' +
                ", weburl='" + weburl + '\'' +
                '}';
    }
}
