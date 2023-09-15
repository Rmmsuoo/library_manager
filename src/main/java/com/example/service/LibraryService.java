package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Library;
import com.example.entity.Log;
import com.example.repository.LibraryRepository;
import com.example.repository.LogRepository;

@Service
public class LibraryService {
	private final LibraryRepository libraryRepository;
	private final LogRepository logRepository;

	@Autowired
	public LibraryService(LibraryRepository libraryRepository, LogRepository logRepository) {
		this.libraryRepository = libraryRepository;
		this.logRepository = logRepository;
	}

	public List<Library> findAll() {
		return this.libraryRepository.findAll();
	}

	public Library findById(Integer id) {
		return this.libraryRepository.findById(id).orElse(null);
	}
	
	public List<Log> findLogsByUserId(Integer userId) {
        return logRepository.findByUserIdOrderByRentDateDesc(userId);
    }

	public void borrowBook(Integer id, String returnDueDateString, LoginUser loginUser) {
		Library library = libraryRepository.findById(id).orElseThrow(); // 仮の書籍取得メソッド
		library.setUserId(loginUser.getId()); // 仮のユーザーID設定メソッド
		libraryRepository.save(library); // 仮の書籍情報更新メソッド

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime returnDueDate = LocalDateTime.parse(returnDueDateString);
		Log log = new Log();
		log.setLibraryId(id);
		log.setUserId(loginUser.getId());
		log.setRentDate(LocalDateTime.now());
		log.setReturnDueDate(returnDueDate);
		log.setReturnDate(null);

		logRepository.save(log);
	}

	public void returnBook(Integer id, LoginUser loginUser) {
		Library library = libraryRepository.findById(id).orElseThrow();
		library.setUserId(0);
		libraryRepository.save(library);

		Log lastLog = logRepository.findTopByLibraryIdAndUserIdOrderByRentDateDesc(id, loginUser.getId());
		LocalDateTime returnDueDate = lastLog.getReturnDueDate();
		
		Log log = new Log();
		log.setLibraryId(id);
		log.setUserId(loginUser.getId());
		log.setRentDate(LocalDateTime.now());
		log.setReturnDueDate(returnDueDate);
		log.setReturnDate(LocalDateTime.now());

		logRepository.save(log);
	}
}
