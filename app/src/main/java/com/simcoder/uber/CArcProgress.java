package com.simcoder.uber;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.lzyzsd.circleprogress.ArcProgress;

/**
 * Created by TAPI04 on 3/10/2018.
 */

public class CArcProgress extends ArcProgress {
    int differentialProgress;
    int lastProgress = 0;
    int currProgress = 0;

    ValueAnimator progressChangeAnimator = new ValueAnimator();
    boolean isDemoRunning = true;

    OnProgressChangeListener onProgressChangeListener;

    public CArcProgress(Context context) {
        super(context);
    }

    public CArcProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CArcProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        runDemo();
    }

    void initAnim() {
        progressChangeAnimator = ValueAnimator.ofInt(lastProgress, currProgress);
        progressChangeAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressChangeAnimator.setDuration((long) Math.abs(currProgress - lastProgress * 20));
        progressChangeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setProgress((int) valueAnimator.getAnimatedValue());
                if (onProgressChangeListener != null) {
                    onProgressChangeListener.onValueChanged((float) valueAnimator.getAnimatedValue());
                }

            }
        });
        post(new Runnable() {
            @Override
            public void run() {
                progressChangeAnimator.start();

            }
        });

    }

    public void setProgressWithAnim(final int progress) {
        this.currProgress = progress;
        differentialProgress = progress - this.lastProgress;
//        progressChangeAnimator.setDuration(Math.abs(differentialProgress * 20));
        if (!isDemoRunning) {
            initAnim();
        }
        lastProgress = currProgress;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }

    public void runDemo() {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setDuration(700);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        final ValueAnimator valueAnimatorRev = ValueAnimator.ofInt(0, Math.round(currProgress));
        valueAnimatorRev.setDuration(400);
        valueAnimatorRev.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setProgress((int) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimatorRev.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setProgress(Math.round((int) valueAnimator.getAnimatedValue()));
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                valueAnimatorRev.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        valueAnimatorRev.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isDemoRunning = false;
                lastProgress = currProgress;
                setProgressWithAnim(currProgress);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        valueAnimator.start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        progressChangeAnimator.cancel();
    }

    public interface OnProgressChangeListener {

        void onValueChanged(float progress);
    }
}
