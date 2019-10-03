package com.mahmoudsalah.budget;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "food",id = "_id")
public class Food extends Model {

    public Food() {
        super();
    }


    @Column(name = "fname")
    public String fname;
    @Column(name = "fmoney")
    public String frmoney;
    @Column(name="date")
    public  String date;
    @Column(name="time")
    public String time;
}
