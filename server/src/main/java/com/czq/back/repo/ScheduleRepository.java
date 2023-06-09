package com.czq.back.repo;
import com.czq.back.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT a FROM  Schedule a  WHERE a.section.course.name LIKE %?1%")
    Page<Schedule> findByKeyword(String keyword, Pageable pageable);

    @Query("SELECT a FROM  Schedule a  WHERE a.teacherId = ?1")
    List<Schedule> getByTeacherId(Long id);
}
