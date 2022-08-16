package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Stack;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowResultFragment extends Fragment {
    Bitmap selectedImage;
    Fish_Item predictedFish;
    String confidence;

    View view;

    com.theartofdev.edmodo.cropper.CropImageView selectedImageView;
    CircleImageView predictedFishImageView;
    TextView predictedFishName;
    TextView predictedFishPrice;
    TextView confidenceTextView;
    Button cropButton, undoButton;

    Stack<Bitmap> imageStack = new Stack<>();
    Stack<Pair<Fish_Item, String>> resultStack = new Stack<>();

    public ShowResultFragment() {
        super(R.layout.activity_show_result);
    }

    public ShowResultFragment(Bitmap selectedImage, Fish_Item predictedResult, String confidence) {
        super(R.layout.activity_show_result);
        this.selectedImage = selectedImage;
        this.predictedFish = predictedResult;
        this.confidence = confidence;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);

        if (view != null) {
            selectedImageView = view.findViewById(R.id.selected_image_view);
            predictedFishImageView = view.findViewById(R.id.predicted_image);
            predictedFishName = view.findViewById(R.id.name_fish);
            predictedFishPrice = view.findViewById(R.id.price_fish);
            confidenceTextView = view.findViewById(R.id.confidence_TextView);
            cropButton = view.findViewById(R.id.crop_button);
            undoButton = view.findViewById(R.id.undo_button);

            selectedImageView.setImageBitmap(this.selectedImage);

            if (predictedFish != null) {
                predictedFishImageView.setImageBitmap (
                        BitmapFactory.decodeResource (
                                view.getContext().getResources(),
                                predictedFish.getImage()
                        )
                );
                predictedFishName.setText(predictedFish.getName());
                predictedFishPrice.setText(predictedFish.getPrice());
                confidenceTextView.setText(confidence);
            }

            cropButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cropImage();
                }
            });

            undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    undoImage();
                }
            });
        } else
            Log.d("ShowResultFragment", "onCreateView: view is null");

        return view;
    }

    private void undoImage() {
        if (!imageStack.empty()) {
            selectedImage = imageStack.pop();
            Pair<Fish_Item, String> popResult = resultStack.pop();
            predictedFish = popResult.first;
            confidence = popResult.second;
            selectedImageView.setImageBitmap(selectedImage);
            updateResult(predictedFish, confidence);
        }
    }

    private void cropImage() {
        imageStack.push(selectedImage);
        selectedImage = selectedImageView.getCroppedImage();

        Pair<Fish_Item, String> newResult = SearchFragment.classifyImage(selectedImage, getContext());
        if (newResult != null) {
            resultStack.push(new Pair<>(predictedFish, confidence));
            predictedFish = newResult.first;
            confidence = newResult.second;
            selectedImageView.setImageBitmap(selectedImage);
            updateResult(predictedFish, confidence);
        } else {
            selectedImage = imageStack.pop();
        }
    }

    private void updateResult(Fish_Item fish, String confidence) {
            if (fish != null) {
                predictedFishImageView.setImageBitmap (
                        BitmapFactory.decodeResource (
                                view.getContext().getResources(),
                                fish.getImage()
                        )
                );
                predictedFishName.setText(fish.getName());
                predictedFishPrice.setText(fish.getPrice());
                confidenceTextView.setText(confidence);
            }
    }
}
