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
    

    boolean existsByMed_usu_idAndRes_fecha(Long med_usu_id, LocalDate res_fecha);
}
