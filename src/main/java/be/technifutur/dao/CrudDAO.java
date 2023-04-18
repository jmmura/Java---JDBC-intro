package be.technifutur.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<TENTITY,TID>{
    //Create
    void insert (TENTITY entity); //TENTITY est un paramètre de généricité


    //Read
    List<TENTITY> getAll();
    Optional<TENTITY> getOne(TID id);//Optional: objet qui contient  qqch ou pas -> peut = null et evite NullpointerException

    //Update
    void update(TID id, TENTITY entity);

    //Delete
    void delete(TID id);

}

