package com.example.e_com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    ImageView back_filter;
//    private MediaPlayer hot_deal_sound;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Samsung"));
        categoryList.add(new Category(2, "LG"));
        categoryList.add(new Category(3, "Xiaomi"));

        setCategoryRecycler(categoryList);

        List<TV> TVList = new ArrayList<>();
        TVList.add(new TV(1, 1, "s1", "Samsung\nQE50Q60BAU", "69990", "3840x2160", "#424345", "Описание товара"));
        TVList.add(new TV(2, 2, "l1", "LG\nOLED55C2RLA", "164999", "3840x2160", "#404345", "Описание товара"));
        TVList.add(new TV(3, 3, "x1", "Xiaomi\nMi TV P1 50", "44990", "3840x2160", "#384345", "Описание товара"));

        fullTVList.addAll(TVList);

        setTVRecycler(TVList);

        back_filter = (ImageView) findViewById(R.id.imageView2);

//        hot_deal = (ImageView) findViewById(R.id.imageView);
//        hot_deal_sound = MediaPlayer.create(this, R.raw.fire);
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

//    public void soundPlay(MediaPlayer sound){
//        sound.start();
//    }
}