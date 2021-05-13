package com.innotech.teatalk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innotech.teatalk.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	Tag findByName(String name);
	Tag findByNameContaining(String query);
}
