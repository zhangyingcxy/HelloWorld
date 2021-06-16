package com.swufe.helloworld;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPageAdapter extends FragmentStateAdapter {

    public MyPageAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new MyFragment();
        }else if(position==1){
            return new MyFragment2();
        }else{
            return new MyFragment3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}