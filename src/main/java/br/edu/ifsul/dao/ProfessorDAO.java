/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Professor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class ProfessorDAO<TIPO> extends DAOGenerico<Professor> implements Serializable{
    public ProfessorDAO(){
        super();
        classePersistente=Professor.class;
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        ordemAtual= listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);
    }
    public List<Professor> getListaObjetosCompleta() {
        String jpql = "select distinct p from Professor p order by p.id";
        List<Professor> lista = em.createQuery(jpql).getResultList();
        System.out.println(lista.size());
        return lista;
    }
}
