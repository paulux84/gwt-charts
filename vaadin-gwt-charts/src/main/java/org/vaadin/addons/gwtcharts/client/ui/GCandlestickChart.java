package org.vaadin.addons.gwtcharts.client.ui;

import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.CandlestickChart;

/**
 * Created by willtemperley@gmail.com on 17-May-17.
 */

import com.google.gwt.dom.client.Style.Unit;
import com.googlecode.gwt.charts.client.corechart.CandlestickChartOptions;
import com.googlecode.gwt.charts.client.options.BackgroundColor;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import com.googlecode.gwt.charts.client.util.ChartHelper;

public class GCandlestickChart extends DockLayoutPanel {

    private CandlestickChart chart;

    public GCandlestickChart() {
        super(Unit.PX);
        initialize();
    }

    private void initialize() {
        ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
        chartLoader.loadApi(new Runnable() {

            @Override
            public void run() {
                // Create and attach the chart
                chart = new CandlestickChart();
                add(chart);
                draw();
            }
        });
    }

    private void draw() {
        JsArrayMixed dataArray = JsonUtils
                .unsafeEval("[['Mon',20,28,38,45],['Tue',31,38,55,66],['Wed',50,55,77,80],['Thu',77,77,66,50],['Fri',68,66,22,15]]");

        // Prepare the data
        DataTable dataTable = ChartHelper.arrayToDataTable(dataArray, true);

        // Set options
        CandlestickChartOptions options = CandlestickChartOptions.create();
        options.setLegend(Legend.create(LegendPosition.NONE));
        options.setRisingColor(BackgroundColor.create("yellow", "orange", 2));
        options.setFallingColor(BackgroundColor.create("black", "red", 2));

        // Draw the chart
        chart.draw(dataTable, options);
    }

    public void setData(String data) {
        JsArrayMixed dataArray = JsonUtils
                .unsafeEval(data);
        DataTable dataTable = ChartHelper.arrayToDataTable(dataArray, true);

        // Set options

        // Draw the chart
        chart.draw(dataTable);
    }
}
