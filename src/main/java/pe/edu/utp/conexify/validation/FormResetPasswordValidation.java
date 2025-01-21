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
    private String codeVerification;
    private String oldPassword;
    private String newPassword;

    private boolean emailValidated = false;
    private boolean codeValidated = true;
    private boolean resendDisabled = false;
    private boolean showChangePassword = false;
    private boolean resendCodeDisabled = true;

    private int resendCountdown = 60;

    @Inject
    public FormResetPasswordValidation() {
        this.emailSenderService = new EmailSenderServiceImpl();
    }

    @SneakyThrows
    public void validateEmail() {
        if (email == null || email.isEmpty()) return;
        emailSenderService.sendEmail(this.email, "Juan Romero", "12A456");
        this.codeValidated = false;
        this.resendDisabled = true;
        this.emailValidated = true;
    }

    public void validateCodeVerification() {
        if (codeVerification == null || codeVerification.isEmpty()) return;
        this.codeValidated = true;
        this.resendDisabled = false;
        this.showChangePassword = true;
    }

    public void startResendCountdown() {
        resendCountdown--;
        if (resendCountdown == 0) {
            this.resendCodeDisabled = false;
            this.resendCountdown = 60;
        }
    }

}
