package com.example.budgettrackingexpense;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment {

    PieChart pieChart;
    DatabaseReference databaseReference;

    List<Categories> categoriesList;
//    int LineCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //  FAB: ADD INCOME
        FloatingActionButton fab_add_income = root.findViewById(R.id.fab_add);
        fab_add_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), add_income.class);
                startActivity(in);
            }
        });

        //  FAB: ADD EXPENSE
        FloatingActionButton fab_add_expense = root.findViewById(R.id.fab_remove);
        fab_add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ADDED EXPENSE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnTestimgTimRead = root.findViewById(R.id.btnTestimgTimRead);

        //  SET FILE NAME
        String file_name = "test4.txt";

        //  READ FILE
        btnTestimgTimRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //  GET NUMBER OF LINES SO THAT WE KNOW HOW BIG THE ARRAY WILL BE
//                try {
//                    FileInputStream fin = getActivity().openFileInput(file_name);
//                    DataInputStream din = new DataInputStream(fin);
//                    InputStreamReader isr = new InputStreamReader(din);
//                    BufferedReader br = new BufferedReader(isr);
//                    int lines = 0;
//                    while (br.readLine() != null) lines++;
//                    System.out.println("LINES: " + lines);
//                    br.close();
//
//                    LineCount = lines;
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                //  READ FILE, SPLIT EACH VARIABLE THAT IS SEPARATED WITH A COMMA
                //  AND SAVE IT TO AN ARRAY LIST OF LISTS
                try {
                    FileInputStream fin = getActivity().openFileInput(file_name);
                    DataInputStream din = new DataInputStream(fin);
                    InputStreamReader isr = new InputStreamReader(din);
                    BufferedReader br = new BufferedReader(isr);

                    //  CREATE ARRAY LIST, LIST AND VARIABLE TO HOLD EACH LINE
                    ArrayList<List> finalArrayList = new ArrayList<>();
                    List<String> strList = new ArrayList<String>();
                    String strLine;

                    //  READ EVERY LINE
                    //  SPLIT EACH LINE BASED ON THE COMMA (SEPARATOR)
                    //  EACH VALUE IS ADDED IN A LIST
                    //  LIST GETS ADDED TO ARRAY LIST
                    //  LIST GETS INITIALIZED AGAIN
                    //  REPEAT UNTIL TEXT FILE IS READ THROUGH
                    while((strLine = br.readLine()) != null) {
                        String[] res = strLine.split("[,]", 0);
                        System.out.println(res);
                        for(String myStr: res) {
                            System.out.println(myStr);
                            //  ADDS THE THREE VARIABLES TO THE STRING ARRAY
                            strList.add(myStr);
                            System.out.println(strList);
                        }
                        System.out.println("1.strList BEFORE: " + strList);
                        finalArrayList.add(strList);
                        System.out.println("2. finalArrayList BEFORE: " + finalArrayList);
                        //  INITIALIZE AGAIN THE STRLIST SO IT CAN GET NEW VALUES
                        strList = new ArrayList<String>();

                        System.out.println("1.strList AFTER: " + strList);
                        System.out.println("2. finalArrayList AFTER: " + finalArrayList);
                    }
                    System.out.println("\nFINAL: " + finalArrayList);

                    //  SOME LOCAL VARIABLES
                    Double total = 0.0;

                    //  ITERATE THROUGH EACH LIST IN THE ARRAY LIST
                    //  TO GRAB THE VALUES AND PROCESS THEM
                    for (int i = 0; i < finalArrayList.size(); i++) {
//                        System.out.println(finalArrayList.get(i) + " ");
                        for (int j = 0; j < finalArrayList.get(i).size(); j++) {
                            System.out.println(finalArrayList.get(i).get(j) + " ");
                            //  PARSE TO DOUBLE THE AMOUNT
                            if (j == 2) {
                                total += Double.parseDouble((String) finalArrayList.get(i).get(j));
                            }

                        }
                    }

                    System.out.println("TOTAL: " + total);

//                    System.out.println("3.Array List AFTER: " + finalArrayList);


                    fin.close();
                    Toast.makeText(getContext(), "Read successfully", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //  PIE CHART
        pieChart = root.findViewById(R.id.pieChart);

        //  CREATE CUSTOM COLORS
        final int[] MY_COLORS = {
                Color.parseColor("#C62828"),
                Color.parseColor("#4527A0"),
                Color.parseColor("#0277BD"),
                Color.parseColor("#2E7D32"),
                Color.parseColor("#F9A825"),
                Color.parseColor("#D84315"),
                Color.parseColor("#37474F"),
                Color.parseColor("#AD1457"),
                Color.parseColor("#6A1B9A"),
                Color.parseColor("#283593"),
                Color.parseColor("#1565C0"),
                Color.parseColor("#00838F"),
                Color.parseColor("#00695C"),
                Color.parseColor("#558B2F"),
                Color.parseColor("#EF6C00"),
                Color.parseColor("#4E342E"),
        };

        //  CREATE AN ARRAY LIST OF THE COLORS AND ASSIGN THEM
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c: MY_COLORS) colors.add(c);

        List<Categories> categoriesList = new ArrayList();

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid).child("categories");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds:snapshot.getChildren()) {
                        Categories data = ds.getValue(Categories.class);
                        categoriesList.add(data);
                    }

                } else {
                    System.out.println("No data found");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<String> categories = new ArrayList();

        try {
            FileInputStream fin = getActivity().openFileInput("categories.txt");
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    categories.add(line);
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        PieDataSet pieDataSet = new PieDataSet(pieChartDataSet(categories), "");
        pieDataSet.setColors(colors);
        pieDataSet.setValueLineColor(R.color.design_default_color_background);
        pieDataSet.setValueTextSize(18f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Spendings");
        pieChart.getLegend().setEnabled(false);
        pieChart.animate();


        return root;
    }


    //  PIE CHART DATA
    private ArrayList<PieEntry> pieChartDataSet(ArrayList<String> categories) {
        ArrayList<PieEntry> dataSet = new ArrayList<PieEntry>();

//        System.out.println("1: " + categories);

        for (String category: categories) {
            System.out.println("1: " + category);
        }


        dataSet.add(new PieEntry(10f, "Bills"));
        dataSet.add(new PieEntry(20f, "Food"));
        dataSet.add(new PieEntry(30f, "Car"));
        dataSet.add(new PieEntry(10f, "Food"));
        dataSet.add(new PieEntry(15f, "Eating out"));
        dataSet.add(new PieEntry(15f, "Entertainment"));


        return dataSet;
    }

}