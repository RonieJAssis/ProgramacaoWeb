/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Disciplina;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class DisciplinaDAO<TIPO> extends DAOGenerico<Disciplina> implements Serializable{
    public DisciplinaDAO(){
        super();
        classePersistente=Disciplina.class;
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        ordemAtual= listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);  
    }
}
