/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class CursoDAO<TIPO> extends DAOGenerico<Curso> implements Serializable{
    public CursoDAO(){
        super();
        classePersistente=Curso.class;
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        listaOrdem.add(new Ordem("sigla","Sigla", "like"));
        listaOrdem.add(new Ordem("instituicao.nome","Instituicao", "="));
        ordemAtual= listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);        
    }
    public Curso getObjectByID(Object id) throws Exception {
        Curso obj = em.find(Curso.class,id);
        obj.getDisciplinas().size();
        
        return obj;
    }
}
