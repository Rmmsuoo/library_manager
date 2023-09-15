package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
	Log findTopByLibraryIdAndUserIdOrderByRentDateDesc(Integer libraryId, Integer userId);

	List<Log> findByUserIdOrderByRentDateDesc(Integer userId);

}
