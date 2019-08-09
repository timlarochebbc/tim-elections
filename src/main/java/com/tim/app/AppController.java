package com.tim.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tim.model.*;
import com.tim.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RestController
public class AppController {
    XmlMapper mapper;
//    @Autowired private LabResultsRepo labResultsRepo;
//    @Autowired private ConResultsRepo conResultsRepo;
//    @Autowired private LdResultsRepo ldResultsRepo;
//    @Autowired private PcResultsRepo pcResultsRepo;
//    @Autowired private OthResultsRepo othResultsRepo;
//    @Autowired private GrnResultsRepo grnResultsRepo;
//    @Autowired private UkipResultsRepo ukipResultsRepo;

    private LabResultsRepo labResultsRepo;
    private ConResultsRepo conResultsRepo;
    private LdResultsRepo ldResultsRepo;
    private PcResultsRepo pcResultsRepo;
    private OthResultsRepo othResultsRepo;
    private GrnResultsRepo grnResultsRepo;
    private UkipResultsRepo ukipResultsRepo;

    public AppController(){
        mapper = new XmlMapper();
    }

    @Autowired
    public AppController(LabResultsRepo labResultsRepo, ConResultsRepo conResultsRepo, LdResultsRepo ldResultsRepo, PcResultsRepo pcResultsRepo, OthResultsRepo othResultsRepo, GrnResultsRepo grnResultsRepo, UkipResultsRepo ukipResultsRepo) {
        this.labResultsRepo = labResultsRepo;
        this.conResultsRepo = conResultsRepo;
        this.ldResultsRepo = ldResultsRepo;
        this.pcResultsRepo = pcResultsRepo;
        this.othResultsRepo = othResultsRepo;
        this.grnResultsRepo = grnResultsRepo;
        this.ukipResultsRepo = ukipResultsRepo;

        this.mapper = new XmlMapper();
    }

    //Endpoint for posting .xml to the database
    @PostMapping("/newresult")
    public ResponseEntity<String> postResults(HttpEntity<String> httpEntity){
        //Holds our xml
        String reqBody = httpEntity.getBody();
        ConstituencyResultsRootModel constituencyResultsRoot = deSerialiseXML(reqBody);

        Integer id = constituencyResultsRoot.getAllConstituencyResult().getConsituencyId();
        String name = constituencyResultsRoot.getAllConstituencyResult().getConstituencyName();

        for(ResultModel constituencyResults : constituencyResultsRoot.getAllConstituencyResult().getResults()){
            String partyCode = constituencyResults.getPartyCode();
            Integer votes = constituencyResults.getVotes();
            Double share = constituencyResults.getShare();

            //Strip all whitespace and switch
            switch (partyCode.replaceAll("\\s+", "")){
                case "LAB":
                    labResultsRepo.save(new LabResults(id, name, votes, share));
                    break;
                case "CON":
                    conResultsRepo.save(new ConResults(id, name, votes, share));
                    break;
                case "LD":
                    ldResultsRepo.save(new LdResults(id, name, votes, share));
                    break;
                case "PC":
                    pcResultsRepo.save(new PcResults(id, name, votes, share));
                    break;
                case "OTH":
                    othResultsRepo.save(new OthResults(id, name, votes, share));
                    break;
                case "GRN":
                    grnResultsRepo.save(new GrnResults(id, name, votes, share));
                    break;
                case "UKIP":
                    ukipResultsRepo.save(new UkipResults(id, name, votes, share));
                    break;
                default:
                    System.out.println("Unkown Party");
                    break;
            }
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    //Helper function for de-serialising xml.
    public ConstituencyResultsRootModel deSerialiseXML(String xmlBody){
        try {
            ConstituencyResultsRootModel result = mapper.readValue(xmlBody, ConstituencyResultsRootModel.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/labresults")
    public ResponseEntity<Iterable<LabResults>> labResults(){
        return new ResponseEntity<>(labResultsRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/conresults")
    public Iterable<ConResults> conResults(){
        return conResultsRepo.findAll();
    }

    @RequestMapping("/ldresults")
    public Iterable<LdResults> ldResults(){
        return ldResultsRepo.findAll();
    }

    @RequestMapping("/pcresults")
    public Iterable<PcResults> pcResults(){
        return pcResultsRepo.findAll();
    }

    @RequestMapping("/othresults")
    public Iterable<OthResults> othResults(){
        return othResultsRepo.findAll();
    }

    @RequestMapping("/grnresults")
    public Iterable<GrnResults> grnResults(){
        return grnResultsRepo.findAll();
    }

    @RequestMapping("/ukipresults")
    public Iterable<UkipResults> ukipResults(){
        return ukipResultsRepo.findAll();
    }

    //Dangerous!
    @RequestMapping("/cleardb/{password}")
    public ResponseEntity<String> clearDb(@PathVariable("password") String password){
        if(password.equals("password123")){
            System.out.println("omg");
            conResultsRepo.deleteAll();
            labResultsRepo.deleteAll();
            grnResultsRepo.deleteAll();
            ldResultsRepo.deleteAll();
            othResultsRepo.deleteAll();
            ukipResultsRepo.deleteAll();
            pcResultsRepo.deleteAll();
            return new ResponseEntity<>("Wiped.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Wrong password.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/results/{id}")
    public ResponseEntity<String> resultsForConstituency(@PathVariable("id") int id) throws JsonProcessingException {
        Map<String, Result> json = new HashMap<>();

        //TODO: Make these check the optional first.
        json.put("CON", conResultsRepo.findById(id).get());
        json.put("LAB", labResultsRepo.findById(id).get());
        json.put("GRN", grnResultsRepo.findById(id).get());
        json.put("LD", ldResultsRepo.findById(id).get());
        json.put("OTH", othResultsRepo.findById(id).get());
        json.put("UKIP", ukipResultsRepo.findById(id).get());
        json.put("PC", pcResultsRepo.findById(id).get());
        ObjectMapper objectMapper = new ObjectMapper();
        return new ResponseEntity<>(objectMapper.writeValueAsString(json), HttpStatus.OK);
    }
}
