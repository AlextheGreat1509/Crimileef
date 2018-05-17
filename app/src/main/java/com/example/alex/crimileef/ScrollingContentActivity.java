package com.example.alex.crimileef;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ScrollingContentActivity extends AppCompatActivity {

    PieChart sportChart;
    PieChart crimeChart;
    HorizontalBarChart comparisonChart;
    XAxis xaxis;

    TextView textSportChart;
    TextView textCrimeChart;
    TextView textComparisonChart;
    TextView textDescSport;
    TextView textDescCrime;
    TextView textDescComparison;
    TextView textCharts;
    TextView textConclusion;
    TextView textDescConclusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("City Data");

        sportChart = (PieChart) findViewById(R.id.SportChart);
        crimeChart = (PieChart) findViewById(R.id.CrimeChart);
        comparisonChart = (HorizontalBarChart) findViewById(R.id.ComparisonChart); 
        textSportChart = (TextView) findViewById(R.id.textSportChart);
        textSportChart.setText("Sport Chart Eindhoven");
        textCrimeChart = (TextView) findViewById(R.id.textCrimeChart);
        textCrimeChart.setText("Crime Chart Eindhoven");
        textComparisonChart = (TextView) findViewById(R.id.textComparisonChart);
        textComparisonChart.setText("Relation between crime and sport in Eindhoven");
        textDescSport = (TextView) findViewById(R.id.textFillerSportChart);
        textDescCrime = (TextView) findViewById(R.id.textFillerCrimeChart);
        textDescComparison = (TextView) findViewById(R.id.textFillerComparisonChart);
        textCharts = (TextView) findViewById(R.id.textCharts);
        textConclusion = (TextView) findViewById(R.id.textConclusion);
        textDescConclusion = (TextView) findViewById(R.id.textFillerConclusion);

        List<PieEntry> sportEntries = new ArrayList<PieEntry>();

        sportEntries.add(new PieEntry(60, "Gestel"));
        sportEntries.add(new PieEntry(67, "Strijp"));
        sportEntries.add(new PieEntry(67, "Centrum"));
        sportEntries.add(new PieEntry(68, "Stratum"));
        sportEntries.add(new PieEntry(62, "Woensel-Zuid"));
        sportEntries.add(new PieEntry(61, "Woensel-Noord"));
        sportEntries.add(new PieEntry(62, "Tongelre"));

        PieDataSet sportDataSet = new PieDataSet(sportEntries, ""); // add entries to dataset
        sportDataSet.setColors(new int[]{Color.rgb(192, 0, 0), Color.rgb(255, 0, 0), Color.rgb(255, 192, 0),
                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189)});
        sportDataSet.setValueTextColor(2); // styling, ...

        PieData sportPieData = new PieData(sportDataSet);
        sportChart.setData(sportPieData);
        sportChart.animateY(2000);
        Legend sportLegend = sportChart.getLegend();
        sportLegend.setEnabled(false);

        sportChart.setDescription(null);
        sportChart.setClickable(true);
        sportChart.setBackgroundColor(Color.TRANSPARENT);
        sportChart.setHoleColor(Color.TRANSPARENT);
        sportChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry temp = (PieEntry) e;
                float value = temp.getY();
                textSportChart.setText("Amount of people sporting atleast once per week in " + temp.getLabel() + ":" + Float.toString(value) + "%");
                SelectBackground(temp.getLabel());
                popup(Float.toString(value));
            }

            @Override
            public void onNothingSelected() {

            }
        });

        sportChart.invalidate(); // refresh

        List<PieEntry> crimeEntries = new ArrayList<PieEntry>();

        crimeEntries.add(new PieEntry(16.3f, "Gestel"));
        crimeEntries.add(new PieEntry(7.9f, "Strijp"));
        crimeEntries.add(new PieEntry(17.3f, "Centrum"));
        crimeEntries.add(new PieEntry(17.1f, "Stratum"));
        crimeEntries.add(new PieEntry(15.7f, "Woensel-Zuid"));
        crimeEntries.add(new PieEntry(7.5f, "Woensel-Noord"));
        crimeEntries.add(new PieEntry(11.3f, "Tongelre"));

        PieDataSet crimeDataSet = new PieDataSet(crimeEntries, ""); // add entries to dataset
        crimeDataSet.setColors(new int[]{Color.rgb(192, 0, 0), Color.rgb(255, 0, 0), Color.rgb(255, 192, 0),
                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189)});
        crimeDataSet.setValueTextColor(2); // styling, ...

        PieData CrimePieData = new PieData(crimeDataSet);
        crimeChart.setData(CrimePieData);

        Legend crimeLegend = crimeChart.getLegend();
        crimeLegend.setEnabled(false);

        crimeChart.setClickable(true);
        crimeChart.setDescription(null);
        crimeChart.setBackgroundColor(Color.TRANSPARENT);
        crimeChart.setHoleColor(Color.TRANSPARENT);
        crimeChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry temp = (PieEntry) e;
                float value = temp.getValue();
                textCrimeChart.setText("Amount of relative disturbances in " + temp.getLabel() +":" + Float.toString(value));
                SelectBackground(temp.getLabel());
                popup(Float.toString(value));
            }

            @Override
            public void onNothingSelected() {

            }
        });

        crimeChart.invalidate(); // refresh

        List<BarEntry> comparisonEntriesSport = new ArrayList<BarEntry>();

        comparisonEntriesSport.add(new BarEntry(0,60f, "Gestel"));
        comparisonEntriesSport.add(new BarEntry(1,67f, "Strijp"));
        comparisonEntriesSport.add(new BarEntry(2,67f, "Centrum"));
        comparisonEntriesSport.add(new BarEntry(3,68f, "Stratum"));
        comparisonEntriesSport.add(new BarEntry(4,62f, "Woensel-Zuid"));
        comparisonEntriesSport.add(new BarEntry(5,61f, "Woensel-Noord"));
        comparisonEntriesSport.add(new BarEntry(6,62f, "Tongelre"));

        BarDataSet comparisonDataSetSport = new BarDataSet(comparisonEntriesSport,""); // add entries to dataset
        comparisonDataSetSport.setColors(new int[]{Color.rgb(192, 0, 0), Color.rgb(255, 0, 0), Color.rgb(255, 192, 0),
                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189)});
        comparisonDataSetSport.setValueTextColor(2); // styling, ...
        //comparisonDataSetSport.setStackLabels(getXAxisValues().toArray(new String[0]));
        comparisonDataSetSport.setDrawValues(true);

        List<BarEntry> comparisonEntriesCrime = new ArrayList<BarEntry>();

        comparisonEntriesCrime.add(new BarEntry(0,11.3f, "Gestel"));
        comparisonEntriesCrime.add(new BarEntry(1,7.5f, "Strijp"));
        comparisonEntriesCrime.add(new BarEntry(2,16.3f, "Centrum"));
        comparisonEntriesCrime.add(new BarEntry(3,7.9f, "Stratum"));
        comparisonEntriesCrime.add(new BarEntry(4,17.1f, "Woensel-Zuid"));
        comparisonEntriesCrime.add(new BarEntry(5,15.7f, "Woensel-Noord"));
        comparisonEntriesCrime.add(new BarEntry(6,17.3f, "Tongelre"));

        final BarDataSet comparisonDataSetCrime = new BarDataSet(comparisonEntriesCrime,""); // add entries to dataset

        comparisonDataSetCrime.setColors(new int[]{Color.rgb(192, 0, 0), Color.rgb(255, 0, 0), Color.rgb(255, 192, 0),
                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189)});
        comparisonDataSetCrime.setValueTextColor(2); // styling, ...
        //comparisonDataSetCrime.setStackLabels(getXAxisValues().toArray(new String[0]));
        comparisonDataSetCrime.setDrawValues(true);

        BarData comparisonData = new BarData(comparisonDataSetCrime,comparisonDataSetSport);
        comparisonData.setBarWidth(0.45f);
        comparisonData.setDrawValues(true);

        xaxis = comparisonChart.getXAxis();
        xaxis.setDrawGridLines(false);
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularityEnabled(true);
        xaxis.setGranularity(1);
        xaxis.setDrawLabels(true);
        xaxis.setXOffset(10);
        xaxis.setDrawAxisLine(false);
        CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(getXAxisValues().toArray(new String[0]));
        xaxis.setValueFormatter(xaxisFormatter);

        YAxis yl = comparisonChart.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);

        YAxis yr = comparisonChart.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        yr.setEnabled(false);
        yr.setDrawLabels(false);

        Legend legend = comparisonChart.getLegend();
        legend.setEnabled(false);

        comparisonChart.setFitBars(false);
        comparisonChart.setBackgroundColor(Color.TRANSPARENT);
        comparisonChart.getAxisLeft().setAxisMaximum(68);
        comparisonChart.getAxisLeft().setAxisMinimum(5);
        comparisonChart.setData(comparisonData);
        comparisonChart.setDescription(null);
        comparisonChart.groupBars(-0.46f,0.06f,0.02f);
        comparisonChart.setClickable(true);
        comparisonChart.setDrawValueAboveBar(true);
        comparisonChart.setPinchZoom(false);
        comparisonChart.setMaxVisibleValueCount(10000);
        comparisonChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                BarEntry temp = (BarEntry) e;
                float value = temp.getY();
                String neighborhood = (String) temp.getData();
                SelectBackground(neighborhood);
                textComparisonChart.setText("Value: " + value);
            }

            @Override
            public void onNothingSelected() {

            }
        });

        comparisonChart.invalidate(); // refresh
        
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Gestel");
        xAxis.add("Strijp");
        xAxis.add("Centrum");
        xAxis.add("Stratum");
        xAxis.add("Woensel-Zuid");
        xAxis.add("Woensel-Noord");
        xAxis.add("Tongelre");
        return xAxis;
    }

    public class CategoryBarChartXaxisFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public CategoryBarChartXaxisFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {

            int val = (int)value;
            String label="";
            if(val>=0 && val<mValues.length) {
                label= mValues[val];
            }
            else {
                label= "";
            }
            return label;
        }
    }

    public void popup(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void ChangeBackground(int drawable){
        View someView = findViewById(R.id.textCharts);
        View root = someView.getRootView();
        root.setBackgroundResource(drawable);
    }

    public void SelectBackground(String neighborhood){
        switch (neighborhood) {

            case "Gestel":
                ChangeBackground(R.drawable.gestel);
                setTextForBackground();
                break;

            case "Strijp":
                ChangeBackground(R.drawable.strijp);
                setTextForBackground();
                break;

            case "Centrum":
                ChangeBackground(R.drawable.centrum);
                setTextForBackground();
                break;

            case "Stratum":
                ChangeBackground(R.drawable.stratum);
                setTextForBackground();
                break;

            case "Woensel-Zuid":
                ChangeBackground(R.drawable.woensel);
                setTextForBackground();
                break;

            case "Woensel-Noord":
                ChangeBackground(R.drawable.woensel);
                setTextForBackground();
                break;

            case "Tongelre":
                ChangeBackground(R.drawable.tongelre);
                setTextForBackground();
                break;
        }
    }

    public void setTextForBackground(){
        textCharts.setTextColor(Color.WHITE);
        textCharts.setShadowLayer(50,0,0, Color.BLACK);
        textCrimeChart.setTextColor(Color.WHITE);
        textCrimeChart.setShadowLayer(50,0,0, Color.BLACK);
        textSportChart.setTextColor(Color.WHITE);
        textSportChart.setShadowLayer(50,0,0, Color.BLACK);
        textComparisonChart.setTextColor(Color.WHITE);
        textComparisonChart.setShadowLayer(50,0,0, Color.BLACK);
        textDescSport.setTextColor(Color.rgb(224, 249, 218));
        textDescSport.setShadowLayer(50,0,0, Color.BLACK);
        textDescCrime.setTextColor(Color.rgb(224, 249, 218));
        textDescCrime.setShadowLayer(50,0,0, Color.BLACK);
        textDescComparison.setTextColor(Color.rgb(224, 249, 218));
        textDescComparison.setShadowLayer(50,0,0, Color.BLACK);
        xaxis.setTextColor(Color.WHITE);
        xaxis.setTextSize(12);
        textConclusion.setTextColor(Color.WHITE);
        textConclusion.setShadowLayer(50,0,0, Color.BLACK);
        textDescConclusion.setTextColor(Color.rgb(224, 249, 218));
        textDescConclusion.setShadowLayer(50,0,0, Color.BLACK);
        comparisonChart.invalidate();
    }

}




