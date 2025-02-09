package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.DialogFrameworkOptions;
import org.primefaces.model.map.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static pe.edu.utp.conexify.config.EnvironmentVariables.TOKEN_GCP_API_MAPS;

@Getter
@Setter
@EqualsAndHashCode
@Named
@SessionScoped
public class EditProfile implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EditProfile.class.getName());

    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String bio;
    private String email;
    private String phone;
    private Date birthDay;
    private String profession;
    private String website;
    private List<Date> validDates;
    private String location;
    private MapModel<Long> simpleModel;
    private String centerMap = "-12.0464,-77.0428";
    private String GCP_GMap = "https://maps.googleapis.com/maps/api/js?key=" + TOKEN_GCP_API_MAPS + "&libraries=places";

    public void editProfile(String name, String paternalSurname, String maternalSurname,
                            String bio, String email, String phone, String birthDay, String city, String profession, String website) throws ParseException {

        // Asignar los valores a las propiedades del bean
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.bio = bio;
        this.email = email;
        this.phone = phone;
        this.profession = profession;
        this.website = website;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.birthDay = dateFormat.parse(birthDay);  // Asignar la fecha formateada
        this.location = city;

        DialogFrameworkOptions options = DialogFrameworkOptions.builder()
                .modal(true)
                .resizable(false)
                .width("90%")
                .height("60%")
                .contentHeight("100%")
                .contentWidth("100%")
                .draggable(false)
                .responsive(true)
                .closeOnEscape(true)
                .build();

        PrimeFaces.current().dialog().openDynamic("editProfile", options, null);
    }

    public void saveProfile() {

    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        if (!results.isEmpty()) {
            LatLng latlng = results.get(0).getLatLng();
            centerMap = latlng.getLat() + "," + latlng.getLng();
            simpleModel.getMarkers().clear();
            simpleModel.addOverlay(new Marker<>(latlng, "Ubicación seleccionada"));
        }
    }

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel<>();
        LatLng coord = new LatLng(-12.0464, -77.0428);
        simpleModel.addOverlay(new Marker<>(coord, "Ubicación seleccionada"));
        Date today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
        validDates = new ArrayList<>();
        validDates.add(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, -100);
        Date thirteenYearsAgo = calendar.getTime();
        for (long time = thirteenYearsAgo.getTime(); time <= today.getTime(); time += oneDay) {
            validDates.add(new Date(time));
        }
    }

    public List<String> completeLocation(String query) {
        List<String> results = new ArrayList<>();
        String urlString = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
                + query + "&types=geocode&key=" + TOKEN_GCP_API_MAPS;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (Reader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                 JsonReader jsonReader = Json.createReader(reader)) {

                JsonObject jsonObject = jsonReader.readObject();
                JsonArray predictions = jsonObject.getJsonArray("predictions");

                for (int i = 0; i < predictions.size(); i++) {
                    JsonObject prediction = predictions.getJsonObject(i);
                    results.add(prediction.getString("description"));
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Error al obtener la lista de ubicaciones: " + e.getMessage());
        }

        return results;
    }
}
