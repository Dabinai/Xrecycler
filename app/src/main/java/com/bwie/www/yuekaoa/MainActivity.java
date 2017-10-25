package com.bwie.www.yuekaoa;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (MyView) findViewById(R.id.main_myview);
        //获取屏幕的宽和高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        //平移属性动画
        ObjectAnimator translationX = ObjectAnimator.ofFloat(myView, "translationX", 0, width);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(myView, "translationY", 0, height);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(translationY);
        animatorSet.setDuration(5000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                startActivity(new Intent(MainActivity.this,Title.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
