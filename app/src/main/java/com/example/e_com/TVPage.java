package com.example.e_com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_com.model.Order;

public class TVPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvpage);

        ConstraintLayout TVBg = findViewById(R.id.tvPageBg);
        ImageView tvImage = findViewById(R.id.tvPageImage);
        TextView tvTitle = findViewById(R.id.tvPageTitle);
        TextView tvPrice = findViewById(R.id.tvPagePrice);
        TextView tvResolution = findViewById(R.id.tvPageResolution);
        TextView tvText = findViewById(R.id.tvPageText);

        TVBg.setBackgroundColor(getIntent().getIntExtra("TVBg", 0));
        tvImage.setImageResource(getIntent().getIntExtra("TVImage", 0));
        tvTitle.setText(getIntent().getStringExtra("TVTitle"));
        tvPrice.setText(getIntent().getStringExtra("TVPrice"));
        tvResolution.setText(getIntent().getStringExtra("TVResolution"));
        tvText.setText(getIntent().getStringExtra("TVText"));
    }
    public void addToCart(View view) {

        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Добаивть товар в корзину?")
                        .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int itemId = getIntent().getIntExtra("TVId", 0);
                                        Order.items_id.add(itemId);
                                        //Toast.makeText(this, "Товар добавлен в корзину!", Toast.LENGTH_LONG).show();
                                    }
                                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.setTitle("e-com");
        alert.show();
    }
}