package com.buritiscript.XMLGeneratorForSICAP.service;

import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



@Service
public class XMLExtratorService implements XMLExtratorServiceInterface{
    @Override
    public void geradorXMLConcurso() throws IOException, ParserConfigurationException, TransformerException{
        try {

            FileInputStream arquivo = new FileInputStream("upload-dir/guarda.xlsx");
            XSSFWorkbook planilha = new XSSFWorkbook(arquivo);
            XSSFSheet livros = planilha.getSheetAt(0);
            Iterator<Row> linhaIterator = livros.iterator();
            int numLinha = 0;


            String arquivoPath = "upload-dir/concurso2023.xml";
 
            DocumentBuilderFactory dbf  =DocumentBuilderFactory.newInstance();
            DocumentBuilder dc = dbf.newDocumentBuilder();
            Document d = dc.newDocument();

            
            Element inscritos = d.createElement("inscritos");
            d.appendChild(inscritos);

            while (linhaIterator.hasNext()){
                Row linha = linhaIterator.next();
                Iterator<Cell> celulIterator = linha.cellIterator();
               
               if(numLinha > 0){
                    Element inscrito = d.createElement("inscrito");
                    inscritos.appendChild(inscrito);

                    while(celulIterator.hasNext()) {
                       Cell celula = celulIterator.next();
                       switch (celula.getColumnIndex()) {
                           case 0:
                                atributoBuilder("ninscrito", celula.getNumericCellValue(), d, inscrito);
                                // Attr ninscrito = d.createAttribute("ninscrito");
                                // ninscrito.setValue(celula.getStringCellValue());
                                // inscrito.setAttributeNode(ninscrito);
                            break;
                            case 1: 
                               atributoBuilder("nome", celula.getStringCellValue(), d, inscrito);
                            //    Attr nome = d.createAttribute("nome");
                            //    nome.setValue(celula.getStringCellValue());
                            //    inscrito.setAttributeNode(nome);
                            break;
                        }
                        // atributoBuilder("deferido", "S", d, inscrito);
                    }
               }
               numLinha++;
           }
           //construção do XML
           TransformerFactory tf = TransformerFactory.newInstance();
           Transformer t = tf.newTransformer();
           DOMSource domSource = new DOMSource(d);
           StreamResult streamResult = new StreamResult(new File(arquivoPath));

           //juntar o conteudo ao arquivo criado
           t.transform(domSource,streamResult);
           arquivo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        }
    }

    @Override
    public void atributoBuilder(String chave, String valor, Document d, Element e){     
        Attr atributo = d.createAttribute(chave);
        atributo.setValue(valor);
        e.setAttributeNode(atributo);
    }
    @Override
    public void atributoBuilder(String chave, Double valor, Document d, Element e){     
        Attr atributo = d.createAttribute(chave);
        String newValor = Double.toString(valor);
        atributo.setValue(newValor);
        e.setAttributeNode(atributo);
    }
}
