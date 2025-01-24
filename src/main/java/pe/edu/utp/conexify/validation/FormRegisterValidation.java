package pe.edu.utp.conexify.validation;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
@Named
@ViewScoped
public class FormRegisterValidation implements Serializable {
    private Date birthDay;
    private List<Date> validDates;

    @PostConstruct
    public void init() {
        Date today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
        validDates = new ArrayList<>();
        validDates.add(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, -100);
        Date thirteenYearsAgo = calendar.getTime();
        for (long time = thirteenYearsAgo.getTime(); time <= today.getTime(); time += oneDay) {
            validDates.add(new Date(time));
        }
    }
}
