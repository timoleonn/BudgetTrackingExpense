package com.example.budgettrackingexpense.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.budgettrackingexpense.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
        {
             root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==2)
        {
            root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==3)
        {
            root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER)==4)
        {
            root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==5)
        {
            root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==6)
        {
            root = inflater.inflate(R.layout.fragment_tabbed__bank_, container, false);
        }

        return root;
    }
}