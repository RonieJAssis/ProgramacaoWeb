/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
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
@Named(value="controleEspecialidade")
@ViewScoped
public class ControleEspecialidade implements Serializable{
    @EJB
    private EspecialidadeDAO<Especialidade> dao;
    private Especialidade objeto;
    
    public ControleEspecialidade(){
        
    }
    
     
    public void imprimeEspecialidades(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioEspecialidade", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeEspecialidade(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Especialidade> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioEspecialidade", parametros, lista);
            
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Especialidade();
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
    
    public EspecialidadeDAO<Especialidade> getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO<Especialidade> dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
}
