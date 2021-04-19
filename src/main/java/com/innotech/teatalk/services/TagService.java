package com.innotech.teatalk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.models.Tag;
import com.innotech.teatalk.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tRepo;
	
	public void addTags(Article a, String tagList) {
		String[] tagArray = tagList.trim().split(",");
		List<Tag> tags = new ArrayList<Tag>();
		for(int i = 0; i < tagArray.length; i++) {
			if(tRepo.findByName(tagArray[i]) != null) {
				tags.add(tRepo.findByName(tagArray[i]));
			} else {
				Tag newTag = new Tag(tagArray[i]);
				tRepo.save(newTag);
				tags.add(newTag);
			}
		}
		a.setTags(tags);
	}
}
