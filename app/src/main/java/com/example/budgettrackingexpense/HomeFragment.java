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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    PieChart pieChart;

    int LineCount = 0;
    ArrayList<ArrayList<String>> finalArrayList = new ArrayList<>();

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
        String file_name = "test3.txt";

        //  READ FILE
        btnTestimgTimRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  GET NUMBER OF LINES SO THAT WE KNOW HOW BIG THE ARRAY WILL BE
                try {
                    FileInputStream fin = getActivity().openFileInput(file_name);
                    DataInputStream din = new DataInputStream(fin);
                    InputStreamReader isr = new InputStreamReader(din);
                    BufferedReader br = new BufferedReader(isr);
                    int lines = 0;
                    while (br.readLine() != null) lines++;
                    System.out.println("LINES: " + lines);
                    br.close();

                    LineCount = lines;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //  READ FILE, SPLIT EACH VARIABLE THAT IS SEPARATED WITH A COMMA
                //  AND SAVE IT TO AN ARRAY
                try {
                    FileInputStream fin = getActivity().openFileInput(file_name);
                    DataInputStream din = new DataInputStream(fin);
                    InputStreamReader isr = new InputStreamReader(din);
                    BufferedReader br = new BufferedReader(isr);

                    //  CREATE ARRAY
                    String[][] income = new String[LineCount][3];
                    ArrayList<String> lineIncome = new ArrayList<>();

                    ArrayList<ArrayList<String>> arrayList = new ArrayList<>();

                    String strLine;
                    while((strLine = br.readLine()) != null) {
                        String[] res = strLine.split("[,]", 0);
                        System.out.println(res);
                        for(String myStr: res) {
                            System.out.println(myStr);
                            //  ADDS THE THREE VARIABLES TO AN ARRAY LIST
                            lineIncome.add(myStr);

                            System.out.println("Line Income BEFORE: " + lineIncome);
//                            arrayList.add(res);
                        }
                        arrayList.add(lineIncome);
                        System.out.println("Array List: " + arrayList);
                        finalArrayList = arrayList;
                        lineIncome.removeAll(lineIncome);
                        System.out.println("Line Income AFTER: " + lineIncome);
                        System.out.println("Array List AFTER: " + arrayList);
                    }

                    System.out.println("\nARRAYLIST\n");
                    System.out.println(finalArrayList);

                    fin.close();
                    Toast.makeText(getContext(), "Read successfully", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("1: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("2: " + e.getMessage());
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

        //
        PieDataSet pieDataSet = new PieDataSet(pieChartDataSet(), "");
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
    private ArrayList<PieEntry> pieChartDataSet() {
        ArrayList<PieEntry> dataSet = new ArrayList<PieEntry>();

        dataSet.add(new PieEntry(10f, "Bills"));
        dataSet.add(new PieEntry(20f, "Food"));
        dataSet.add(new PieEntry(30f, "Car"));
        dataSet.add(new PieEntry(10f, "Food"));
        dataSet.add(new PieEntry(15f, "Eating out"));
        dataSet.add(new PieEntry(15f, "Entertainment"));

        return dataSet;
    }
}