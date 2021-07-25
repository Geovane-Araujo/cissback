/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.processociss.dynamicutils;

import java.sql.Connection;

public class Dynamic {

    private String route;
    private int paging = 1;
    private String filters = "";
    private String orders = "";

    public static Connection conectionsdbmaster;

     public Dynamic() {
    }
    
    public Dynamic(String route) {
        this.route = route;
    }
    
    

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}

