package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.util.LangUtils;
import pe.edu.utp.conexify.dto.QuestionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Named
@ViewScoped
public class HelpBean implements Serializable {
    private List<QuestionDTO> questions;
    private List<QuestionDTO> filteredQuestions;
    private QuestionDTO selectedQuestion;

    @PostConstruct
    public void init() {
        questions = new ArrayList<>();
        questions.add(new QuestionDTO("¿Cómo puedo registrarme en Conexify?", "Debes ir a la página de registro."));
        questions.add(new QuestionDTO("¿Cómo puedo iniciar sesión en Conexify?", "Ingresa tu usuario y contraseña."));
        questions.add(new QuestionDTO("¿Cómo puedo recuperar mi contraseña?", "Utiliza la opción 'Recuperar contraseña'."));
        questions.add(new QuestionDTO("¿Cómo puedo buscar a un usuario?", "Usa la barra de búsqueda."));
        questions.add(new QuestionDTO("¿Cómo puedo enviar un mensaje a un usuario?", "Haz clic en 'Enviar mensaje'."));
        questions.add(new QuestionDTO("¿Cómo puedo ver mis mensajes?", "Ve a la sección de mensajes."));
        questions.add(new QuestionDTO("¿Cómo puedo ver mis contactos?", "Accede a la lista de contactos."));
        questions.add(new QuestionDTO("¿Cómo puedo ver mi perfil?", "Haz clic en tu avatar."));
        questions.add(new QuestionDTO("¿Cómo puedo editar mi perfil?", "Entra a 'Configuración' y edita."));
        questions.add(new QuestionDTO("¿Cómo puedo cerrar sesión en Conexify?", "Haz clic en 'Cerrar sesión'."));
    }

    public boolean isQuestionFilter(Object value, Object filter, Locale filterLocale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase(filterLocale);
        if(LangUtils.isBlank(filterText)) {
            return true;
        }

        QuestionDTO question = (QuestionDTO) value;
        return question.getQuestion().toLowerCase(filterLocale).contains(filterText);
    }

    public void viewAnswer(QuestionDTO question) {
        this.selectedQuestion = question;
    }
}
