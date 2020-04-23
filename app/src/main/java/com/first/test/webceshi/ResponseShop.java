package com.first.test.webceshi;

import java.util.LinkedList;
import java.util.List;


public class ResponseShop {
    public boolean succ() {
        return succ;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<ValueShop> getValue() {
        return value;
    }
    public ResponseShop(){

    }

    boolean succ;
    String code,message;
    List<ValueShop> value =new LinkedList<>();

    public class ValueShop{
        public String getShopId() {
            return shopId;
        }

        public String getName() {
            return name;
        }

        private String shopId,name;
    }
}
