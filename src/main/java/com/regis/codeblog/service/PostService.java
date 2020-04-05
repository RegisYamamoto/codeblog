package com.regis.codeblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regis.codeblog.model.Post;
import com.regis.codeblog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public List<Post> listarTodosOsPosts() {
		return postRepository.findAll();
	}
	
	public Post listarPostPorId(long id) {
		return postRepository.findById(id).get();
	}
	
	public Post salvarPost(Post post) {
		return postRepository.save(post);
	}
	
}