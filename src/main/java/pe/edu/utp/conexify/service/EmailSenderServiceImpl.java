package pe.edu.utp.conexify.service;

import jakarta.enterprise.context.Dependent;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.Serializable;
import java.time.Year;

import static pe.edu.utp.conexify.config.EnvironmentVariables.*;

@Dependent
public class EmailSenderServiceImpl implements EmailSenderService, Serializable {
    @Override
    public void sendEmail(String recipientEmail, String name, String code) throws EmailException {
        HtmlEmail email = new HtmlEmail();

        email.setHostName(HOST_NAME);
        email.setSmtpPort(SMTP_PORT);
        email.setStartTLSEnabled(true);
        email.setAuthentication(EMAIL, APP_CODE);
        email.setContentType("text/html; charset=UTF-8");
        email.setCharset("UTF-8");
        email.setFrom(EMAIL);
        email.addTo(recipientEmail);
        email.setSubject("Código de verificación");

        String htmlMessage = createHtmlMessage(name, code);
        email.setMsg(htmlMessage);
        email.send();
    }

    @Override
    public String createHtmlMessage(String name, String code) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <title>Conexify - Código de Verificación</title>
              <style>
                /* Reset de estilos */
                body {
                  margin: 0;
                  padding: 0;
                  background-color: #f8f9fa;
                  font-family: 'Roboto', Arial, sans-serif;
                }
                table {
                  border-spacing: 0;
                  width: 100%%;
                }
                td {
                  padding: 0;
                }
                img {
                  border: 0;
                  display: block;
                }
    
                /* Contenedor del email */
                .email-container {
                  width: 100%%;
                  max-width: 600px;
                  margin: 0 auto; /* Centrado horizontal */
                  background-color: #ffffff;
                  border-radius: 10px;
                  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                  overflow: hidden;
                }
    
                /* Estilos para el contenido */
                .email-header {
                  background-color: #0d6efd;
                  color: #ffffff;
                  text-align: center;
                  padding: 20px;
                  font-family: 'Roboto', Arial, sans-serif;
                }
    
                .email-header h1 {
                  margin: 0;
                  font-size: 1.8rem;
                  line-height: 1.2;
                }
    
                .email-body {
                  padding: 20px 30px;
                  color: #212529;
                  line-height: 1.5;
                }
    
                .email-body p {
                  margin: 10px 0;
                }
    
                .verification-code {
                  display: block;
                  background-color: #0d6efd;
                  color: #ffffff;
                  font-size: 1.5rem;
                  font-weight: bold;
                  padding: 10px 15px;
                  border-radius: 5px;
                  text-align: center;
                  margin: 20px auto;
                  max-width: 200px;
                }
    
                .email-footer {
                  text-align: center;
                  padding: 15px;
                  font-size: 0.85rem;
                  color: #6c757d;
                  background-color: #f1f1f1;
                }
              </style>
            </head>
            <body>
              <table role="presentation" class="email-container" align="center">
                <!-- Cabecera -->
                <tr>
                  <td class="email-header">
                    <h1>Conexify</h1>
                  </td>
                </tr>
                <!-- Contenido -->
                <tr>
                  <td class="email-body">
                    <p>Hola <strong>%s</strong>,</p>
                    <p>Recibimos una solicitud para recuperar tu cuenta en Conexify. Por favor, utiliza el siguiente código para completar el proceso de recuperación:</p>
                    <span class="verification-code">%s</span>
                    <p>Este código es válido por 10 minutos. Si no solicitaste este código, por favor ignora este correo.</p>
                  </td>
                </tr>
                <!-- Pie -->
                <tr>
                  <td class="email-footer">
                    <p>&copy; %d Conexify. Todos los derechos reservados.</p>
                  </td>
                </tr>
              </table>
            </body>
            </html>
        """.formatted(name, code, getYearNow());
    }

    private static int getYearNow() {
        return Year.now().getValue();
    }
}
