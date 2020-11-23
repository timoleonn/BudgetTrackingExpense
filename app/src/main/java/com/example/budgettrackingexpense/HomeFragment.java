package com.example.budgettrackingexpense;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    int categoryCount = 0;
    double[] expensePerCategory;

    String file_name = "expenses.txt";

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

//        List<Categories> categoriesList = new ArrayList();

//        //  GET CURRENT USER
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        //  GET CURRENT USER UID
//        String currentUserUid = currentUser.getUid();
//
//        //  FETCH
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid).child("categories");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot ds:snapshot.getChildren()) {
//                        Categories data = ds.getValue(Categories.class);
//                        categoriesList.add(data);
//                    }
//
//                } else {
//                    System.out.println("No data found");
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

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
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("CATEGORIES: IN ARRAY LIST OF STRINGS");
//        for (String category: categories) {
//            System.out.println("\nCAT: " + category);
//        }

        //  READ INCOME
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
//                System.out.println("1.strList BEFORE: " + strList);
                finalArrayList.add(strList);
//                System.out.println("2. finalArrayList BEFORE: " + finalArrayList);
                //  INITIALIZE AGAIN THE STRLIST SO IT CAN GET NEW VALUES
                strList = new ArrayList<String>();

//                System.out.println("1.strList AFTER: " + strList);
//                System.out.println("2. finalArrayList AFTER: " + finalArrayList);
            }
            System.out.println("\nFINAL: " + finalArrayList);

            //  SOME LOCAL VARIABLES
//            Double total = 0.0;

            //  INITIALIZED ABOVE
            //  THIS IS AN ARRAY TO HOLD THE TOTAL AMOUNT SPENDED FOR EACH CATEGORY
            expensePerCategory = new double[categoryCount];

            System.out.println("IS EMPTY: " + finalArrayList.isEmpty());

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
        } catch (IOException e) {
            e.printStackTrace();
        }


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

        if (array != null) {
            System.out.println("TIMOLEON 1");
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
                dataSet.add(new PieEntry((float) array[i], categories.get(i)));
            }
            return dataSet;
        }

        if (array == null) {
            System.out.println("SIZE: " + categories.size());
            double zero = 0.0;
            for (int i = 0; i < categories.size(); i++) {
                dataSet.add(new PieEntry((float) zero, categories.get(i)));
                System.out.println("NAME [" + i + "]: " + categories.get(i));
            }
        }



        return dataSet;
    }

}