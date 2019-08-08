package com.tim.app;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tim.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    @Autowired private LabResultsRepo labResultsRepo;
    @Autowired private ConResultsRepo conResultsRepo;
    @Autowired private LdResultsRepo ldResultsRepo;
    @Autowired private PcResultsRepo pcResultsRepo;
    @Autowired private OthResultsRepo othResultsRepo;
    @Autowired private GrnResultsRepo grnResultsRepo;
    @Autowired private UkipResultsRepo ukipResultsRepo;

    public AppController(){
        mapper = new XmlMapper();
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
    public Iterable<LabResults> labResults(){
        return labResultsRepo.findAll();
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

    @PostMapping("/testpost")
    public void testpost(){
        Map<String, Integer> x = new HashMap<>();
        x.put("LAB", 5);
        Map<String, Double> y = new HashMap<>();
        y.put("LAB", 22.0);
        labResultsRepo.save(new LabResults(1, "Sussex",1,2.0));
    }

}
