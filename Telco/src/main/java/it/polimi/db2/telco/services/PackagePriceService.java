package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PackagePrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PackagePriceService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public PackagePriceService(){}

    public PackagePrice getPackagePriceById(Integer packagePriceId) {
        PackagePrice packagePrice = em.find(PackagePrice.class, packagePriceId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return packagePrice;
    }

    public Double getPriceByValidityPeriodAndPackage(Integer validityPeriod, Integer packageId) throws Exception {
        TypedQuery<PackagePrice> query = em.createQuery("SELECT p FROM PackagePrice p WHERE p.validityPeriod = :validityPeriod AND p._package = :packageId", PackagePrice.class);
        query.setParameter("validityPeriod", validityPeriod);
        query.setParameter("packageId", packageId);
        PackagePrice packagePrices = query.getSingleResult();
        if (packagePrices == null) {
            throw new Exception("");
        }
        return packagePrices.getPrice();
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
    }

    public List<PackagePrice> getPackagePricesOfPackage(Integer packageId) {
        TypedQuery<PackagePrice> query = em.createQuery("SELECT p FROM PackagePrice p WHERE p._package.id = :packageId", PackagePrice.class);
        query.setParameter("packageId", packageId);
        List<PackagePrice> packagePrices = query.getResultList();
        return packagePrices;
    }

    public Integer createPackagePrice(PackagePrice packagePrice){
        em.persist(packagePrice);
        em.flush();
        return packagePrice.getId();
    }

    public Integer updatePackagePrice(PackagePrice packagePrice){
        packagePrice = em.merge(packagePrice);
        em.flush();
        return packagePrice.getId();
    }


    public void deletePackagePrice(PackagePrice packagePrice){
        if (!em.contains(packagePrice)) {
            packagePrice = em.merge(packagePrice);
        }
        em.remove(packagePrice);
        em.flush();
    }
}
