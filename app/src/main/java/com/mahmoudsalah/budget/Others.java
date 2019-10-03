package com.mahmoudsalah.budget;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "others",id = "_id")
public class Others extends Model {

    public Others() {
        super();
    }

    @Column(name = "oname")
    public String name;

    @Column(name = "omoney")
    public String money;
    @Column(name="date")
    public  String date;
    @Column(name="time")
    public String time;
}
