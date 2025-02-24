package pe.edu.utp.conexify.dto;

import java.util.List;

public class PeopleDTO {
    private String fullName;
    private List<PeopleDTO> mutualFriends;

    public PeopleDTO(String fullName, List<PeopleDTO> mutualFriends) {
        this.fullName = fullName;
        this.mutualFriends = mutualFriends;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<PeopleDTO> getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(List<PeopleDTO> mutualFriends) {
        this.mutualFriends = mutualFriends;
    }
}
