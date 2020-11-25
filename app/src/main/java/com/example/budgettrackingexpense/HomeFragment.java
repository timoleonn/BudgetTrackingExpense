package com.example.budgettrackingexpense;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {

    PieChart pieChart;

    int categoryCount = 0;
    double[] expensePerCategory;
    double totalExpenses = 0;
    double totalIncome = 0;
    String expense;
    String file_name = "expenses.txt";
    String income_file_name = "income.txt";
    String filename ="total.txt";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //  SET FRAGMENT TITLE
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Dashboard");

        expense ="";
        TextView tvTotalExpenses = root.findViewById(R.id.tvTotalExpenses);
        TextView tvTotalIncome = root.findViewById(R.id.tvTotalIncome);

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
                Intent in = new Intent(getContext(), addExpenses.class);
                startActivity(in);
            }
        });

//        Button btnTestimgTimRead = root.findViewById(R.id.btnTestimgTimRead);



//        //  READ FILE
//        btnTestimgTimRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                //  READ FILE, SPLIT EACH VARIABLE THAT IS SEPARATED WITH A COMMA
//                //  AND SAVE IT TO AN ARRAY LIST OF LISTS
//                try {
//                    FileInputStream fin = getActivity().openFileInput(file_name);
//                    DataInputStream din = new DataInputStream(fin);
//                    InputStreamReader isr = new InputStreamReader(din);
//                    BufferedReader br = new BufferedReader(isr);
//
//                    //  CREATE ARRAY LIST, LIST AND VARIABLE TO HOLD EACH LINE
//                    ArrayList<List> finalArrayList = new ArrayList<>();
//                    List<String> strList = new ArrayList<String>();
//                    String strLine;
//
//                    //  READ EVERY LINE
//                    //  SPLIT EACH LINE BASED ON THE COMMA (SEPARATOR)
//                    //  EACH VALUE IS ADDED IN A LIST
//                    //  LIST GETS ADDED TO ARRAY LIST
//                    //  LIST GETS INITIALIZED AGAIN
//                    //  REPEAT UNTIL TEXT FILE IS READ THROUGH
//                    while((strLine = br.readLine()) != null) {
//                        String[] res = strLine.split("[,]", 0);
//                        System.out.println(res);
//                        for(String myStr: res) {
//                            System.out.println(myStr);
//                            //  ADDS THE THREE VARIABLES TO THE STRING ARRAY
//                            strList.add(myStr);
//                            System.out.println(strList);
//                        }
//                        System.out.println("1.strList BEFORE: " + strList);
//                        finalArrayList.add(strList);
//                        System.out.println("2. finalArrayList BEFORE: " + finalArrayList);
//                        //  INITIALIZE AGAIN THE STRLIST SO IT CAN GET NEW VALUES
//                        strList = new ArrayList<String>();
//
//                        System.out.println("1.strList AFTER: " + strList);
//                        System.out.println("2. finalArrayList AFTER: " + finalArrayList);
//                    }
//                    System.out.println("\nFINAL: " + finalArrayList);
//
//                    //  SOME LOCAL VARIABLES
//                    Double total = 0.0;
//
//                    //  ITERATE THROUGH EACH LIST IN THE ARRAY LIST
//                    //  TO GRAB THE VALUES AND PROCESS THEM
//                    for (int i = 0; i < finalArrayList.size(); i++) {
////                        System.out.println(finalArrayList.get(i) + " ");
//                        for (int j = 0; j < finalArrayList.get(i).size(); j++) {
//                            System.out.println(finalArrayList.get(i).get(j) + " ");
//                            //  PARSE TO DOUBLE THE AMOUNT
//                            if (j == 2) {
//                                total += Double.parseDouble((String) finalArrayList.get(i).get(j));
//                            }
//
//                        }
//                    }
//
//                    System.out.println("TOTAL: " + total);
//
////                    System.out.println("3.Array List AFTER: " + finalArrayList);
//
//
//                    fin.close();
//                    Toast.makeText(getContext(), "Read successfully", Toast.LENGTH_LONG).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        //  PIE CHART
        pieChart = root.findViewById(R.id.pieChart);

        //  CREATE CUSTOM COLORS FOR THE PIE CHART
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

        //  CREATE ARRAY LIST OF STRINGS FOR CATEGORIES
        ArrayList<String> categories = new ArrayList();

        //  READ CATEGORIES FROM TEXT FILE THAT IS CREATED IN AddCategoryActivity
        //  ADD EACH CATEGORY TO THE ArrayList<String> categories
        //  COUNT HOW MANY CATEGORIES YOU HAVE (WILL NEED TO CREATE ARRAY)
        try {
            FileInputStream fin = getActivity().openFileInput("categories.txt");
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    categories.add(line);
                    categoryCount++;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }

        //  READ EXPENSES
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
                    //  ADDS THE FOUR VARIABLES TO THE STRING ARRAY
                    strList.add(myStr);
                    System.out.println(strList);
                }
                finalArrayList.add(strList);

                //  INITIALIZE AGAIN THE STRLIST SO IT CAN GET NEW VALUES
                strList = new ArrayList<String>();
            }

            //  INITIALIZED ABOVE
            //  THIS IS AN ARRAY TO HOLD THE TOTAL AMOUNT THAT USER SPENT FOR EACH CATEGORY
            expensePerCategory = new double[categoryCount];

            int category_count = 0;
            //  GET ALL CATEGORIES
            if (!finalArrayList.isEmpty()) {
                for (String category: categories) {
                    //  ITERATE THROUGH EACH LIST IN THE ARRAY LIST
                    //  TO GRAB THE VALUES AND PROCESS THEM
                    for (int i = 0; i < finalArrayList.size(); i++) {
                        for (int j = 0; j < finalArrayList.get(i).size(); j++) {
                            //  J == 1 MEANS THAT IF WE ARE CHECKING THE CATEGORY COLUMN IN THE LIST OF EXPENSES
                            if (j == 1) {
                                if (finalArrayList.get(i).get(j).equals(category)) {
                                    expensePerCategory[category_count] += Double.parseDouble((String) finalArrayList.get(i).get(3));
                                }
                            }
                            //  J == 3 MEANS THAT WE ARE CHECKING THE EXPENSE COLUMN IN THE LIST OF EXPENSES
                            if (j == 3) {
                                totalExpenses += Double.parseDouble((String) finalArrayList.get(i).get(3));
                                }

                        }
                    }
                    category_count++;
                }
            } else {
                expensePerCategory = null;
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }

        //  READ INCOME
        //  READ FILE, SPLIT EACH VARIABLE THAT IS SEPARATED WITH A COMMA
        //  AND SAVE IT TO AN ARRAY LIST OF LISTS
        try {
            FileInputStream fin = getActivity().openFileInput(income_file_name);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);

            //  CREATE ARRAY LIST, LIST AND VARIABLE TO HOLD EACH LINE
            ArrayList<List> incomeArrayList = new ArrayList<>();
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
                    //  ADDS THE FOUR VARIABLES TO THE STRING ARRAY
                    strList.add(myStr);
                    System.out.println(strList);
                }
                incomeArrayList.add(strList);

                //  INITIALIZE AGAIN THE STRLIST SO IT CAN GET NEW VALUES
                strList = new ArrayList<String>();
            }

            //  LOOP THROUGH THE INCOME ARRAY LIST SO WE CAN FIND TOTAL INCOME
            if (!incomeArrayList.isEmpty()) {
                for (int i = 0; i < incomeArrayList.size(); i++) {
                    for (int j = 0; j < incomeArrayList.get(i).size(); j++) {
                        //  J == 2 MEANS THAT IF WE ARE CHECKING THE INCOME COLUMN IN THE LIST OF EXPENSES
                        if (j == 2) {
                            totalIncome += Double.parseDouble((String) incomeArrayList.get(i).get(2));
                            expense = Double.toString(totalIncome);
//                            Create a bundle for moving the total income to expenses
                            Intent in = new Intent(getContext(), addExpenses.class);
                            startActivity(in);
                        }
                    }
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }

        //  SET TOTAL EXPENSES AND TOTAL INCOME TEXT
        tvTotalExpenses.setText(Double.toString(totalExpenses / 2));
        tvTotalIncome.setText(Double.toString(totalIncome));

        PieDataSet pieDataSet = new PieDataSet(pieChartDataSet(categories, expensePerCategory), "");
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
    private ArrayList<PieEntry> pieChartDataSet(ArrayList<String> categories, double[] array) {
        ArrayList<PieEntry> dataSet = new ArrayList<PieEntry>();

        //  CHECK IF THE ARRAY WITH THE EXPENSES IS NULL, IF NOT THEN IT MEANS WE HAVE EXPENSES
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                dataSet.add(new PieEntry((float) array[i], categories.get(i)));
            }
        }

        return dataSet;
    }

}