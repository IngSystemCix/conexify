package pe.edu.utp.conexify.validation;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pe.edu.utp.conexify.service.EmailSenderService;
import pe.edu.utp.conexify.service.EmailSenderServiceImpl;
import pe.edu.utp.conexify.util.ControllerSession;
import pe.edu.utp.conexify.util.GeneratorCode;

import java.io.Serializable;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@ViewScoped
public class FormResetPasswordValidation implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FormResetPasswordValidation.class.getName());

    private final EmailSenderService emailSenderService;
    private final ControllerSession controllerSession;

    private String email;
    private String code;
    private Integer time = 30;
    private String oldPassword;
    private String newPassword;

    private boolean emailValidated = false;
    private boolean codeDisabled = true;
    private boolean buttonCodeValidateDisable = true;
    private boolean renderedButtonResendPassword = false;
    private boolean buttonResendCodeDisable = true;
    private boolean pollStopped = false;
    private boolean showMessage = false;
    private boolean closed = false;
    private boolean buttonOldPasswordDisable = false;
    private boolean oldPasswordDisabled = false;
    private boolean showChangePassword = false;
    private boolean newPasswordDisabled = true;
    private boolean showMessageNotMatchOldPassword = false;
    private boolean showMessageInfoChangePassword = false;

    @Inject
    public FormResetPasswordValidation() {
        this.emailSenderService = new EmailSenderServiceImpl();
        this.controllerSession = new ControllerSession();
    }

    @SneakyThrows
    public void sendEmail() {
        if (!validateEmail(email)) return;
        Object sendCode = GeneratorCode.generateCode();
        emailSenderService.sendEmail(email, "Juan Romero", sendCode.toString());
        controllerSession.saveSession("sendCode", sendCode);
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
        Object sendCode = GeneratorCode.generateCode();
        controllerSession.saveSession("sendCode", sendCode);
        emailSenderService.sendEmail(email, "Juan Romero", sendCode.toString());
        LOGGER.info("Email sent to: " + email);
        time = 30;
        buttonResendCodeDisable = true;
        pollStopped = false;
    }

    public void validateCode() {
        if (code.equals(controllerSession.getSession("sendCode"))) {
            closed = true;
            showMessage = false;
            codeDisabled = true;
            buttonCodeValidateDisable = true;
            renderedButtonResendPassword = false;
            showChangePassword = true;
            LOGGER.info("Code validated");
        } else {
            showChangePassword = false;
            closed = false;
            showMessage = true;
            LOGGER.info("Code not validated");
        }
    }

    public void onClose() {
        closed = true;
        showMessage = false;
        LOGGER.info("Closed dialog");
    }

    public void validateOldPassword() {
        if (oldPassword.equals("12345678")) {
            newPasswordDisabled = false;
            showMessageNotMatchOldPassword = false;
            codeDisabled = true;
            oldPasswordDisabled = true;
            buttonOldPasswordDisable = true;
            LOGGER.info("Old password validated");
        } else {
            newPasswordDisabled = true;
            closed = false;
            showMessageNotMatchOldPassword = true;
            oldPasswordDisabled = false;
            buttonOldPasswordDisable = false;
            LOGGER.info("Old password not validated");
        }
    }

    public void changePassword() {
        if (newPassword.isEmpty()) return;
        showMessageInfoChangePassword = true;
        showChangePassword = false;
        emailValidated = false;
        reset();
        controllerSession.invalidateSession();
        LOGGER.info("Password changed");
    }

    private void reset() {
        email = "";
        code = "";
        time = 30;
        oldPassword = "";
        newPassword = "";
        emailValidated = false;
        codeDisabled = true;
        buttonCodeValidateDisable = true;
        renderedButtonResendPassword = false;
        buttonResendCodeDisable = true;
        pollStopped = false;
        showMessage = false;
        closed = false;
        buttonOldPasswordDisable = false;
        oldPasswordDisabled = false;
        showChangePassword = false;
        newPasswordDisabled = true;
        showMessageNotMatchOldPassword = false;
        showMessageInfoChangePassword = false;
    }
}
