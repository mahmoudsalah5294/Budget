package com.mahmoudsalah.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    ListView incomeListview,foodListview,otherListview;
    List<Income> incomes;
    List<Food> foods;
    List<Others> others;
    ArrayList<String> inc = new ArrayList<>();
    ArrayList<String> foo = new ArrayList<>();
    ArrayList<String> oth = new ArrayList<>();
    ArrayList<String> incname = new ArrayList<>();
    ArrayList<String> fooname = new ArrayList<>();
    ArrayList<String> othname = new ArrayList<>();
    TextView sumIncText,sumFoodText,sumOtherText,diffText;
    ArrayList<Float> incNum = new ArrayList<>();
    ArrayList<Float> fooNum = new ArrayList<>();
    ArrayList<Float> othNum = new ArrayList<>();
    float suminc=0,sumfood=0,sumother=0,diff = 0;
    ArrayAdapter adapter,adapter1,adapter2;;
    ArrayList<String> incdate = new ArrayList<>();
    ArrayList<String> foodate = new ArrayList<>();
    ArrayList<String> othdate = new ArrayList<>();
    Long x;
    int y,n=0;
    ArrayList<String> inctime = new ArrayList<>();
    ArrayList<String> footime = new ArrayList<>();
    ArrayList<String> othtime = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inc.clear();
        foo.clear();
        oth.clear();
        incNum.clear();
        fooNum.clear();
        othNum.clear();

        incomes = new Select().from(Income.class).execute();
        foods = new Select().from(Food.class).execute();
        others = new Select().from(Others.class).execute();

        for (Income income : incomes) {

            inc.add(income.name+" "+income.money);
            incNum.add(Float.valueOf(income.money));
            incname.add(income.name);
            incdate.add(income.date);
            inctime.add(income.time);

        }
        for (Food food : foods) {

            foo.add((food.fname+" "+food.frmoney));
            fooNum.add(Float.valueOf(food.frmoney));
            fooname.add(food.fname);
            foodate.add(food.date);
            footime.add(food.time);


        }
        for (Others other : others) {

            oth.add((other.name+" "+other.money));
            othNum.add(Float.valueOf(other.money));
            othname.add(other.name);
            othdate.add(other.date);
            othtime.add(other.time);

        }
        sumIncText = findViewById(R.id.sumIncText);
        sumFoodText = findViewById(R.id.sumFoodText);
        sumOtherText=findViewById(R.id.sumOtherText);
        diffText = findViewById(R.id.diffText);
        addButton =findViewById(R.id.addButton);
        incomeListview =findViewById(R.id.incomeListview);
        foodListview =findViewById(R.id.foodListview);
        otherListview =findViewById(R.id.otherListview);
        registerForContextMenu(incomeListview);
        registerForContextMenu(foodListview);
        registerForContextMenu(otherListview);
//        Toast.makeText(this, ""+inc, Toast.LENGTH_SHORT).show();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, inc);
        incomeListview.setAdapter(adapter);
        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foo);
        foodListview.setAdapter(adapter1);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, oth);
        otherListview.setAdapter(adapter2);

        for (int i =0;i<incNum.size();i++){
            suminc = suminc + incNum.get(i);
        }
        for (int i =0;i<fooNum.size();i++){
            sumfood = sumfood + fooNum.get(i);
        }
        for (int i =0;i<othNum.size();i++){
            sumother = sumother + othNum.get(i);
        }

        diff = suminc-(sumfood+sumother) ;


        sumIncText.setText("" + suminc);

        sumFoodText.setText(""+sumfood);

        sumOtherText.setText(""+sumother);

        diffText.setText(""+diff);


            final Bundle bundle = new Bundle();


        incomeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
