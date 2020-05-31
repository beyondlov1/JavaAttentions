package com.beyond.mapper;

import com.beyond.entity.Owner;

public interface OwnerMapper {

	Owner queryById(String id);

	void addOwner(Owner owner);
}
