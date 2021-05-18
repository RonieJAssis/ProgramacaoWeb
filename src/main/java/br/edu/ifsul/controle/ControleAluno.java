/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
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
@Named(value="controleAluno")
@ViewScoped
public class ControleAluno implements Serializable{
    @EJB
    private AlunoDAO<Aluno> dao;
    private Aluno objeto;
    
    public ControleAluno(){
        
    }
    
    public void imprimeAlunos(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAlunos", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeAluno(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Aluno> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioAlunos", parametros, lista);
            
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public String listar(){
        return "/privado/aluno/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Aluno();
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
    
    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }
}
