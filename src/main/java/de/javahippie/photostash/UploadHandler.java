package de.javahippie.photostash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author zoeller
 */
@Named("uploadHandler")
@RequestScoped
public class UploadHandler implements Serializable {

    private final static String uploadFolder = "uploads";

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        String fileName = calculateCurrentDatePart() + " - " + event.getFile().getFileName();
        FacesMessage message = new FacesMessage("Datei ", fileName + " erfolgreich gespeichert.");
        Files.copy(event.getFile().getInputstream(), new File(fileName).toPath());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private String calculateCurrentDatePart() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    
}