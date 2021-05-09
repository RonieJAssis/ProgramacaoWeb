/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Nota;
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
@Named(value = "controleDisciplina")
@ViewScoped
public class ControleDisciplina implements Serializable {

    @EJB
    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    private Aluno aluno;
    private Nota nota;
    private boolean novanota;
    @EJB
    private CursoDAO<Curso> daoCurso;

    public ControleDisciplina() {

    }
    
    public void imprimeDisciplinas(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioDisciplinas", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeDisciplina(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Disciplina> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioDisciplinas", parametros, lista);
            
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void novoAluno() {
        setAluno(new Aluno());
    }

    public void salvarAluno() {
        objeto.addAlunos(getAluno());
        Util.mensagemInformacao("aluno adicionado com sucesso!");
    }

    public void removerAluno(int index) {
        objeto.removerAluno(index);
        Util.mensagemInformacao("aluno removido com sucesso!");
    }

    public void novaNota() {
        setNota(new Nota());
        setNovanota(true);
    }

    public void alterarNota(int index) {
        setNota(objeto.getNotas().get(index));
        setNovanota(false);
    }

    public void salvarNota() {
        if (isNovanota()) {
            objeto.addNotas(getNota());
        }
        Util.mensagemInformacao("nota adicionada ou alterada com sucesso!");
    }

    public void removerNota(int index) {
        objeto.removerNotas(index);
    }

    public String listar() {
        return "/privado/disciplina/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Disciplina();
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
            System.out.println(objeto.getNome());
            dao.remover(objeto);
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

    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }

    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
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

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the nota
     */
    public Nota getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    /**
     * @return the novanota
     */
    public boolean isNovanota() {
        return novanota;
    }

    /**
     * @param novanota the novanota to set
     */
    public void setNovanota(boolean novanota) {
        this.novanota = novanota;
    }

    /**
     * @return the daoCurso
     */
    public CursoDAO<Curso> getDaoCurso() {
        return daoCurso;
    }

    /**
     * @param daoCurso the daoCurso to set
     */
    public void setDaoCurso(CursoDAO<Curso> daoCurso) {
        this.daoCurso = daoCurso;
    }
}
