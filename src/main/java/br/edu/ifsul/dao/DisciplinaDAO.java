/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
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
    @Override
    public Disciplina getObjectByID(Object id) throws Exception {
        Disciplina obj = em.find(Disciplina.class, id);
        obj.getNotas().size();
        obj.getAlunos().size();
        return obj;
    }   
    
    public List<Disciplina> getListaObjetosCompleta(){
        String jpql = "select distinct d from Disciplina d join fetch d.notas order by d.id";
        List<Disciplina> lista = em.createQuery(jpql).getResultList();
        System.out.println(lista.size());
        return lista;
    }
    
    public void remover(Disciplina obj) throws Exception {
        obj = em.find(Disciplina.class, obj.getId());
        em.remove(obj);
    } 
}
