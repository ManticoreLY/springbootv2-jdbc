package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_budget")
public class Budget extends Base{

    @Id
    @GeneratedValue
    @Column(name = "budget_id")
    private String budgetId;

    @Column(name = "budget_name")
    private String budgetName;

    @Column(name = "budget_price")
    private Double budgetPrice;

    @Column(name = "budget_count")
    private Double budgetCount;

    @Column(name = "budget_sum")
    private Double budgetSum;

    @Column(name = "project_id")
    private String projectId;

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public Double getBudgetPrice() {
        return budgetPrice;
    }

    public void setBudgetPrice(Double budgetPrice) {
        this.budgetPrice = budgetPrice;
    }

    public Double getBudgetCount() {
        return budgetCount;
    }

    public void setBudgetCount(Double budgetCount) {
        this.budgetCount = budgetCount;
    }

    public Double getBudgetSum() {
        return budgetSum;
    }

    public void setBudgetSum(Double budgetSum) {
        this.budgetSum = budgetSum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
