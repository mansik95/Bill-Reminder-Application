package com.example.yashoza.billreminder_devit;

/**
 * Created by yashoza on 02/04/18.
 */

class Bill {
    public String name;
    public String date;
    public String amount;
    public String type;
    public String desc;
    public String flag;

    // TODO: Pay flag - Boolean type

    public Bill() {}

    public Bill(String name, String date, String amount, String flag, String desc, String type) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.flag = flag;
        this.type = type;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String time) {
        this.flag = flag;
    }
}
