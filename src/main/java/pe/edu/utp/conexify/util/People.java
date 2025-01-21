package pe.edu.utp.conexify.util;

import java.util.List;

public class People {
    private String fullName;
    private List<People> mutualFriends;

    public People(String fullName, List<People> mutualFriends) {
        this.fullName = fullName;
        this.mutualFriends = mutualFriends;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<People> getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(List<People> mutualFriends) {
        this.mutualFriends = mutualFriends;
    }
}
