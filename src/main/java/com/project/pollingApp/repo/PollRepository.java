package com.project.pollingApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pollingApp.enitty.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long>{

	
}
