package com.example.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.librarymanagementsystem.entity.Book;

public interface BookService {
	public Book getBookById(Long id);
	public List<Book> getBooks();
	public List<Book> getBooks(Integer page, Integer pageSize, String sortBy);
	public void saveBook(Book employee);
	public void deleteBookById(Long id);
	public Page<Book> getBooksPage(Integer page, Integer pageSize, String sortBy);
	public void deleteAllBooks();
}
