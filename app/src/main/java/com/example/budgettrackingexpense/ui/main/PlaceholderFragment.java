package com.example.budgettrackingexpense.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        View root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
        {
             root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);

            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://aeb.alphabank.com.cy/netteller-war/Login.xhtml");

            return root;
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==2)
        {
            root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);

            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://www.piraeusbank.com/sites/cyprus/el/Pages/default.aspx");

            return root;
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==3)
        {
            root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);

            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://online.bankofcyprus.com/netteller-web/");

            return root;
        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER)==4)
        {
            root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);
            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://www.hellenicbank.com/portalserver/hb-en-portal/en/personal-banking/ways-to-bank/i-need-a/web-banking");

            return root;
        }
        else if (getArguments().getInt(ARG_SECTION_NUMBER)==5)
        {
            root = inflater.inflate(R.layout.fragment_tabbed_activity, container, false);
            WebView web=root.findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://online.rcbcy.com/netteller-war/");

            return root;
        }


        return root;
    }
}