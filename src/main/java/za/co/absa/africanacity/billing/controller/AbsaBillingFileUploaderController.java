package za.co.absa.africanacity.billing.controller;

import com.github.ffpojo.exception.FFPojoException;
import com.sun.jersey.multipart.FormDataParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.absa.africanacity.billing.service.BillingUploaderService;

import javax.ws.rs.core.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = "/file")
public class AbsaBillingFileUploaderController {

    public static final String INVALID_INPUT_DATA = "Invalid input data";
    public static final String FILE_READ_ERROR = "Error occurred while reading the file. ";
    public static final String FILE_PARSING_ERROR = "Error occurred while parsing the file. ";
    public static final String SUCCESS_UPLOAD = "Billing extract uploaded with success ";

    @Autowired
    private BillingUploaderService service;

    @RequestMapping(value = "/post", method= RequestMethod.POST, produces = "application/json")
    public Response file(@FormDataParam("file") InputStream inputStream){

        log.info("New file upload request received..........");

        if (inputStream == null)
            return Response.status(400).entity(INVALID_INPUT_DATA).build();

        try {
            log.info(" to save");
            saveStream(inputStream);
            log.info("done saving");

            inputStream.close();
        } catch (IOException e) {
            return Response.status(500).entity(FILE_READ_ERROR + e.getMessage()).build();
        } catch (FFPojoException e) {
            return Response.status(500).entity(FILE_PARSING_ERROR + e.getMessage()).build();
        }

        return Response.status(200)
                .entity(SUCCESS_UPLOAD).build();
    }

    private void saveStream(InputStream inputStream) throws IOException, FFPojoException {

        log.info("to save stream");
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(isr);

        String data;
        while ((data = in.readLine()) != null) {
            service.saveData(data);
        }
    }

}