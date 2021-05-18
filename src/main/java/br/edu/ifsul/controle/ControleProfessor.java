/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ronie
 */
@Named(value="controleProfessor")
@ViewScoped
public class ControleProfessor implements Serializable{
    @EJB
    private ProfessorDAO<Professor> dao;
    private Professor objeto;
    @EJB
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    
    public ControleProfessor(){
        
    }
    
    public void imprimeProfessores(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioProfessores", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeProfessor(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Professor> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioProfessores", parametros, lista);
            
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public String listar(){
        return "/privado/professor/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Professor();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public ProfessorDAO<Professor> getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO<Professor> dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoEspecialidade
     */
    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    /**
     * @param daoEspecialidade the daoEspecialidade to set
     */
    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
