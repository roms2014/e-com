package com.example.e_com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.e_com.adapter.CategoryAdapter;
import com.example.e_com.adapter.TVAdapter;
import com.example.e_com.model.Category;
import com.example.e_com.model.TV;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, TVRecycler;
    CategoryAdapter categoryAdapter;
    static TVAdapter tvAdapter;
    static List<TV> TVList = new ArrayList<>();
    static List<TV> fullTVList = new ArrayList<>();
    private DBwork db;
    ImageView back_filter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBwork(this);

        db.openConnection();
//        db.dbInsert_tv_table("'1','s1','Samsung\nQE50Q60BAU','69990','3840x2160','#424345','Описание товара'");
//        db.dbInsert_tv_table("'1','l1','LG\nOLED55C2RLA','164999','3840x2160','#404345','Описание товара'");
//        db.dbInsert_tv_table("'1','x1','Xiaomi\nMi TV P1 50','44990','3840x2160','#384345','Описание товара'");

        List<Category> categoryList = new ArrayList<>();

//        db.checkTable();
//        db.dbInsert_category("'Samsung'");
//        db.dbInsert_category("'LG'");
//        db.dbInsert_category("'Xiaomi'");

        int i = 1;
        for(String s : db.dbSelect_category()){
            categoryList.add(new Category(i, s));
            i++;
        }

        setCategoryRecycler(categoryList);

        List<TV> TVList = new ArrayList<>();
        for(String s : db.dbSelect_tv_table()){
            String[] str = s.split("`");
            TVList.add(new TV(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
                    str[2],str[3],str[4],str[5],str[6], str[7]));
        }

        db.closeConnection();

        fullTVList.addAll(TVList);

        setTVRecycler(TVList);

        back_filter = (ImageView) findViewById(R.id.imageView2);

        imageClick();

    }

    public void openShoppingCart(View view){
        Intent intent = new Intent(this, order_page.class);
        startActivity(intent);
    }

    private void setTVRecycler(List<TV> TVList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        TVRecycler = findViewById(R.id.TVRecycler);
        TVRecycler.setLayoutManager(layoutManager);

        tvAdapter = new TVAdapter(this, TVList);
        TVRecycler.setAdapter(tvAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void showTVByCategory(int category){
        TVList.clear();
        TVList.addAll(fullTVList);

        List<TV> filterTVs = new ArrayList<>();
        for (TV tv : TVList){
            if(tv.getCategory() == category)
                filterTVs.add(tv);
        }

        TVList.clear();
        TVList.addAll(filterTVs);

        tvAdapter.setTVs(TVList);
        tvAdapter.notifyDataSetChanged();
    }
    public void imageClick(){
        back_filter.setOnClickListener(
                view -> {
                    TVList.clear();
                    TVList.addAll(fullTVList);
                    tvAdapter.setTVs(TVList);
                    tvAdapter.notifyDataSetChanged();
                }
        );
    }
}