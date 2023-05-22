package com.buritiscript.XMLGeneratorForSICAP.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLExtratorServiceInterface {
    void atributoBuilder(String chave, String valor, Document d, Element e);
    void atributoBuilder(String chave, Double valor, Document d, Element e);
    void geradorXMLConcurso() throws IOException, ParserConfigurationException, TransformerException;
}
