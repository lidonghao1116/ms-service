package com.jiacer.modules;

import com.jiacer.modules.common.utils.EntryptUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by gaoyan on 28/06/2017.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(EntryptUtils.entryptUserPassword("141827".toUpperCase(), "15212074229"));
        System.out.println(RandomStringUtils.random(32,true,true));
        System.out.println(String.format("adsf:%s, dd: %s ",1,2));
    }
}
