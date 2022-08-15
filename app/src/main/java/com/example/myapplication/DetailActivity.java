package com.example.myapplication;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Fish_Item fish_item = (Fish_Item) bundle.get("object_fish");

        ImageView imgFish = findViewById(R.id.ivFish);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPrice = findViewById(R.id.tvPrice);

        imgFish.setImageBitmap (
                BitmapFactory.decodeResource (
                        imgFish.getContext().getResources(),
                        fish_item.getImage()
                )
        );
        tvName.setText(fish_item.getName());
        tvPrice.setText(fish_item.getPrice());


    }
}
