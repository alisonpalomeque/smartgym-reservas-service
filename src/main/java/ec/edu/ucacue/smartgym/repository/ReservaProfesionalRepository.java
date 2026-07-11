package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.ReservaProfesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaProfesionalRepository extends JpaRepository<ReservaProfesional, Long> {
}