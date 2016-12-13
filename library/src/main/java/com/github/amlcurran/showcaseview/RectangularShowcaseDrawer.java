package com.github.amlcurran.showcaseview;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

/**
 * See both of these (second one is {@link com.github.amlcurran.showcaseview.MaterialShowcaseDrawer})
 * https://github.com/amlcurran/ShowcaseView/blob/87cfcb935f1af7f4e1d57b62111f267e68c53c61/sample/src/main/java/com/github/amlcurran/showcaseview/sample/CustomShowcaseActivity.java
 * https://github.com/amlcurran/ShowcaseView/blob/87cfcb935f1af7f4e1d57b62111f267e68c53c61/library/src/main/java/com/github/amlcurran/showcaseview/MaterialShowcaseDrawer.java
 */

public class RectangularShowcaseDrawer implements ShowcaseDrawer {
    private final float mWidth;
    private final float mHeight;
    private final Paint mEraserPaint;
    private final Paint mBasicPaint;
    private final RectF mRenderRect;
    private int mBackgroundColor;

    public RectangularShowcaseDrawer(View view) {
        mWidth = view.getWidth();
        mHeight = view.getHeight();

        mEraserPaint = new Paint();
        mEraserPaint.setColor(0xFFFFFF);
        mEraserPaint.setAlpha(0);
        mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        mEraserPaint.setAntiAlias(true);
        mBasicPaint = new Paint();

        mRenderRect = new RectF();
    }

    @Override
    public boolean isRectangularDrawer() {
        return true;
    }

    @Override
    public void setShowcaseColour(int color) {
    }

    @Override
    public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
        Canvas bufferCanvas = new Canvas(buffer);
        mRenderRect.left = x - mWidth / 2f;
        mRenderRect.right = x + mWidth / 2f;
        mRenderRect.top = y - mHeight / 2f;
        mRenderRect.bottom = y + mHeight / 2f;
        bufferCanvas.drawRect(mRenderRect, mEraserPaint);
    }

    @Override
    public int getShowcaseWidth() {
        return (int) mWidth;
    }

    @Override
    public int getShowcaseHeight() {
        return (int) mHeight;
    }

    @Override
    public float getBlockedRadius() {
        return 0;
    }

    @Override
    public void setBackgroundColour(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    @Override
    public void erase(Bitmap bitmapBuffer) {
        bitmapBuffer.eraseColor(mBackgroundColor);
    }

    @Override
    public void drawToCanvas(Canvas canvas, Bitmap bitmapBuffer) {
        canvas.drawBitmap(bitmapBuffer, 0, 0, mBasicPaint);
    }
}
