package com.beyond.service;

import java.util.List;

public interface Service<T> {

	public void add(T t);

	public void remove(T t);

	public void modify(T t);

	public T find(Class<T> c, String id);

	public List<T> findAll(Class<T> c);
}
