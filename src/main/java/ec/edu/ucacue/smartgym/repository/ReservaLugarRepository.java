package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.ReservaLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaLugarRepository extends JpaRepository<ReservaLugar, Long> {

    @Query("SELECT r FROM ReservaLugar r WHERE r.usu_id = :usu_id")
    List<ReservaLugar> findByUsu_id(@Param("usu_id") Long usu_id);

    @Query("SELECT r FROM ReservaLugar r WHERE r.res_lug_id = :res_lug_id")
    ReservaLugar findByRes_lug_id(@Param("res_lug_id") Long res_lug_id);
}