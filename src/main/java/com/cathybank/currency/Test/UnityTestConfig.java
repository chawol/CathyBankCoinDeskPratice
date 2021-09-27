package com.cathybank.currency.Test;

import com.cathybank.currency.Controller.ApiController;
import com.cathybank.currency.Enity.CyName;
import com.cathybank.currency.Repo.CyNameRepo;
import com.cathybank.currency.Service.CyNameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnityTestConfig {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CyNameRepo cyNameRepo;
    @Autowired
    private CyNameService cyNameService;


    private ObjectMapper objectMapper = new ObjectMapper();
    private Assert anAssert;

    @Test
    public void Tests_getAll()
            throws Exception {
        mvc.perform(get("/currencyName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void Test_add()
            throws Exception {
        String testData = "{\"cyChtName\": \"人民幣\",\"cyName\": \"CNY\"}";
        mvc.perform(post("/currencyName/add").contentType(MediaType.APPLICATION_JSON).content(testData))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    JsonToDto(result);
                }).andDo(print());
    }

    @Test
    public void Test_update()
            throws Exception {
        String testData = "{\"id\": 1,\"cyChtName\": \"人民幣測試修改\",\"cyName\": \"CNY\"}";
        mvc.perform(put("/currencyName/update").contentType(MediaType.APPLICATION_JSON).content(testData))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(this::match).andDo(print());
    }

    @Test
    public void Test_delete()
            throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/currencyName?id=4"))
                .andExpect(status().isOk()).andDo(result -> Assert.assertFalse(cyNameRepo.findById(0).get() == null))
                .andDo(print());
    }

    @Test
    public void Tests_getCoinDeskInfo()
            throws Exception {
        mvc.perform(get("/currencyName/getCoinDeskInfo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void Tests_getCoinDeskRaw()
            throws Exception {
        mvc.perform(get("/currencyName/getCoinDeskRaw")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }


    private void match(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        JsonToDto(result);
    }

    private void JsonToDto(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        CyName dataToCheck = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), CyName.class);
        Optional<CyName> dataInRepo = cyNameRepo.findById(dataToCheck.getId());
        Assert.assertNotNull(dataInRepo);
        Assert.assertEquals(dataInRepo.get().getId(), dataToCheck.getId());
        Assert.assertEquals(dataInRepo.get().getCyChtName(), dataToCheck.getCyChtName());
        Assert.assertEquals(dataInRepo.get().getCyName(), dataToCheck.getCyName());
    }
}
