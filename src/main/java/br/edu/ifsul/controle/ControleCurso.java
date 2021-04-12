/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.modelo.Curso;
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
@Named(value = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {

    @EJB
    private CursoDAO<Curso> dao;
    private Curso objeto;
    @EJB
    private InstituicaoDAO<Instituicao> daoInstituicao;
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    private Disciplina disciplina;
    private boolean novadisciplina;
    private Aluno aluno;
    private Nota nota;
    private boolean novanota;

    public ControleCurso() {

    }

    public void novaDisciplina() {
        disciplina = new Disciplina();
        novadisciplina = true;
    }

    public void alterarDisciplina(int index) {
        disciplina = objeto.getDisciplinas().get(index);
        novadisciplina = false;
    }

    public void salvarDisciplina() {
        if (novadisciplina) {
            objeto.addDisciplinas(disciplina);
        }
        Util.mensagemInformacao("disciplina adicionada ou alterada com sucesso!");
    }

    public void removerDisciplina(int index) {
        objeto.removerDisciplina(index);
    }

    public void novoAluno() {
        aluno = new Aluno();
    }

    public void salvarAluno() {
        disciplina.addAlunos(aluno);
        Util.mensagemInformacao("aluno adicionado com sucesso!");
    }

    public void removerAluno(int index) {
        disciplina.removerAluno(index);
    }

    public void novaNota() {
        nota = new Nota();
        novanota = true;
    }

    public void alterarNota(int index) {
        nota = disciplina.getNotas().get(index);
        novanota = false;
    }

    public void salvarNota() {
        if (novanota) {
            disciplina.addNotas(nota);
        }
        Util.mensagemInformacao("nota adicionada ou alterada com sucesso!");
    }

    public void removerNota(int index) {
        disciplina.removerNotas(index);
    }

    public String listar() {
        return "/privado/curso/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Curso();
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

    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoInstituicao
     */
    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    /**
     * @param daoInstituicao the daoInstituicao to set
     */
    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    /**
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the novadisciplina
     */
    public boolean isNovadisciplina() {
        return novadisciplina;
    }

    /**
     * @param novadisciplina the novadisciplina to set
     */
    public void setNovadisciplina(boolean novadisciplina) {
        this.novadisciplina = novadisciplina;
    }
}
