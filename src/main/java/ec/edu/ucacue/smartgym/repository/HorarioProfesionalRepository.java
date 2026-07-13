package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.HorarioProfesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioProfesionalRepository extends JpaRepository<HorarioProfesional, Long> {

    @Query("SELECT h FROM HorarioProfesional h WHERE h.med_usu_id = :medUsuId")
    List<HorarioProfesional> findByMedUsuId(@Param("medUsuId") Long medUsuId);
}