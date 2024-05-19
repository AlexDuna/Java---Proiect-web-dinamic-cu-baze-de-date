package com.example.Repository;

import com.example.Entity.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasinaRepository extends JpaRepository<Masina,String> {
    @Query("SELECT m FROM Masina m WHERE m.marca = :marca")
    List<Masina> findMasiniByMarca(@Param("marca") String marca);

    @Query("SELECT m FROM Masina m WHERE m.putere >= :putere")
    List<Masina> findMasiniByPutereMaiMareDecat(@Param("putere") int putere);

    @Query("SELECT m FROM Masina m WHERE m.an_fabricatie = :an_fabricatie")
    List<Masina> findMasiniByAnFabricatie(@Param("an_fabricatie") int an_fabricatie);
}
