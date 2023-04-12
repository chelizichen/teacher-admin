package com.czq.back.repo;
import com.czq.back.entity.Assignment;
import com.czq.back.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query("SELECT a FROM  Section a  WHERE a.course.name LIKE %?1%")
    Page<Section> findByKeyword(String keyword, Pageable pageable);
}
