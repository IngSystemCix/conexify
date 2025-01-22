package pe.edu.utp.conexify.validation;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pe.edu.utp.conexify.service.EmailSenderService;
import pe.edu.utp.conexify.service.EmailSenderServiceImpl;

import java.io.Serializable;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@ViewScoped
public class FormResetPasswordValidation implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FormResetPasswordValidation.class.getName());

    private final EmailSenderService emailSenderService;

    private String email;
    private String code;
    private Integer time = 30;

    private boolean emailValidated = false;
    private boolean codeDisabled = true;
    private boolean buttonCodeValidateDisable = true;
    private boolean renderedButtonResendPassword = false;
    private boolean buttonResendCodeDisable = true;
    private boolean pollStopped = false;

    @Inject
    public FormResetPasswordValidation() {
        this.emailSenderService = new EmailSenderServiceImpl();
    }

    @SneakyThrows
    public void sendEmail() {
        if (!validateEmail(email)) return;
        emailSenderService.sendEmail(email, "Juan Romero", "A1SDD2");
        LOGGER.info("Email sent to: " + email);
        emailValidated = true;
        codeDisabled = false;
        buttonCodeValidateDisable = false;
        renderedButtonResendPassword = true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public void countResendCode() {
        if (time > 0) {
            this.time--;
        }else {
            buttonResendCodeDisable = false;
            pollStopped = true;
        }
    }

    @SneakyThrows
    public void resendCode() {
        emailSenderService.sendEmail(email, "Juan Romero", "A1SDD2");
        LOGGER.info("Email sent to: " + email);
        time = 30;
        buttonResendCodeDisable = true;
        pollStopped = false;
    }

}
