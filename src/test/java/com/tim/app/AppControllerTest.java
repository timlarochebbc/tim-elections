package com.tim.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tim.model.*;
import com.tim.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest {

    @Mock
    LabResultsRepo labResultsRepo;
    @Mock
    ConResultsRepo conResultsRepo;
    @Mock
    LdResultsRepo ldResultsRepo;
    @Mock
    PcResultsRepo pcResultsRepo;
    @Mock
    OthResultsRepo othResultsRepo;
    @Mock
    GrnResultsRepo grnResultsRepo;
    @Mock
    UkipResultsRepo ukipResultsRepo;

    @Mock
    private Iterator<Result> iteratorMock;

    @Mock
    Iterable<LabResults> iterator;

    HttpEntity httpEntity;

    String xml = "<constituencyResults><constituencyResult seqNo=\"1\"><consituencyId>2</consituencyId><constituencyName>Aberconwy</constituencyName><results><result><partyCode>LAB </partyCode><votes>8994</votes><share>33.00</share></result><result><partyCode>CON </partyCode><votes>7924</votes><share>29.10</share></result><result><partyCode>LD  </partyCode><votes>5197</votes><share>19.10</share></result><result><partyCode>PC  </partyCode><votes>3818</votes><share>14.00</share></result><result><partyCode>OTH</partyCode><votes>517</votes><share>1.90</share></result><result><partyCode>GRN </partyCode><votes>512</votes><share>1.90</share></result><result><partyCode>UKIP</partyCode><votes>296</votes><share>1.10</share></result></results></constituencyResult></constituencyResults>";


    AppController underTest;

    @Before
    public void before(){
        underTest = new AppController(labResultsRepo, conResultsRepo, ldResultsRepo, pcResultsRepo, othResultsRepo, grnResultsRepo, ukipResultsRepo);
        httpEntity = new HttpEntity(xml);
    }

    @Test
    public void postNewResultsTest(){
        //given
        given(labResultsRepo.save(any(LabResults.class))).willReturn(new LabResults());
        given(conResultsRepo.save(any(ConResults.class))).willReturn(new ConResults());
        given(ldResultsRepo.save(any(LdResults.class))).willReturn(new LdResults());
        given(pcResultsRepo.save(any(PcResults.class))).willReturn(new PcResults());
        given(othResultsRepo.save(any(OthResults.class))).willReturn(new OthResults());
        given(grnResultsRepo.save(any(GrnResults.class))).willReturn(new GrnResults());
        given(ukipResultsRepo.save(any(UkipResults.class))).willReturn(new UkipResults());

        //when
        ResponseEntity<String> response = underTest.postResults(httpEntity);

        //assert
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getLabResultsTest(){
        given(labResultsRepo.findAll()).willReturn(iterator);

        ResponseEntity<Iterable<LabResults>> response = underTest.labResults();

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getConstituencyResultsByIdTest() throws JsonProcessingException {
        given(conResultsRepo.findById(anyInt()).get()).willReturn(new ConResults());
        given(labResultsRepo.findById(anyInt()).get()).willReturn(new LabResults());
        given(grnResultsRepo.findById(anyInt()).get()).willReturn(new GrnResults());
        given(ldResultsRepo.findById(anyInt()).get()).willReturn(new LdResults());
        given(othResultsRepo.findById(anyInt()).get()).willReturn(new OthResults());
        given(ukipResultsRepo.findById(anyInt()).get()).willReturn(new UkipResults());
        given(pcResultsRepo.findById(anyInt()).get()).willReturn(new PcResults());

        ResponseEntity<String> response = underTest.resultsForConstituency(2);
        //If Json in undertest.ResultsForConstituency successfully serialises, no error
        //will be thrown otherwise it will error so no need to check here.

        assertEquals(200, response.getStatusCodeValue());
    }


}
