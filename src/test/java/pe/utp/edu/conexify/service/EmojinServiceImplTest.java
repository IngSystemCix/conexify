package pe.utp.edu.conexify.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.conexify.service.EmojinService;
import pe.edu.utp.conexify.service.EmojinServiceImpl;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmojinServiceImplTest {

    private EmojinService emojinService;

    @BeforeEach
    public void setUp() {
        emojinService = new EmojinServiceImpl();
    }

    @Test
    public void getAllEmojin() {
        emojinService.getAllEmojis("smileys-emotion");
        // imprime el resultado
        System.out.println(emojinService.getAllEmojis("smileys-emotion"));
    }
}