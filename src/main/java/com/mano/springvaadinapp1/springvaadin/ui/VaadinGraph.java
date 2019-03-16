package com.mano.springvaadinapp1.springvaadin.ui;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("chart")
public class VaadinGraph extends HorizontalLayout {

    // Create a chart of some primary type
    Chart chart = new Chart(ChartType.SCATTER);

    // Modify the default configuration a bit
    Configuration conf = chart.getConfiguration();
    //conf.setTitle("Average Temperatures in Turku");
   // conf.getLegend().setEnabled(false);


    public void setConf(Configuration conf) {
        this.conf = conf;
        conf.setTitle("Average Temperatures in Turku");
        conf.getLegend().setEnabled(false);
    }

    // The primary data series
    ListSeries averages = new ListSeries(-6, -6.5, -4, 3, 9, 14, 17, 16, 11, 6, 2, -2.5);

    // Error bar data series with low and high values
    DataSeries errors = new DataSeries();
    /*errors.add(new DataSeriesItem(0,  -9, -3));
    errors.add(new DataSeriesItem(1, -10, -3));
    errors.add(new DataSeriesItem(2,  -8,  1));*/

    public void setErrors(DataSeries errors) {
        errors.add(new DataSeriesItem(0,  -9, -3));
        errors.add(new DataSeriesItem(1,  -10, -3));
        errors.add(new DataSeriesItem(2,  -8, 1));
    }

    // Need to be used for series to be recognized as error bar
    PlotOptionsErrorbar barOptions = new PlotOptionsErrorbar();
    //errors.setPlotOptions(barOptions);


    // The errors should be drawn lower
    /*conf.addSeries(errors);
    conf.addSeries(averages);*/

}
