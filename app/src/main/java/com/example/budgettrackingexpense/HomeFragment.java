package com.example.budgettrackingexpense;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.budgettrackingexpense.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    PieChart pieChart;

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