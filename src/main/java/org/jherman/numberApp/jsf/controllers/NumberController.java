package org.jherman.numberApp.jsf.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.*;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.*;
import org.jherman.numberApp.jsf.entities.Number;
import org.jherman.numberApp.jsf.services.NumberService;
import org.primefaces.PrimeFaces;

import java.util.List;

@Model
public class NumberController {

    private Long id;
    private Number number;
    private List<Number> numbersList;

    @Inject
    private NumberService service;

    @PostConstruct
    public void init() {
        this.numbersList = this.service.listar();
        number = new Number();
    }

    /* Get and Set */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public List<Number> getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(List<Number> numbersList) {
        this.numbersList = numbersList;
    }

    /* Obtiene el dato por medio del id */
    public Number number(){
        this.number = new Number();
        if (id != null && id > 0) {
            service.porId(id).ifPresent(p -> {
                this.number = p;
            });
        }
        return number;
    }

    /* Obtiene el dato a editar */
    public void editNumber(Long id) {
        this.id = id;
        number();
    }

    /* Guarda o edita el dato */
    public void saveNumber() {
        if (number.getId() != null && number.getId() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Número Actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Número Guardado"));
        }
        service.guardar(number);
        numbersList = service.listar();
        PrimeFaces.current().executeScript("PF('manageNumberDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-numbers");
        number = new Number();
    }

    /* Eliminar el dato */
    public void deleteNumber(Number number){
        service.eliminar(number.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Número Eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-numbers");
        numbersList = service.listar();
    }

    /* Suma los numeros */
    public float sumNumbers() {
        float sum = 0;
        for (Number n : numbersList) {
            sum += n.getNumeroingresado();
        }
        return sum;
    }

    /* Promedia los numeros */
    public float average() {
        float sum = 0;
        for (Number n : numbersList) {
            sum += n.getNumeroingresado();
        }
        if (numbersList.size() > 0) {
            return sum / numbersList.size();
        } else {
            return 0;
        }
    }

    /* Obtiene el número mayor */
    public float largestNumber() {
        float largest = numbersList.get(0).getNumeroingresado();
        for (Number n : numbersList) {
            if (n.getNumeroingresado() > largest) {
                largest = n.getNumeroingresado();
            }
        }
        return largest;
    }

    /* Obtiene el número menor */
    public float lowerNumber() {
        float min = Float.MAX_VALUE;
        for (Number n : numbersList) {
            if (n.getNumeroingresado() < min) {
                min = n.getNumeroingresado();
            }
        }
        return min;
    }

}
