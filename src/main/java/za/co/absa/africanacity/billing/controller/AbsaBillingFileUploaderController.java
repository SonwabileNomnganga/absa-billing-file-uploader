package za.co.absa.africanacity.billing.controller;

import com.github.ffpojo.exception.FFPojoException;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.absa.africanacity.billing.service.BillingUploaderService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

        log.info("Upload request received......");

        if (inputStream == null)
            return Response.status(400).entity(INVALID_INPUT_DATA).build();

        try {
            saveStreamToDatabase(inputStream);
        } catch (IOException e) {
            return Response.status(500).entity(FILE_READ_ERROR + e.getMessage()).build();
        } catch (FFPojoException e) {
            return Response.status(500).entity(FILE_PARSING_ERROR + e.getMessage()).build();
        }

        return Response.status(200)
                .entity(SUCCESS_UPLOAD).build();
    }

    private void saveStreamToDatabase(InputStream inputStream) throws IOException, FFPojoException {

        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(isr);
        String data;
        while ((data = in.readLine()) != null) {
            service.saveData(data);
        }
    }

}
