package ec.edu.ucacue.smartgym.repository;

import ec.edu.ucacue.smartgym.entity.ReservaLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaLugarRepository extends JpaRepository<ReservaLugar, Long> {
    
}