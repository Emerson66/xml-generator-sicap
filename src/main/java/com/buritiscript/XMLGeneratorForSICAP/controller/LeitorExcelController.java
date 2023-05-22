package com.buritiscript.XMLGeneratorForSICAP.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeitorExcelController {

    @GetMapping("/leitores")
    public void lerExcel() throws IOException{
        try{
            FileInputStream arquivo = new FileInputStream("upload-dir/livros.xls");
            XSSFWorkbook planilha = new XSSFWorkbook(arquivo);
            XSSFSheet livros = planilha.getSheetAt(0);
            Iterator<Row> linhaIterator = livros.iterator();
            int numLinha = 0;
            while (linhaIterator.hasNext()){
                Row linha = linhaIterator.next();
                Iterator<Cell> celulIterator = linha.cellIterator();
                if(numLinha > 0){
                    while(celulIterator.hasNext()) {
                        Cell celula = celulIterator.next();
                        switch (celula.getColumnIndex()) {
                            case 0:
                                System.out.println("Código "+celula.getNumericCellValue());
                                break;
                            case 1: 
                                System.out.println("Titulo "+celula.getStringCellValue());
                                break;
                        }
                    }
                }
                numLinha++;
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        }
    }
}
