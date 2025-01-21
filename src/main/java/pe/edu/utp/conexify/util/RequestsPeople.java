package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class RequestsPeople implements Serializable {
    private List<People> requests;

    @PostConstruct
    public void init() {
        requests = new ArrayList<>(Arrays.asList(
                new People("John Doe", new ArrayList<>(Arrays.asList(
                        new People("Jane Doe", new ArrayList<>()),
                        new People("Alice Doe", new ArrayList<>()),
                        new People("Bob Doe", new ArrayList<>())
                ))),
                new People("Jane Doe", new ArrayList<>(Arrays.asList(
                        new People("John Doe", new ArrayList<>()),
                        new People("Alice Doe", new ArrayList<>()),
                        new People("Bob Doe", new ArrayList<>())
                ))),
                new People("Alice Doe", new ArrayList<>(Arrays.asList(
                        new People("John Doe", new ArrayList<>()),
                        new People("Jane Doe", new ArrayList<>()),
                        new People("Bob Doe", new ArrayList<>())
                )))
        ));
    }

    public List<People> getRequests() {
        return requests;
    }

    public void setRequests(List<People> requests) {
        this.requests = requests;
    }
}
