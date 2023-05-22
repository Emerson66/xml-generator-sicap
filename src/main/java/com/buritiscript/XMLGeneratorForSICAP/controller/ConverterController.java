package com.buritiscript.XMLGeneratorForSICAP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buritiscript.XMLGeneratorForSICAP.service.XMLExtratorService;


@RestController
public class ConverterController {

    @Autowired
    XMLExtratorService xmlExtratorService;

    @GetMapping("/leitor")
    public void lerExcel(){
        try {
            xmlExtratorService.geradorXMLConcurso();
        } catch (Exception e) {
            
        }
    }
}
