package com.example.securenotes.Painting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;

import java.util.ArrayList;

public class MyDraw extends View {
    public MyDraw(Context context) {
        super(context);
    }
    Paint paint = new Paint();

    ArrayList<Float> x = new ArrayList<>();
    ArrayList<Float> y = new ArrayList<>();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x.add(event.getX());
        y.add(event.getY());
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setARGB(1000, 0, 255, 200);
        for (int i = 0; i < x.size(); i++) {
            canvas.drawCircle(x.get(i), y.get(i), 10, paint);
        }
        invalidate();
    }
}
