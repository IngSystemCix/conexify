package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.util.Bundle;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.options.LineOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.elements.Fill;

import java.awt.*;
import java.io.Serializable;

@Getter
@Setter
@Named
@RequestScoped
public class DashboardBean implements Serializable {
    private String amountUsers;
    private String deviceUsage;
    private String timeUsage;
    private String userDemographics;
    private String chartModelAmountUsers;
    private String chartModelDeviceUsage;
    private String chartModelTimeUsage;
    private String chartModelUserDemographics;

    @PostConstruct
    public void init() {
        amountUsers = converterAmountUsers(1200L);
        deviceUsage = "Mobile";
        timeUsage = Bundle.getAttributeI18N("label_morning");
        userDemographics = "<18";
        createChartLinealAmountUser();
        createChartLinealDeviceUsage();
        createChartLinealTimeUsage();
        createChartLinealUserDemographics();
    }

    private String converterAmountUsers(Long amountUsers) {
        if (amountUsers < 1000) {
            return amountUsers + "";
        } else if (amountUsers < 1000000) {
            return amountUsers / 1000 + "K";
        } else if (amountUsers < 1000000000) {
            return amountUsers / 1000000 + "M";
        } else {
            return amountUsers / 1000000000 + "B";
        }
    }

    public void createChartLinealAmountUser() {
        chartModelAmountUsers = new LineChart().setData(
                new LineData()
                        .addDataset(
                                new LineDataset()
                                        .setData(100, 200, 300, 400, 500, 600, 700)
                                        .setLabel(Bundle.getAttributeI18N("label_amount_users"))
                                        .setBorderColor("#2196F3")
                                        .setBackgroundColor("#4FC3F7")
                                        .setLineTension(0.1f)
                                        .setFill(new Fill<Boolean>(true))
                        )
                        .setLabels(Bundle.getAttributeI18N("label_january"), Bundle.getAttributeI18N("label_february"), Bundle.getAttributeI18N("label_march"), Bundle.getAttributeI18N("label_april"), Bundle.getAttributeI18N("label_may"), Bundle.getAttributeI18N("label_june"), Bundle.getAttributeI18N("label_july"), Bundle.getAttributeI18N("label_august"), Bundle.getAttributeI18N("label_september"), Bundle.getAttributeI18N("label_october"), Bundle.getAttributeI18N("label_november"), Bundle.getAttributeI18N("label_december"))
        ).setOptions(
                new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(
                                new Plugins()
                                        .setTitle(
                                                new Title()
                                                        .setDisplay(true)
                                                        .setText(Bundle.getAttributeI18N("label_amount_users"))
                                        )
                        )
        ).toJson();
    }

    public void createChartLinealDeviceUsage() {
        chartModelDeviceUsage = new LineChart().setData(
                new LineData()
                        .addDataset(
                                new LineDataset()
                                        .setData(510, 190, 300)
                                        .setLabel(Bundle.getAttributeI18N("label_device_usage"))
                                        .setBorderColor("#335B35")
                                        .setBackgroundColor("#84D0C9")
                                        .setLineTension(0.1f)
                                        .setFill(new Fill<Boolean>(true))
                        )
                        .setLabels("Mobile", "Tablet", "Desktop")
        ).setOptions(
                new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(
                                new Plugins()
                                        .setTitle(
                                                new Title()
                                                        .setDisplay(true)
                                                        .setText(Bundle.getAttributeI18N("label_amount_users"))
                                        )
                        )
        ).toJson();
    }

    public void createChartLinealTimeUsage() {
        chartModelTimeUsage = new LineChart().setData(
                new LineData()
                        .addDataset(
                                new LineDataset()
                                        .setData(510, 190, 300)
                                        .setLabel(Bundle.getAttributeI18N("label_time_usage"))
                                        .setBorderColor("#4C2C5E")
                                        .setBackgroundColor("#B388FF")
                                        .setLineTension(0.1f)
                                        .setFill(new Fill<Boolean>(true))
                        )
                        .setLabels(Bundle.getAttributeI18N("label_morning"), Bundle.getAttributeI18N("label_afternoon"), Bundle.getAttributeI18N("label_night"))
        ).setOptions(
                new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(
                                new Plugins()
                                        .setTitle(
                                                new Title()
                                                        .setDisplay(true)
                                                        .setText(Bundle.getAttributeI18N("label_amount_users"))
                                        )
                        )
        ).toJson();
    }

    public void createChartLinealUserDemographics() {
        chartModelUserDemographics = new LineChart().setData(
                new LineData()
                        .addDataset(
                                new LineDataset()
                                        .setData(510, 190, 300, 200, 400, 300, 100)
                                        .setLabel(Bundle.getAttributeI18N("label_user_demographics"))
                                        .setBorderColor("#FF5370")
                                        .setBackgroundColor("#D57987")
                                        .setLineTension(0.1f)
                                        .setFill(new Fill<Boolean>(true))
                        )
                        .setLabels("<18", "18-24", "25-34", "35-44", "45-54", "55-64", "65+")
        ).setOptions(
                new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(
                                new Plugins()
                                        .setTitle(
                                                new Title()
                                                        .setDisplay(true)
                                                        .setText(Bundle.getAttributeI18N("label_amount_users"))
                                        )
                        )
        ).toJson();
    }
}
