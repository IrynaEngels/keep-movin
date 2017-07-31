package com.example.kapusta.keepmoovin;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView circle;
    ImageView rectangle;
    ConstraintLayout mConstraintLayout;
    final Handler handler = new Handler();
    HashMap<Integer, RunRunnable> hashObjectMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.container);

        circle = (ImageView) findViewById(R.id.circle);
        rectangle = (ImageView) findViewById(R.id.rectan);

        circle.setOnClickListener(this);
        rectangle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            moving(v);
    }

    public void moving(final View v){
        if (hashObjectMap.get(v.getId())==null) {
            RunRunnable mRun = new RunRunnable(v);
            handler.post(mRun);
            hashObjectMap.put(v.getId(), mRun);
        } else {
            RunRunnable mRun = hashObjectMap.get(v.getId());
            handler.removeCallbacks(mRun);
            hashObjectMap.remove(v.getId());
            mRun = null;
        }
    }

    class RunRunnable implements Runnable {
        View v;
        int width = mConstraintLayout.getWidth();
        int height = mConstraintLayout.getHeight();
        float viewX = 0;
        float viewY = 0;
        int side = 1;

        public View getmView() {
            return v;
        }

        public void setmView(View mView) {
            this.v = mView;
        }

        private RunRunnable() {
        }

        public RunRunnable(View mView) {
            this.v = mView;
            viewX = v.getX();
            viewY = v.getY();
        }

        @Override
        public void run() {
            for (Map.Entry<Integer, RunRunnable> entry : hashObjectMap.entrySet()) {
                Integer key = entry.getKey();
                if (key.equals(v.getId())) continue;
                RunRunnable value = entry.getValue();


            }

            if (v.getX() >= width - v.getWidth()) {
                side = 2;
            }
            if (v.getY() >= height - v.getHeight()) {
                side = 3;
            }
            if (v.getX() <= 0) {
                side = 4;
            }
            if (v.getY() <= 0) {
                side = 1;
            }

            if (side == 1) {
                v.setX(viewX++);
                v.setY(viewY++);
            }
            else if (side == 2){
                v.setX(viewX-=2);
                v.setY(viewY++);
            }
            else if (side == 3){
                v.setX(viewX--);
                v.setY(viewY-=2);
            }
            else if (side == 4){
                v.setX(viewX++);
                v.setY(viewY-=3);
            }
            handler.postDelayed(this, 5);
        }
    }
}
