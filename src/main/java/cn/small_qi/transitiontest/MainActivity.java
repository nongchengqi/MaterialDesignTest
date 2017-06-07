package cn.small_qi.transitiontest;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.ViewGroup;


import cn.small_qi.transitiontest.diyview.ColorEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new BlanFragment()).commit();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            ChangeBounds bounds = new ChangeBounds();
            bounds.setDuration(1500);
        }
        ColorEditText ct = (ColorEditText) findViewById(R.id.colorEt);
        ct.setRepeatTime(5000);
        ct.setTextTransitionColor(Color.parseColor("#FF1E88E5"),Color.parseColor("#FF1EC7E5"));
    }

    public void changeFragment(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Scene scene = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.container),R.layout.fragment_blan_fragment2,this);
            TransitionManager.go(scene,new ChangeBounds());
        }
    }
}
