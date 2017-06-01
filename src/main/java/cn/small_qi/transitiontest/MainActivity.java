package cn.small_qi.transitiontest;

import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
    }

    public void changeFragment(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Scene scene = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.container),R.layout.fragment_blan_fragment2,this);
            TransitionManager.go(scene,new ChangeBounds());
        }
    }
}
