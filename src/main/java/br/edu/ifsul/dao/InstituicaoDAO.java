/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author ronie
 */
@Stateful
public class InstituicaoDAO<TIPO> extends DAOGenerico<Instituicao> implements Serializable{
    public InstituicaoDAO(){
        super();
        classePersistente=Instituicao.class;
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        ordemAtual= listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrderm(listaOrdem);  
    }
    public List<Instituicao> getListaObjetosCompleta() {
        String jpql = "select distinct i from Instituicao i order by i.id";
        List<Instituicao> lista = em.createQuery(jpql).getResultList();
        System.out.println(lista.size());
        return lista;
    }
}
