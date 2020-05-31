package com.beyond.mapper;

import java.util.List;

import com.beyond.entity.Author;

public interface AuthorMapper extends BaseMapper<Author> {
	List<Author> selectByExampleBlur(Author author);
}
