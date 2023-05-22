package com.buritiscript.XMLGeneratorForSICAP.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("endereco")
public class Pessoa {
    private String nome;
    private String cpf;
    private List<Endereco> enderecos;
}
