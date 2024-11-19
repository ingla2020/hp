package hd.repository;

import hd.entity.HdDepartment;
import hd.entity.HdEmpresas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeparmentRepository extends JpaRepository<HdDepartment, String> {
}
