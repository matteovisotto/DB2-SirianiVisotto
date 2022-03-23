package it.polimi.db2.telco.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service_package")
public class ServicePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     Integer id;

    @Column(name = "name", nullable = false)
     String name;

    @OneToMany(mappedBy = "_package", cascade = CascadeType.ALL)
     List<PackagePrice> packagePrices = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "optional_product_in_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "optional_product_id"))
     List<OptionalProduct> optionalProducts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "service_in_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
     List<Service> services = new ArrayList<>();

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public List<PackagePrice> getPackagePrices() {
        return packagePrices;
    }

    public void setPackagePrices(List<PackagePrice> packagePrices) {
        this.packagePrices = packagePrices;
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