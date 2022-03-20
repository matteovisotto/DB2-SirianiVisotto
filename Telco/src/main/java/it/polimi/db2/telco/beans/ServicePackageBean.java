package it.polimi.db2.telco.beans;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.entities.ServicePackage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



public class ServicePackageBean {
    class Price{
        Integer validityPeriod;
        Double price;

        public Price(Integer validityPeriod, Double price) {
            this.validityPeriod = validityPeriod;
            this.price = price;
        }
    }
    Integer id;
    String name;
    List<Price> packagePrice =new  ArrayList<>();
    List<OptionalProductBean> optionalProducts = new ArrayList<>();
    List<ServiceBean> services = new ArrayList<>();

    public ServicePackageBean(ServicePackage entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.services = entity.getServices().stream().map(ServiceBean::new).collect(Collectors.toList());
        this.optionalProducts = entity.getOptionalProducts().stream().map(OptionalProductBean::new).collect(Collectors.toList());
        entity.getPackagePrices().forEach(p -> {
            this.packagePrice.add(new Price(p.getValidityPeriod(), p.getPrice()));
        });
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(List<Price> packagePrice) {
        this.packagePrice = packagePrice;
    }

    public List<OptionalProductBean> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(List<OptionalProductBean> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public List<ServiceBean> getServices() {
        return services;
    }

    public void setServices(List<ServiceBean> services) {
        this.services = services;
    }
}
