package vn.edu.usth.dropboxclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class ZoomActivity extends FragmentActivity{
    private Animator currentAnimator;
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        final View imgView = findViewById(R.id.imageView1);
        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                zoomImageFromThumb(imgView,R.drawable.avatar1);
            }
        });

        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId){
        if (currentAnimator != null){
            currentAnimator.cancel();
        }

        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        final Rect startBound = new Rect();
        final Rect endBound = new Rect();
        final Point gloablOffset = new Point();

        thumbView.getGlobalVisibleRect(startBound);
        findViewById(R.id.container).getGlobalVisibleRect(endBound, gloablOffset);
        startBound.offset(-gloablOffset.x, -gloablOffset.y);
        endBound.offset(-gloablOffset.x, -gloablOffset.y);

        float scale;
        if((float) endBound.width() / endBound.height() > (float)startBound.width()/startBound.height()){
            // Extend horizontally
            scale = (float) startBound.height() / endBound.height();
            float startWidth = scale * endBound.width();
            float deltaWidth = (startWidth - startBound.width()) / 2;
            startBound.left -= deltaWidth;
            startBound.right += deltaWidth;
        }
        else{
            // Extend vertically
            scale = (float) startBound.width() / endBound.width();
            float startHeight = scale * endBound.height();
            float deltaHeight = (startHeight - startBound.height()) / 2;
            startBound.top -= deltaHeight;
            startBound.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotX(0f);


        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBound.left, endBound.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBound.top, endBound.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, scale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, scale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                currentAnimator = null;
            }

            public void onAnimationCancel(Animator animation){
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        final float scaleFinal = scale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAnimator != null){
                    currentAnimator.cancel();
                }

                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBound.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBound.top))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, scaleFinal))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, scaleFinal));
                set.setDuration(shortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation){
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation){
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });

                set.start();
                currentAnimator = set;
            }
        });
    }
}