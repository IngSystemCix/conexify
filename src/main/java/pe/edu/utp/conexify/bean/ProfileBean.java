package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.util.Bundle;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Named
@ViewScoped
public class ProfileBean implements Serializable {
    private String bio;
    private Long amountFriends;
    private String city;
    private String email;
    private String phone;
    private String website;
    private String birthday;
    private String profession;
    private boolean verified = true;

    @PostConstruct
    public void init() {
        bio = "I'm a software engineer who loves to code and learn new things.";
        amountFriends = 10000L;
        city = "Lambayeque, Peru";
        email = "juanromerocollazos@gmail.com";
        phone = "+51 900 0002 602";
        birthday = "05/03/2002";
        website = "";
        profession = "Software Engineer";
    }

    public String converterAmountFriends(Long amountFriends) {
        int friends = amountFriends.intValue();

        if (friends > 1_000_000) {
            return "1M " + Bundle.getAttributeI18N("label_of_friends");
        } else if (friends > 100_000) {
            return "100k " + Bundle.getAttributeI18N("label_of_friends");
        } else if (friends > 10_000) {
            return "10k " + Bundle.getAttributeI18N("label_of_friends");
        } else if (friends > 1_000) {
            return "1k " + Bundle.getAttributeI18N("label_of_friends");
        } else {
            return amountFriends + " " + Bundle.getAttributeI18N("label_of_friends");
        }
    }

    public String generatorCodeUnique() {
        return UUID.randomUUID().toString();
    }
}
