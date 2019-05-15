package com.zhangli.test.material_design_application.Utils;

import java.math.BigDecimal;

/**
 * Created by LX5 on 2019/5/11.
 */

public class FormatUtils {
    public static String formatSize(long size){
        LogUtils.d("size:" + size);
        double kilo = size/ 1024;
        LogUtils.d(" kilo size:" + kilo);
        if(kilo < 1){
            return size + "B";
        }
        double megaByte = kilo / 1024 ;
        LogUtils.d(" megaByte size:" + megaByte);
        if(megaByte < 1){
            BigDecimal bgByte1 = new BigDecimal(Double.toString(kilo));
            return  bgByte1.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaM = megaByte / 1024 ;
        LogUtils.d(" gigaM size:" + gigaM);
        if(megaByte < 1){
            BigDecimal bgByte2 = new BigDecimal(Double.toString(megaByte));
            return  bgByte2.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double tear = gigaM / 1024 ;
        LogUtils.d(" tear size:" + tear);
        BigDecimal bgByte3 = new BigDecimal(Double.toString(gigaM));
        return bgByte3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
    }
}
