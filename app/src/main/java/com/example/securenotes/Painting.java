package com.example.securenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Painting extends View {

    public Painting(Context context) {
        super(context);
    }
    Paint paint = new Paint();
    float x = 10;
    float y = 10;
    private Path drawPath;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ALPHA_8);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0 , paint);
        canvas.drawPath(drawPath, paint);
        canvas.drawCircle(x, y, 5, paint);
    }
}