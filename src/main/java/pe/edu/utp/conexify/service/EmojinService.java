package pe.edu.utp.conexify.service;


import java.util.HashMap;
import java.util.List;

public interface EmojinService {
    HashMap<String, List<String>> getAllEmojis(String category);
}
