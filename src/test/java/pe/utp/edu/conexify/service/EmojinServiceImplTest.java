package pe.utp.edu.conexify.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.conexify.service.EmojinService;
import pe.edu.utp.conexify.service.EmojinServiceImpl;

class EmojinServiceImplTest {

    private EmojinService emojinService;

    @BeforeEach
    public void setUp() {
        emojinService = new EmojinServiceImpl();
    }

    @Test
    public void getAllEmojin() {
        emojinService.getAllEmojis("flags");
        // imprime el resultado
        System.out.println(emojinService.getAllEmojis("flags"));
    }
}