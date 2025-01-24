package pe.edu.utp.conexify.validation;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@RequestScoped
public class FormRegisterValidation implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FormRegisterValidation.class.getName());

    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String numberPhone;
    private String email;
    private String password1;
    private String password2;
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

    public void register() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User registered successfully"));
        LOGGER.info("Registering user " + name + " " + paternalSurname + " " + maternalSurname + " with email " + email);
    }
}
