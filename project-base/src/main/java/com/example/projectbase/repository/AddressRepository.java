package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByLatitudeAndLongitude(float latitude, float longitude);

    boolean existsByLatitudeAndLongitude(float latitude, float longitude);

    @Transactional
    @Modifying
    @Query("UPDATE Address a SET a.addressName = ?2, a.latitude = ?3, a.longitude = ?4 WHERE a.id = ?1")
    void updateAddress(int addressId, String addressName, float latitude, float longitude);
}
