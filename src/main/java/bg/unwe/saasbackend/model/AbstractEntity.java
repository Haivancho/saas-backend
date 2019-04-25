package bg.unwe.saasbackend.model;


import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
    private Long id;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
