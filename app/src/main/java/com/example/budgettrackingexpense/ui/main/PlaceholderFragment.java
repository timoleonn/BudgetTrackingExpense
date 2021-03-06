package com.example.budgettrackingexpense.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.budgettrackingexpense.R;
import com.example.budgettrackingexpense.Settings;
import com.example.budgettrackingexpense.Tabbed_Bank_Activity;
import com.example.budgettrackingexpense.add_income;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        View root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);
        View fabRoot = inflater.inflate(R.layout.activity_tabbed__bank_, container, false);

        FloatingActionButton fab = fabRoot.findViewById(R.id.fabGoToBanks);
//        final TextView textView = root.findViewById(R.id.text_home);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1)
        {
            WebView web = root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://aeb.alphabank.com.cy/netteller-war/Login.xhtml");

        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==2)
        {
            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://www.piraeusbank.com/sites/cyprus/el/Pages/default.aspx");


        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==3)
        {
            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://online.bankofcyprus.com/netteller-web/");


        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER)==4)
        {
            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://www.hellenicbank.com/portalserver/hb-en-portal/en/personal-banking/ways-to-bank/i-need-a/web-banking");


        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==5)
        {

            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://online.rcbcy.com/netteller-war/");
        }
        return root;
    }
}