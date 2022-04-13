/*
package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.exceptions.packagePrice.PackagePriceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PackagePriceService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public PackagePriceService(){}

    public PackagePrice getPackagePriceById(Integer packagePriceId) throws PackagePriceNotFoundException {
        PackagePrice packagePrice = em.find(PackagePrice.class, packagePriceId);
        if(packagePrice == null){
            throw new PackagePriceNotFoundException();
        }
        return packagePrice;
    }

    public Double getPriceByValidityPeriodAndPackage(Integer validityPeriod, Integer packageId) throws PackagePriceNotFoundException {
        TypedQuery<PackagePrice> query = em.createQuery("SELECT p FROM PackagePrice p WHERE p.validityPeriod = :validityPeriod AND p._package = :packageId", PackagePrice.class);
        query.setParameter("validityPeriod", validityPeriod);
        query.setParameter("packageId", packageId);
        PackagePrice packagePrice = query.getSingleResult();
        if (packagePrice == null) {
            throw new PackagePriceNotFoundException();
        }
        return packagePrice.getPrice();
    }

    public boolean isPackagePriceAlreadyExisting(Integer validityPeriod, Integer packageId) throws PackagePriceNotFoundException {
        TypedQuery<PackagePrice> query = em.createQuery("SELECT p FROM PackagePrice p WHERE p.validityPeriod = :validityPeriod AND p._package = :packageId", PackagePrice.class);
        query.setParameter("validityPeriod", validityPeriod);
        query.setParameter("packageId", packageId);
        return query.getResultList().size() == 1;
    }

    public List<PackagePrice> getPackagePricesOfPackage(Integer packageId) throws PackagePriceNotFoundException {
        TypedQuery<PackagePrice> query = em.createQuery("SELECT p FROM PackagePrice p WHERE p._package.id = :packageId", PackagePrice.class);
        query.setParameter("packageId", packageId);
        List<PackagePrice> packagePrices = query.getResultList();
        if (packagePrices.size() == 0) {
            throw new PackagePriceNotFoundException();
        }
        return packagePrices;
    }

    public Integer createPackagePrice(PackagePrice packagePrice) {
        em.persist(packagePrice);
        em.flush();
        return packagePrice.getId();
    }

    public Integer updatePackagePrice(PackagePrice packagePrice) {
        packagePrice = em.merge(packagePrice);
        em.flush();
        return packagePrice.getId();
    }


    public void deletePackagePrice(PackagePrice packagePrice) {
        if (!em.contains(packagePrice)) {
            packagePrice = em.merge(packagePrice);
        }
        em.remove(packagePrice);
        em.flush();
    }
}
*/
