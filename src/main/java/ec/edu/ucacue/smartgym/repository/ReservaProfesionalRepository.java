package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.ReservaProfesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaProfesionalRepository extends JpaRepository<ReservaProfesional, Long> {
    @Query("SELECT r FROM ReservaProfesional r WHERE r.usu_id = :usu_id")
    List<ReservaProfesional> findByUsu_id(@Param("usu_id") Long usu_id);
    
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
       "FROM ReservaProfesional r " +
       "WHERE r.profesional_id = :profesional_id AND r.res_fecha = :res_fecha")
boolean existsByProfesional_idAndRes_fecha(@Param("profesional_id") Long profesional_id,
                                           @Param("res_fecha") LocalDate res_fecha);
}
