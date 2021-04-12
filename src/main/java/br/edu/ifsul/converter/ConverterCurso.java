/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;


import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ronie
 */
@Named(value = "converterCurso")
@RequestScoped
public class ConverterCurso implements Serializable,Converter{
    
    @PersistenceContext(unitName = "EscolaMavenWebPU")
    protected EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null ||string.equals("Selecione um registro"))
            return null;
        return em.find(Curso.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if(t==null)
            return null;
        Curso obj = (Curso)t;
        return obj.getId().toString();
    }
    
}
