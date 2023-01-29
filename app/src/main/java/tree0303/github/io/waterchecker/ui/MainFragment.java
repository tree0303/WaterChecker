package tree0303.github.io.waterchecker.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import tree0303.github.io.waterchecker.R;
import tree0303.github.io.waterchecker.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {


    private FragmentMainBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
//        アダプターをViewPager2セット、タブレイアウトにセット＆リンク
        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager(), getLifecycle());
        ViewPager2 viewPager2 = binding.viewPager2;
        viewPager2.setAdapter(tabAdapter);
        TabLayout tabs = binding.tabs;
        TabLayoutMediator.TabConfigurationStrategy mediatorStrategy = (tab, position) ->
                tab.setText(tabAdapter.getTabTitle(position));
        new TabLayoutMediator(tabs, viewPager2, mediatorStrategy).attach();

//      floatingボタン、リスナーセット
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(this::onClick);
        return view;
    }
//      floatingボタンで画面遷移
    private void onClick(View view) {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        navController.navigate(R.id.action_mainFragment_to_addFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}