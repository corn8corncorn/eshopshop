package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String listProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "edit-product";
	}
	
	@PutMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
		productService.updateProduct(id, product);
		return "redirect:/products";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deletProduct(id);
		return "redirect:/products";
	}
}
