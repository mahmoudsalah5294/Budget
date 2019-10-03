package com.mahmoudsalah.budget;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "income",id = "_id")
public class Income extends Model {

    public Income() {
        super();
    }

    @Column(name = "name")
    public String name;

    @Column(name = "incmoney")
    public String money;
    @Column(name="date")
    public  String date;
    @Column(name="time")
    public String time;
}
