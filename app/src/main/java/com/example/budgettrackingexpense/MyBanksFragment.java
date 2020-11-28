package com.example.budgettrackingexpense;

import android.content.Intent;
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
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_banks, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("My Banks");


        ArrayList<List> Banks = new ArrayList();

        Button cy = view.findViewById(R.id.cbcyprus);
        cy.setEnabled(false);
        Button astrob = view.findViewById(R.id.cbastro);
        astrob.setEnabled(false);
        Button hel = view.findViewById(R.id.cbhellenic);
        hel.setEnabled(false);
        Button rcb = view.findViewById(R.id.rcb);
        rcb.setEnabled(false);
        Button alphaB = view.findViewById(R.id.cbalpha);
        alphaB.setEnabled(false);


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
                    if (myStr.equals("Bank of Cyprus")) {
                        cy.setEnabled(true);
                    }
                    if (myStr.equals("AstroBank")) {
                        astrob.setEnabled(true);
                    }
                    if (myStr.equals("Hellenic Bank"))
                    {
                        hel.setEnabled(true);
                    }
                    if (myStr.equals("RCB Bank"))
                    {
                        rcb.setEnabled(true);
                    }
                    if (myStr.equals("Alpha Bank"))
                    {
                        alphaB.setEnabled(true);
                    }


                    cy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity().getApplication(), Tabbed_Bank_Activity.class);
                            startActivity(in);
                        }
                    });
                    astrob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity().getApplication(), Tabbed_Bank_Activity.class);
                            startActivity(in);
                        }
                    });
                    hel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity().getApplication(), Tabbed_Bank_Activity.class);
                            startActivity(in);
                        }
                    });
                    rcb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity().getApplication(), Tabbed_Bank_Activity.class);
                            startActivity(in);
                        }
                    });
                    alphaB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity().getApplication(), Tabbed_Bank_Activity.class);
                            startActivity(in);
                        }
                    });
                    System.out.println(myStr);
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