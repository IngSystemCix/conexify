package pe.utp.edu.conexify.util;

import org.junit.jupiter.api.Test;
import pe.edu.utp.conexify.util.GeneratorCode;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorCodeTest {

    @Test
    void generateCodeShouldReturnCodeOfCorrectLength() {
        String code = GeneratorCode.generateCode();
        assertEquals(6, code.length());
    }

    @Test
    void generateCodeShouldReturnUniqueCodes() {
        String code1 = GeneratorCode.generateCode();
        String code2 = GeneratorCode.generateCode();
        assertNotEquals(code1, code2);
    }
}