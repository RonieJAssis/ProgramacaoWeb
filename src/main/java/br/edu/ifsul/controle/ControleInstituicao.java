/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
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
@Named(value="controleInstituicao")
@ViewScoped
public class ControleInstituicao implements Serializable{
    @EJB
    private InstituicaoDAO<Instituicao> dao;
    private Instituicao objeto;
    
    public ControleInstituicao(){
        
    }
    
    public void imprimeInstituicoes(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioInstituicoes", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeInstituicao(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Instituicao> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioInstituicoes", parametros, lista);
            
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public String listar(){
        return "/privado/instituicao/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Instituicao();
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
    
    public InstituicaoDAO<Instituicao> getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO<Instituicao> dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }
}
