package it.polimi.db2.telco.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_in_package")
public class ServiceInPackage {
    @EmbeddedId
    private ServiceInPackageId id;

    @MapsId("packageId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id", nullable = false)
    private ServicePackage _package;

    @MapsId("serviceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServicePackage get_package() {
        return _package;
    }

    public void set_package(ServicePackage _package) {
        this._package = _package;
    }

    public ServiceInPackageId getId() {
        return id;
    }

    public void setId(ServiceInPackageId id) {
        this.id = id;
    }
}