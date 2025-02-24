package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import pe.edu.utp.conexify.dto.PeopleDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class RequestsPeopleBean implements Serializable {
    private List<PeopleDTO> requests;

    @PostConstruct
    public void init() {
        requests = new ArrayList<>(Arrays.asList(
                new PeopleDTO("John Doe", new ArrayList<>(Arrays.asList(
                        new PeopleDTO("Jane Doe", new ArrayList<>()),
                        new PeopleDTO("Alice Doe", new ArrayList<>()),
                        new PeopleDTO("Bob Doe", new ArrayList<>())
                ))),
                new PeopleDTO("Jane Doe", new ArrayList<>(Arrays.asList(
                        new PeopleDTO("John Doe", new ArrayList<>()),
                        new PeopleDTO("Alice Doe", new ArrayList<>()),
                        new PeopleDTO("Bob Doe", new ArrayList<>())
                ))),
                new PeopleDTO("Alice Doe", new ArrayList<>(Arrays.asList(
                        new PeopleDTO("John Doe", new ArrayList<>()),
                        new PeopleDTO("Jane Doe", new ArrayList<>()),
                        new PeopleDTO("Bob Doe", new ArrayList<>())
                )))
        ));
    }

    public List<PeopleDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<PeopleDTO> requests) {
        this.requests = requests;
    }
}
