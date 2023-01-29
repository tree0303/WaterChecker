package tree0303.github.io.waterchecker.add;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tree0303.github.io.waterchecker.PlantViewModel;
import tree0303.github.io.waterchecker.R;
import tree0303.github.io.waterchecker.databinding.FragmentAddBinding;

public class AddFragment extends Fragment implements TextWatcher {

    private PlantViewModel mplantViewModel;
    private FragmentAddBinding binding;
    private boolean minputflag = false;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mplantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

        @SuppressLint("ClickableViewAccessibility")
        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
            NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();

//            戻るボタン
            ImageButton backbtn = binding.backBtn;
            backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navController.popBackStack();
                }
            });


//            登録ボタン
            EditText plantnametext = binding.plantName;
            plantnametext.addTextChangedListener(this);
            Button addbtn = binding.addBtn;
            addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = plantnametext.getText().toString();
                    int num = 0;
                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    String dateTime = datetimeformatter.format(localDateTime);

                    mplantViewModel.insert(name, num, dateTime);
                    navController.popBackStack();

                }
            });
//            フォーカスアウト
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            ConstraintLayout constraintLayout = binding.foundLayout;
            constraintLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    //if(foundlayout){}
                    inputMethodManager.hideSoftInputFromWindow(constraintLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    constraintLayout.requestFocus();
                    return true;
                }
            });
            plantnametext.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == keyEvent.KEYCODE_ENTER){
                        inputMethodManager.hideSoftInputFromWindow(plantnametext.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                        constraintLayout.requestFocus();
                        return true;
                    }
                    return false;
                }
            });
        return view;
    }

//edittextが入力されたか
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString().replaceAll(" |　","");
        minputflag = !str.equals("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}