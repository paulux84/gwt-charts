package org.vaadin.addons.gwtcharts;

import com.vaadin.ui.AbstractComponent;
import org.vaadin.addons.gwtcharts.client.ui.ChartState;

/**
 * Created by willtemperley@gmail.com on 17-May-17.
 */
public class VCandlestickChart extends AbstractComponent {

    @Override
    protected ChartState getState() {
        return (ChartState) super.getState();
    }

    public void setData(String data) {
        getState().data = data;
        markAsDirty();
    }
}
