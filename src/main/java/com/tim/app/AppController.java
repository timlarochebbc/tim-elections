package com.tim.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tim.model.*;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;

@Service
@RestController
public class AppController {
    XmlMapper mapper;
    @Autowired private ResultsTableRepository resultsTableRepository;
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
        ConstituencyResults constituencyResults = deSerialiseXML(reqBody);

        for(ConstituencyResult constituencyResult : constituencyResults.getAllConstituencyResult()){
            ElectionDbStub.addNewResult(constituencyResult.getConsituencyId(), constituencyResult.getResults());
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    //Helper function for de-serialising xml.
    public ConstituencyResults deSerialiseXML(String xmlBody){
        try {
            ConstituencyResults result = mapper.readValue(xmlBody, ConstituencyResults.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/test")
    public Iterable<ResultsTable> test(){
        return resultsTableRepository.findAll();
    }

}
