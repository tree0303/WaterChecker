package tree0303.github.io.waterchecker.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import tree0303.github.io.waterchecker.check.CheckFragment;
import tree0303.github.io.waterchecker.home.HomeFragment;

public class TabAdapter extends FragmentStateAdapter {
    private final int NUMBER_TABS = 2;

    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new CheckFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUMBER_TABS;
    }

    public String getTabTitle(int position) {
        switch (position){
            case 0:
                return "ホーム";
            case 1:
                return "チェックリスト";
            default:
                return null;
        }
    }
}
