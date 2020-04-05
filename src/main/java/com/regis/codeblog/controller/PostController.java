package com.regis.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.regis.codeblog.model.Post;
import com.regis.codeblog.service.PostService;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping(value = "")
	public ModelAndView listarTodosOsPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Post> posts = postService.listarTodosOsPosts(); // Passar nome da página html por parâmetro
		mv.addObject("posts", posts); // Passar nome do objeto a ser usado no front-end por parâmetro
		return mv;
	}
	
	@GetMapping(value = "/id/{id}")
	public ModelAndView listarPostPorId(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("postDetails"); // Passar nome da página html por parâmetro
		Post post = postService.listarPostPorId(id);
		mv.addObject("post", post); // Passar nome do objeto a ser usado no front-end por parâmetro
		return mv;
	}
	
	@GetMapping(value = "/newpost")
	public String getPostForm() {
		return "postForm";
	}
	
	@PostMapping(value = "/newpost")
	public String salvarPost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/posts/newpost";
		} 
		
		post.setData(LocalDate.now());
		postService.salvarPost(post);
		return "redirect:/posts";
	}
	
	
}