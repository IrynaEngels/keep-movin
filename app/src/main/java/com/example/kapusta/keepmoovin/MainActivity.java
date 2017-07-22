package com.example.kapusta.keepmoovin;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView circle;
    ImageView rectangle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        final Handler handler1 = new Handler();
        handler1.post(new Runnable() {
            Display display = getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            float viewX = v.getX();
            float viewY = v.getY();
            int side = 1;
            @Override
            public void run() {

                if (v.getX() == width - 50) {
                    side = 2;
                }
                if (v.getY() == height - 200) {
                    side = 3;
                }
                if (v.getX() == 0) {
                    side = 4;
                }
                if (v.getY() == 0) {
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
                handler1.postDelayed(this, 5);
            }
        });



    }
}
