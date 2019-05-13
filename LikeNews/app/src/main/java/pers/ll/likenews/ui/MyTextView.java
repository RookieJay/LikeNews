package pers.ll.likenews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import pers.ll.likenews.base.MyApplication;

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        setTypeface(MyApplication.getInstance().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApplication.getInstance().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApplication.getInstance().getTypeface());
    }
}
