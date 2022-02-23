package it.polimi.db2.telco.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "service_package")
public class ServicePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "_package")
    private Set<PackagePrice> packagePrices = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "optional_product_in_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "optional_product_id"))
    private Set<OptionalProduct> optionalProducts = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "service_in_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services = new LinkedHashSet<>();

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(Set<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public Set<PackagePrice> getPackagePrices() {
        return packagePrices;
    }

    public void setPackagePrices(Set<PackagePrice> packagePrices) {
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