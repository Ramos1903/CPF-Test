package com.CPFtests.perfumaria;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

class ProgressButton {

    private CardView cardView;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private TextView textView;

    Animation fade_in;


    ProgressButton(Context ct, View view) {
        fade_in = AnimationUtils.loadAnimation(ct, R.anim.fade_in);
        cardView = view.findViewById(R.id.cardView);
        layout = view.findViewById(R.id.constraint_layout);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.textView);
    }

    void buttonActivated() {
        progressBar.setAnimation(fade_in);
        textView.setAnimation(fade_in);
        progressBar.setVisibility(View.VISIBLE);
    }

    void buttonFinished() {
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.colorPrimary));
        progressBar.setVisibility(View.GONE);
        //textView.setText("Done");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 1000);
    }

    void reset() {
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.colorPrimaryDark));
        progressBar.setVisibility(View.GONE);
        //textView.setText("1");
    }
}

