package com.ipme;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            prop.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
        }catch (Exception e){

        }
        String maV = StringUtils.upperCase(prop.getProperty("devops.config"));
        System.out.println("Hello I'm " + maV);
    }
}