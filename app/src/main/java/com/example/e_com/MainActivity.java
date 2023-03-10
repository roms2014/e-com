package com.example.e_com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.e_com.adapter.CategoryAdapter;
import com.example.e_com.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    ImageView hot_deal;
    private MediaPlayer hot_deal_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Samsung"));
        categoryList.add(new Category(2, "LG"));
        categoryList.add(new Category(3, "Xiaomi"));

        setCategoryRecycler(categoryList);

        hot_deal = (ImageView) findViewById(R.id.imageView);
        hot_deal_sound = MediaPlayer.create(this, R.raw.fire);
        imageClick();
    }
    public void imageClick(){
        hot_deal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        soundPlay(hot_deal_sound);
                    }
                }
        );
    }

    public void soundPlay(MediaPlayer sound){
        sound.start();
    }
    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
}