package com.maxkudla.reserve.presenter.common.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.maxkudla.reserve.R;

public class SetOfStarView extends LinearLayout {

    private ImageButton mIbStarOne;
    private ImageButton mIbStarTwo;
    private ImageButton mIbStarThree;
    private ImageButton mIbStarFour;
    private ImageButton mIbStarFive;
    private ImageButton[] stars;

    public SetOfStarView(Context context) {
        super(context);
        init();
    }

    public SetOfStarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SetOfStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setQuantityOfStars(int count) {
        clearStars();
        for (int i = 0; i < count; i++) {
            if (i < stars.length) stars[i].setImageResource(R.drawable.filled_star);
        }
    }

    public void clearStars() {
        for (ImageButton i : stars) {
            i.setImageResource(R.drawable.empty_star);
        }
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.set_of_star_view, this);
        stars = new ImageButton[5];
        stars[0] = (ImageButton) view.findViewById(R.id.star_one);
        stars[1] = (ImageButton) view.findViewById(R.id.star_two);
        stars[2] = (ImageButton) view.findViewById(R.id.star_three);
        stars[3] = (ImageButton) view.findViewById(R.id.star_four);
        stars[4] = (ImageButton) view.findViewById(R.id.star_five);
//        mIbStarOne = (ImageButton) view.findViewById(R.id.star_one);
//        mIbStarTwo = (ImageButton) view.findViewById(R.id.star_two);
//        mIbStarThree = (ImageButton) view.findViewById(R.id.star_three);
//        mIbStarFour = (ImageButton) view.findViewById(R.id.star_four);
//        mIbStarFive = (ImageButton) view.findViewById(R.id.star_five);

    }
}
