package be.technifutur.models;
import lombok.Data;

@Data
public class Supplier {
    private long id;
    private String name;
    private String address;
    private String phone;
}
