package com.zhangli.test.material_design_application.Gson;

import com.google.gson.annotations.SerializedName;

public class Now{
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}