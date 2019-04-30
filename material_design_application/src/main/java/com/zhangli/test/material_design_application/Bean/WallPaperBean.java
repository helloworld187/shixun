package com.zhangli.test.material_design_application.Bean;

public class WallPaperBean {
    private String image_url;


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "WallPaperBean{" +
                "image_url='" + image_url + '\'' +
                '}';
    }
}
