package org.vaadin.addons.gwtcharts;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.vaadin.addons.gwtcharts.VaadinCharts")
public class TestUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        VCandlestickChart vCandlestickChart = new VCandlestickChart();
        layout.addComponent(vCandlestickChart);
        vCandlestickChart.setWidth(200, Unit.PIXELS);
        vCandlestickChart.setHeight(200, Unit.PIXELS);



        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));

            String s = "[['a',20,28,38,45],['b',31,38,55,66],['c',50,55,77,80],['d',77,77,66,50],['e',68,66,22,15]]";
            vCandlestickChart.setData(s);
        });
        
        layout.addComponents(name, button);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "TestUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TestUI.class, productionMode = false)
    public static class TestUIServlet extends VaadinServlet {
    }
}
