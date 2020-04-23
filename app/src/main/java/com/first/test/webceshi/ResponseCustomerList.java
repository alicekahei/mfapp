package com.first.test.webceshi;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class ResponseCustomerList {
    public boolean succ() {
        return succ;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ValueCus getValue() {
        return value;
    }

    boolean succ;
    String code,message;
    ValueCus value;


    public class ValueCus {
        public long getPageCount() {
            return pageCount;
        }

        public long getTotalCount() {
            return totalCount;
        }

        public List<ValuesCus> getValues() {
            return values;
        }

        long pageCount,totalCount;
        List<ValuesCus> values = new LinkedList<>();
    }

    public class ValuesCus{
        public String getPersonId() {
            return personId;
        }

        public String getPic() {
            return pic;
        }

        public String getSexDesc() {
            return sexDesc;
        }

        public int getAge() {
            return age;
        }

        public int getSex() {
            return sex;
        }

        public boolean newbie() {
            return newbie;
        }

        public long getEntryNum() {
            return entryNum;
        }

        public String entryTime() {
            return entryTime;
        }

        public double getConsumePercentage() {
            return consumePercentage;
        }

        public List<String> getItems() {
            return items;
        }

        String personId,pic,sexDesc,entryTime;
        int age,sex;
        boolean newbie;
        long entryNum;

        double consumePercentage;
        List<String> items  = new LinkedList<>();

    }

}
