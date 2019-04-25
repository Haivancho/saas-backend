package bg.unwe.saasbackend.resource;

import bg.unwe.saasbackend.dto.ProductDTO;
import bg.unwe.saasbackend.model.Product;
import bg.unwe.saasbackend.repo.ProductRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@RequestScoped
@Path("/products")
public class ProductsResource implements CRUDResource<ProductDTO> {

    @Inject
    private ProductRepository productRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(@Valid ProductDTO productDTO) {
        if(productRepository.getProductByName(productDTO.getName()).isPresent()){
            return  Response.status(Response.Status.CONFLICT).build();
        }else {
            Product product = productRepository.save(new Product(productDTO.getName(),productDTO.getDescription(),productDTO.getPrice()));
            return Response.ok(new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getPrice())).build();

        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        List<Product> allProducts = productRepository.getAllProducts();

        ProductDTO[] productS = new ProductDTO[allProducts.size()];

        for (int i = 0; i <allProducts.size() ; i++) {
            Product product = allProducts.get(i);
            productS[i] = new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getPrice());
        }
        return Response.ok(productS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Override
    public Response getById(@PathParam("id") Long id) {
        Optional<Product> product = productRepository.getProductById(id);

        if(product.isPresent()){
            return Response.ok(new ProductDTO(product.get())).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Override
    public Response update(ProductDTO productDTO) {
        Optional<Product> product =productRepository.getProductById(productDTO.getId());

        if(product.isPresent()){
            Product product1 = product.get();
            if (productDTO.getDescription() != null){
                product1.setDescription(product1.getDescription());
            }
            if(productDTO.){

            }
        }
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
