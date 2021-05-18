/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends DAOGenerico<Especialidade> implements Serializable {

    public EspecialidadeDAO() {
        super();
        classePersistente = Especialidade.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);
    }

    public List<Especialidade> getListaObjetosCompleta() {
        String jpql = "select distinct e from Especialidade e order by e.id";
        List<Especialidade> lista = em.createQuery(jpql).getResultList();
        System.out.println(lista.size());
        return lista;
    }
}
