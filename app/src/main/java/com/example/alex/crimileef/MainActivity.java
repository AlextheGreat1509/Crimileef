package com.example.alex.crimileef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PieChart chart;
    TextView textSportChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chart = (PieChart) findViewById(R.id.chart);
        textSportChart = (TextView) findViewById(R.id.textSportChart);
        textSportChart.setText("Sport Chart");

        List<PieEntry> entries = new ArrayList<PieEntry>();

        entries.add(new PieEntry(1, "Voetbal"));
        entries.add(new PieEntry(2, "Volleybal"));
        entries.add(new PieEntry(3, "Tennis"));
        entries.add(new PieEntry(4, "Golf"));
        entries.add(new PieEntry(5, "Hockey"));
        entries.add(new PieEntry(6, "Basketbal"));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Voetbal");
        labels.add("Volleybal");
        labels.add("Tennis");
        labels.add("Golf");
        labels.add("Hockey");
        labels.add("Basketbal");

        PieDataSet dataSet = new PieDataSet(entries, "test"); // add entries to dataset
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(2); // styling, ...


        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.animateY(5000);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        chart.setClickable(true);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                float value = e.getY();
                textSportChart.setText("Sport Chart value:" + Float.toString(value));
                popup(Float.toString(value));
            }

            @Override
            public void onNothingSelected()
            {

            }
        });

        chart.invalidate(); // refresh
    }

    public void popup(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickBtn(View view) {

        setContentView(R.layout.activity_main);
    }

}