//                x = id;
                y = position;

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, incomeListview);
                popupMenu.getMenuInflater().inflate(R.menu.floating_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.edit:


                                bundle.putString("name",incname.get(position));
                                bundle.putFloat("number",incNum.get(position));
                                bundle.putString("type","Income");
                                Intent intent = new Intent(MainActivity.this,Update.class);

                                intent.putExtras(bundle);
                                startActivity(intent);

                                new Delete().from(Income.class).where("name=?",incname.get(y)).execute();
                                inc.remove(y);
                                adapter.notifyDataSetChanged();

                                return true;
                            case R.id.delete:
                                new Delete().from(Income.class).where("name=?", incname.get(y)).execute();
                                inc.remove(y);
                                adapter.notifyDataSetChanged();
                                incNum.remove(y);
                                incname.remove(y);
                                suminc=0;

                                for (int i =0;i<incNum.size();i++){
                                    suminc = suminc + incNum.get(i);
                                }

                                diff = suminc-(sumfood+sumother) ;


                                sumIncText.setText("" + suminc);

                                diffText.setText(""+diff);
                                return true;

                            case R.id.delete_all:
                                new Delete().from(Income.class).execute();
                                incNum.clear();
                                incname.clear();
                                inc.clear();
                                adapter.notifyDataSetChanged();
                                suminc=0;

                                for (int i =0;i<incNum.size();i++){
                                    suminc = suminc + incNum.get(i);
                                }

                                diff = suminc-(sumfood+sumother) ;


                                sumIncText.setText("" + suminc);

                                diffText.setText(""+diff);
                                return true;
                            case R.id.details:
                                Intent detail = new Intent(MainActivity.this,Details.class);
                                detail.putExtra("name",incname.get(position));
                                detail.putExtra("number",""+incNum.get(position));
                                detail.putExtra("date",incdate.get(position));
                                detail.putExtra("time",inctime.get(position));
                                detail.putExtra("type","Income");
                                startActivity(detail);

                        }
                        return true;
                    }
                });


                popupMenu.show();


            }
        });


        foodListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, final long id) {

//                x = id;
                y = position;

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, foodListview);
                popupMenu.getMenuInflater().inflate(R.menu.floating_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                bundle.putString("name",fooname.get(position));
                                bundle.putFloat("number",fooNum.get(position));
                                bundle.putString("type","Food");
                                Intent intent = new Intent(MainActivity.this,Update.class);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                new Delete().from(Food.class).where("fname=?",fooname.get(y)).execute();
                                foo.remove(y);
                                adapter1.notifyDataSetChanged();
                                return true;
                            case R.id.delete:
                                new Delete().from(Food.class).where("fname=?",fooname.get(y)).execute();
                                foo.remove(y);
                                adapter1.notifyDataSetChanged();

                                fooNum.remove(y);
                                fooname.remove(y);
                                sumfood=0;

                                for (int i =0;i<fooNum.size();i++){
                                    sumfood = sumfood + fooNum.get(i);
                                }
                                diff = suminc-(sumfood+sumother) ;


                                sumFoodText.setText(""+sumfood);


                                diffText.setText(""+diff);
                                return true;

                            case R.id.delete_all:

                                new Delete().from(Food.class).execute();
                                fooNum.clear();
                                fooname.clear();
                                foo.clear();
                                adapter1.notifyDataSetChanged();
                                sumfood=0;

                                for (int i =0;i<fooNum.size();i++){
                                    sumfood = sumfood + fooNum.get(i);
                                }
                                diff = suminc-(sumfood+sumother) ;


                                sumFoodText.setText(""+sumfood);


                                diffText.setText(""+diff);
                                return true;

                            case R.id.details:
                                Intent detail = new Intent(MainActivity.this,Details.class);
                                detail.putExtra("name",fooname.get(position));
                                detail.putExtra("number",""+fooNum.get(position));
                                detail.putExtra("date",foodate.get(position));
                                detail.putExtra("time",footime.get(position));
                                detail.putExtra("type","Foods");
                                startActivity(detail);

                        }
                        return true;
                    }
                });


                popupMenu.show();


            }
        });


        otherListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                x = id;
                y = position;

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, otherListview);
                popupMenu.getMenuInflater().inflate(R.menu.floating_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                bundle.putString("name",othname.get(y));
                                bundle.putFloat("number",othNum.get(y));
                                bundle.putString("type","Others");
                                Intent intent = new Intent(MainActivity.this,Update.class);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                new Delete().from(Others.class).where("oname=?",othname.get(y)).execute();
                                oth.remove(y);
                                adapter2.notifyDataSetChanged();
                                return true;
                            case R.id.delete:
                                new Delete().from(Others.class).where("oname=?", othname.get(y)).execute();
                                oth.remove(y);
                                othname.remove(y);
                                adapter2.notifyDataSetChanged();
                                othNum.remove(y);
                                sumother = 0;
                                for (int i = 0; i < othNum.size(); i++) {
                                    sumother = sumother + othNum.get(i);
                                }

                                diff = suminc - (sumfood + sumother);

                                sumOtherText.setText("" + sumother);

                                diffText.setText("" + diff);
                                return true;

                            case R.id.delete_all:
                                new Delete().from(Others.class).execute();
                                oth.clear();
                                adapter2.notifyDataSetChanged();
                                othNum.clear();
                                othname.clear();
                                sumother = 0;
                                for (int i = 0; i < othNum.size(); i++) {
                                    sumother = sumother + othNum.get(i);
                                }

                                diff = suminc - (sumfood + sumother);

                                sumOtherText.setText("" + sumother);

                                diffText.setText("" + diff);
                                return true;

                            case R.id.details:
                                Intent detail = new Intent(MainActivity.this,Details.class);
                                detail.putExtra("name",othname.get(y));
                                detail.putExtra("number",""+othNum.get(y));
                                detail.putExtra("date",othdate.get(y));
                                detail.putExtra("time",othtime.get(y));
                                detail.putExtra("type","Others");
                                startActivity(detail);
                        }
                        return true;
                    }
                });


                popupMenu.show();
            }
        });

   }

    @Override
    public void onBackPressed() {
        n++;
        if (n==1)
        Toast.makeText(this, "Press back again and will get out", Toast.LENGTH_SHORT).show();
    else if (n==2)
        System.exit(0);

    }

    public void add(View view) {
        Intent intent = new Intent(MainActivity.this,Add.class);
        startActivity(intent);
    }
}
