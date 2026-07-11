package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.HorarioProfesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioProfesionalRepository extends JpaRepository<HorarioProfesional, Long> {
}