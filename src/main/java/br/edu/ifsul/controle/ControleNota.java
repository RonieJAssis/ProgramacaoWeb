/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.NotaDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Nota;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ronie
 */
@Named(value = "controleNota")
@ViewScoped
public class ControleNota implements Serializable {

    @EJB
    private NotaDAO<Nota> dao;
    private Nota objeto;
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;

    public ControleNota() {

    }

    public String listar() {
        return "/privado/nota/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Nota();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }

    public NotaDAO<Nota> getDao() {
        return dao;
    }

    public void setDao(NotaDAO<Nota> dao) {
        this.dao = dao;
    }

    public Nota getObjeto() {
        return objeto;
    }

    public void setObjeto(Nota objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoAluno
     */
    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    /**
     * @param daoAluno the daoAluno to set
     */
    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }
}
