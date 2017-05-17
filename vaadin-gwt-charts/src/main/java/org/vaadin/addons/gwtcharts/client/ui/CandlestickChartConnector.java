package org.vaadin.addons.gwtcharts.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.corechart.CandlestickChart;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.addons.gwtcharts.VCandlestickChart;

import java.util.List;

/**
 * Created by willtemperley@gmail.com on 17-May-17.
 */
@Connect(VCandlestickChart.class)
public class CandlestickChartConnector extends AbstractComponentConnector {

    @Override
    public ChartState getState() {
        return (ChartState) super.getState();
    }

    @Override
    public GCandlestickChart getWidget() {
        return (GCandlestickChart) super.getWidget();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(GCandlestickChart.class);
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        ChartState state = getState();
        getWidget().setData(state.data);
    }
}
