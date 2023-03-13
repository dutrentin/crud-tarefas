package br.com.poc.util;

import java.io.Serializable;
import java.util.Date;

public class FilterTask implements Serializable {

    private static final long serialVersionUID = 7833360363674843256L;

    private Integer idPerson;
    private String descriptionFilter;
    private String title;
    private Boolean filterStatus;
    private Date dateCreation;
    private Date dateTask;
    private Date dataConclusion;
    private int maxResults;
    private int currentPage;
    private boolean status;
    private Date dateInitial;
    private Date dateFinal;
    private String order;

    public String getDescriptionFilter() {
        return descriptionFilter;
    }

    public void setDescriptionFilter(String descriptionFilter) {
        this.descriptionFilter = descriptionFilter;
    }

    public Boolean isFiltroStatus() {
        return filterStatus;
    }

    public void setFilterStatus(Boolean filterStatus) {
        this.filterStatus = filterStatus;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDataConclusion() {
        return dataConclusion;
    }

    public void setDataConclusion(Date dataConclusion) {
        this.dataConclusion = dataConclusion;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Boolean getFilterStatus() {
        return filterStatus;
    }

    public Date getDateTask() {
        return dateTask;
    }

    public void setDateTask(Date dateTask) {
        this.dateTask = dateTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(Date dateInitial) {
        this.dateInitial = dateInitial;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
