package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;


@Service
public class LogService {
	@Autowired
	private LogRepository logRepository;

	public Log save(Log log) {
		return logRepository.save(log);
	}

	public Optional<Log> findById(Integer id) {
		return logRepository.findById(id);
	}
}
