/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Nota;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class NotaDAO<TIPO> extends DAOGenerico<Nota> implements Serializable{
    public NotaDAO(){
        super();
        classePersistente=Nota.class;
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("aluno.nome","Aluno", "="));
        listaOrdem.add(new Ordem("disciplina.nome","Disciplina", "="));
        ordemAtual= listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);        
    }
}
