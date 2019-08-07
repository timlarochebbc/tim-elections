package com.tim.app;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tim.model.ConstituencyResult;
import com.tim.model.ConstituencyResults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AppController {
    XmlMapper mapper;
    public AppController(){
        mapper = new XmlMapper();
    }

    //Show some kind of front end of results
    @RequestMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    //Endpoint for posting .xml to the database
    @PostMapping("/newresult")
    public ResponseEntity<String> postResults(HttpEntity<String> httpEntity){
        //Holds our xml
        String reqBody = httpEntity.getBody();
        //do stuff
        deSerialiseXML(reqBody);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    //Helper function for de-serialising xml.
    public ConstituencyResults deSerialiseXML(String xmlBody){
        try {
            ConstituencyResults result = mapper.readValue(xmlBody, ConstituencyResults.class);
            System.out.println(result.getConstituencyResult()[0].getConsituencyId());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
