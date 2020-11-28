package com.example.budgettrackingexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_banks, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("My Banks");

        ArrayList<List> Banks = new ArrayList();

        try {
            FileInputStream fin = getActivity().openFileInput(file);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] res = strLine.split("[,]", 0);
                System.out.println(res);
                for (String myStr : res) {
                    if (myStr.getBytes().equals("Bank of Cyprus"))
                    {
                        Button cy = view.findViewById(R.id.cbcyprus);
                        cy.setEnabled(true);

                    }else {
                        Button cy = view.findViewById(R.id.cbcyprus);
                        cy.setEnabled(false);
                    }
                    if (myStr.getBytes().equals("AstroBank"))
                    {
                        Button astrob = view.findViewById(R.id.cbastro);
                        astrob.setEnabled(true);
                    }else {
                        Button astrob = view.findViewById(R.id.cbastro);
                        astrob.setEnabled(false);
                    }
                    if (myStr.getBytes().equals("Hellenic"))
                    {
                        Button hel = view.findViewById(R.id.cbhellenic);
                        hel.setEnabled(true);
                    }else {
                        Button hel = view.findViewById(R.id.cbhellenic);
                        hel.setEnabled(false);
                    }
                    if (myStr.getBytes().equals("RcbBank"))
                    {
                        Button rcbB = view.findViewById(R.id.rcbBank);
                        rcbB.setEnabled(true);
                    }else {
                        Button rcbB = view.findViewById(R.id.rcbBank);
                        rcbB.setEnabled(false);
                    }
                    if (myStr.getBytes().equals("Alpha Bank"))
                    {
                        Button alphaB = view.findViewById(R.id.cbalpha);
                        alphaB.setEnabled(true);
                    }else {
                        Button alphaB = view.findViewById(R.id.cbalpha);
                        alphaB.setEnabled(false);
                    }
                    System.out.println(myStr);
                    //  ADDS THE FOUR VARIABLES TO THE STRING ARRAY
                    Banks.add(Collections.singletonList(myStr));
                    System.out.println("1"+Banks);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }

        return view;
    }
}