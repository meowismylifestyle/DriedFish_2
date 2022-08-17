package com.example.myapplication;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView rcvRecipe;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mListRecipe;

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
        ImageButton back = findViewById(R.id.back_pressed);

        rcvRecipe = findViewById(R.id.list_recipe);

        imgFish.setImageBitmap (
                BitmapFactory.decodeResource (
                        imgFish.getContext().getResources(),
                        fish_item.getImage()
                )
        );
        tvName.setText(fish_item.getName());
        tvPrice.setText(fish_item.getPrice());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvRecipe.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvRecipe.addItemDecoration(dividerItemDecoration);

        mListRecipe = new ArrayList<>();
        mRecipeAdapter = new RecipeAdapter(mListRecipe);

        rcvRecipe.setAdapter(mRecipeAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getListRecipeFromRealtimeDatabase();
    }

    private void getListRecipeFromRealtimeDatabase(){
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Fish_Item fish_item = (Fish_Item) bundle.get("object_fish");
        String nameFish = fish_item.getClassLabel();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recipe").child("Recipe").child(nameFish);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot post : snapshot.getChildren()){
                    Recipe recipe = post.getValue(Recipe.class);
                    mListRecipe.add(recipe);
                }

                mRecipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
