package com.example.rater;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import org.w3c.dom.Text;

public class RateDialog extends Dialog {

    private float userRate = 0;
    private String movieName = "";

    private Dialog resultDialog = null;

    public RateDialog(@NonNull Context context, String movieName) {
        super(context);
        this.movieName = movieName;
        resultDialog = new Dialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_dialog);

        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
        final AppCompatButton laterBtn = findViewById(R.id.laterBtn);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);



        rateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Click handler code here
                Log.e("Movie : ", movieName + " , Rating " + userRate);
                dismiss();
                openResultDialog();
            }

            private void openResultDialog() {
                resultDialog.setContentView(R.layout.result_dialog);
                resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btnOK = resultDialog.findViewById(R.id.closeBtn);
                TextView resultText = (TextView) resultDialog.findViewById(R.id.resultText);
                resultText.setText("You have rated " + movieName + " : " + userRate + " stars");

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultDialog.dismiss();
                    }
                });

                resultDialog.show();
            }
        });

        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("Rating : ", "" + rating);
                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.one_star);
                } else if (rating <= 2) {
                    ratingImage.setImageResource(R.drawable.two_star);
                } else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.three_star);
                } else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.four_star);
                } else {
                    ratingImage.setImageResource(R.drawable.five_star);
                }
                // [Fix] : Animation happening only at last two emojis
                // animate emoji image
                animateImage(ratingImage);

                // selected rating by user
                userRate = rating;
            }
        });
    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.setAnimation(scaleAnimation);
    }
}
