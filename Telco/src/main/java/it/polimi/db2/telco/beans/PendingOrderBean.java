package it.polimi.db2.telco.beans;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.Order;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PendingOrderBean {

    Integer id = 0;
    Integer packageId;
    Integer validityPeriod;
    Date startDate;
    List<Integer> optionalProducts;

    public PendingOrderBean(Integer id, Integer packageId, Integer validityPeriod, Date startDate, List<Integer> optionalProducts) {
        this.id = id;
        this.packageId = packageId;
        this.validityPeriod = validityPeriod;
        this.startDate = startDate;
        this.optionalProducts = optionalProducts;
    }

    public PendingOrderBean(Order entity) {
        this.id = entity.getId();
        this.packageId = entity.get_package().getId();
        this.validityPeriod = entity.getValidityPeriod();
        this.startDate = Date.from(entity.getStartDate().atStartOfDay(ZoneId.of( "Europe/Rome" )).toInstant());
        this.optionalProducts = entity.getOptionalProducts().stream().map(OptionalProduct::getId).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Integer> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(List<Integer> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }
}
