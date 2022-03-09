package it.polimi.db2.telco.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "min")
    private Integer min;

    @Column(name = "sms")
    private Integer sms;

    @Column(name = "internet")
    private Integer internet;

    @Column(name = "extra_min")
    private Double extraMin;

    @Column(name = "extra_sms")
    private Double extraSms;

    @Column(name = "extra_internet")
    private Double extraInternet;

    @ManyToMany(mappedBy = "services")
    private List<ServicePackage> servicePackages = new ArrayList<>();

    public List<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(List<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }

    public Double getExtraInternet() {
        return extraInternet;
    }

    public void setExtraInternet(Double extraInternet) {
        this.extraInternet = extraInternet;
    }

    public Double getExtraSms() {
        return extraSms;
    }

    public void setExtraSms(Double extraSms) {
        this.extraSms = extraSms;
    }

    public Double getExtraMin() {
        return extraMin;
    }

    public void setExtraMin(Double extraMin) {
        this.extraMin = extraMin;
    }

    public Integer getInternet() {
        return internet;
    }

    public void setInternet(Integer internet) {
        this.internet = internet;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}