package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.Tariff;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "select * from v_tariffs_info",nativeQuery = true)
    List<TariffProjection> getAllTariffs();

    @Query(value = "select * from v_tariffs_info where id_tariff = ?1", nativeQuery = true)
    TariffProjection getTariffById(Integer idTariff);

    @Query(value = "select * from v_tariffs_info where city_origin = ? AND city_destination = ?",nativeQuery = true)
    TariffProjection getTariffByName(String originCityName, String destinationCityName);

    @Transactional
    @Procedure(procedureName = "sp_create_tariff_by_city_name")
    Integer addTariff(@Param("pOriginCityName") String originCityName,@Param("pDestinationCityName") String destinationCityName,@Param("pPrice_per_minute") long pricePerMinute,@Param("pCost_per_minute") float costPerMinute);
}
