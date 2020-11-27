package com.example.budgettrackingexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyBanksFragment extends Fragment {
    private String file = "Banks.txt";
    int banking=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<List> Banks = new ArrayList();

        try {
            FileInputStream fin = getActivity().openFileInput(file);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String strLine;
            while((strLine = br.readLine()) != null) {
                String[] res = strLine.split("[,]", 0);
                System.out.println(res);
                for (String myStr : res) {
                    System.out.println(myStr);
                    //  ADDS THE FOUR VARIABLES TO THE STRING ARRAY
                    Banks.add(Collections.singletonList(myStr));
                    System.out.println(Banks);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_banks, container, false);

    }
}
