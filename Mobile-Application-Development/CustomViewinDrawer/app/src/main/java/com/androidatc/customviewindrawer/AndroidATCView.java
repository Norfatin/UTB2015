package com.androidatc.customviewindrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AndroidATCView extends View {

    private int mSquareCol, mLabelCol; //circle and text colors
    private String mSquareText; //label text
    private Paint mSquarePaint; //paint for drawing custom view

    public AndroidATCView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint object for drawing in onDraw
        mSquarePaint = new Paint();
        //get attributes specified in attrs.xml
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.AndroidATCView,0,0);

        try {
            //get text and colors specified using the names in attrs.xml
            mSquareText = typedArray.getString(R.styleable.AndroidATCView_squareLabel);
            //0 is default
            mSquareCol = typedArray.getInteger(R.styleable.AndroidATCView_squareColor, 0);
            mLabelCol = typedArray.getInteger(R.styleable.AndroidATCView_labelColor, 0);
        } finally {
            typedArray.recycle();
        }
    }

    public String getSquareText() {
        return mSquareText;
    }

    public int getLabelCol() {
        return  mLabelCol;
    }

    public int getSquareCol() {
        return mSquareCol;
    }

    public void setSquareColor (int newColor) {
        //update the instance variable
        mSquareCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public void setLabelColor (int newColor) {
        //update the instance variable
        mLabelCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel) {
        //update the instance variable
        mSquareText = newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInEditMode()) {

            //set drawing properties
            mSquarePaint.setStyle(Paint.Style.FILL);
            mSquarePaint.setAntiAlias(true);

            //set the paint color using the circle color specified
            mSquarePaint.setColor(mSquareCol);

            //draw the Square using the properties defined
            canvas.drawRect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight(), mSquarePaint);

            //set the text color using the color specified
            mSquarePaint.setColor(mLabelCol);

            //set text properties
            mSquarePaint.setTextAlign(Paint.Align.CENTER);
            mSquarePaint.setTextSize(50);
            //draw
            canvas.drawText(mSquareText,
                    this.getMeasuredWidth() / 2,
                    this.getMeasuredHeight() / 2,
                    mSquarePaint);
        }
    }
}