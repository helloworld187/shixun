package com.zhangli.test.material_design_application.Gson;

import com.google.gson.annotations.SerializedName;

public class Basic{
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
