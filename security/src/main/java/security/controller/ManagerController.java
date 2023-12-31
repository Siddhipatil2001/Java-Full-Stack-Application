package security.controller;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.pojo.Address;

import security.pojo.Product;

import security.proxy.Products;


@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	@Autowired
	private Products pr;


//ProductService
	
	@GetMapping(value = "/allProducts", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value =  "Authorization",required = false) String authorization){
		   return pr.getAllProducts(authorization);
	}
	
	
	@PostMapping(value = "/add",produces = "application/json")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<Product> addProduct(@RequestBody Product product,@RequestHeader(value =  "Authorization",required = false) String authorization){
		return pr.addProduct(product, authorization);
	}
	
	@GetMapping(value ="/findByCategory/{category}",produces = "application/json")
    @PreAuthorize("hasRole('MANAGER')")	
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category,@RequestHeader(value =  "Authorization",required = false) String authorization){
		
		return pr.getProductByCategory(category, authorization);
	}
	
	
	@GetMapping(value ="/findByType/{productType}",produces = "application/json")
   @PreAuthorize("hasRole('MANAGER')")	
   public ResponseEntity<List<Product>> getProductByType(@PathVariable String productType,@RequestHeader(value =  "Authorization",required = false) String authorization){
	 return	pr.getProductByType(productType, authorization);
	}
	
	@GetMapping(value ="/findByName/{productName}",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName,@RequestHeader(value =  "Authorization",required = false) String authorization){
	return pr.getProductByName(productName, authorization);
	}

	

}
