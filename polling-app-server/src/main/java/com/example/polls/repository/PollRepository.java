package com.example.polls.repository;

import com.example.polls.models.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List ;

public interface PollRepository extends JpaRepository<Poll,Long> {
    Optional<Poll> findById(Long aLong);


    Page<Poll> findByCreatedBy(Long userId , Pageable pageable) ;
    long countByCreatedBy(Long userId);

    List<Poll> findByIdIn(List<Long> pollIds);

    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
