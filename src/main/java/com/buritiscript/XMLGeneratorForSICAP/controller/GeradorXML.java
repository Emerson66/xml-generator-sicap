package com.buritiscript.XMLGeneratorForSICAP.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.buritiscript.XMLGeneratorForSICAP.model.Endereco;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


@Controller
public class GeradorXML {


    @GetMapping("/xmls")
    public static void gravaEndereco(){
        XStream xStream = new XStream();

        Endereco endereco = new Endereco();
        endereco.setCidade("Primavera do Leste");
        endereco.setRua("Rua das araras");
        
        
        xStream.alias("endereco", Endereco.class);
       
        File arquivo = new File("upload-dir/endereco.xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(endereco).getBytes());
            gravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/xml")
    public void geraXML(){
        try{
            String arquivo = "upload-dir/concurso2023.xml";
 
            DocumentBuilderFactory dbf  =DocumentBuilderFactory.newInstance();
            DocumentBuilder dc = dbf.newDocumentBuilder();
            Document d = dc.newDocument();

            //elemento raiz do XML
            Element inscritos = d.createElement("inscritos");
            d.appendChild(inscritos);

            for(int i = 0; i < 5; i++){

                //elemento post
                Element inscrito = d.createElement("inscrito");
                inscritos.appendChild(inscrito);
    
                //definindo o atributo do post
                Attr nome =d.createAttribute("nome");
                nome.setValue("fulano");
                inscrito.setAttributeNode(nome);

                //definindo o atributo do post
                Attr cpf =d.createAttribute("cpf");
                cpf.setValue("000.000.000-0"+ i);
                inscrito.setAttributeNode(cpf);

                

            
                
            }
            //construção do XML
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource domSource = new DOMSource(d);
            StreamResult streamResult = new StreamResult(new File(arquivo));
            //juntar o conteudo ao arquivo criado
            t.transform(domSource,streamResult);
            System.out.println("Criado");
            
 
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Diretório "+System.getProperty("user.dir"));
        }
 
    }
}
