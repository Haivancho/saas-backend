package bg.unwe.saasbackend.resource;


import javax.ws.rs.core.Response;

public interface CRUDResource<T> {

    Response create(T entityDTO);
    Response getAll();
    Response getById(Long id);
    Response update(T entity);
    Response delete(Long id);
}
