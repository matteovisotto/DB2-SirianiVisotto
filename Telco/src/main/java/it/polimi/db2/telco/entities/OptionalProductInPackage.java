package it.polimi.db2.telco.entities;

import javax.persistence.*;

@Entity
@Table(name = "optional_product_in_package")
public class OptionalProductInPackage {
    @EmbeddedId
    private OptionalProductInPackageId id;

    @MapsId("packageId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id", nullable = false)
    private ServicePackage _package;

    @MapsId("optionalProductId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "optional_product_id", nullable = false)
    private OptionalProduct optionalProduct;

    public OptionalProduct getOptionalProduct() {
        return optionalProduct;
    }

    public void setOptionalProduct(OptionalProduct optionalProduct) {
        this.optionalProduct = optionalProduct;
    }

    public ServicePackage get_package() {
        return _package;
    }

    public void set_package(ServicePackage _package) {
        this._package = _package;
    }

    public OptionalProductInPackageId getId() {
        return id;
    }

    public void setId(OptionalProductInPackageId id) {
        this.id = id;
    }
}