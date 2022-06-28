package fr.dauphine.miageIf.msa.MSA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Creation du JPA Repository basé sur Spring Data
//  Permet de "générer" toutes sortes de requêtes JPA, sans implementation
public interface OperationRepository extends JpaRepository<Operation, Long>{
    // Query Method findBy
    @Query("SELECT i FROM Operation i where i.id = :id")
    Operation findByNumber(int id);
    @Query("SELECT i FROM Operation i where i.iban = :iban")
    List<Operation> findByIban(String iban);
    //Operation findById(Long number);


    //@Query("DELETE i FROM Operation where i.id = :id")
    Long deleteById(int id);

    List<Operation> findAll();
    @Query("SELECT i FROM Operation i where i.iban = :iban ORDER BY dates DESC")
    List<Operation> findAllByOrderDateDesc(String iban);
    @Query("SELECT i FROM Operation i where i.iban = :iban ORDER BY type ASC")
    List<Operation> findAllByOrderType(String iban);
    @Query("SELECT i FROM Operation i where i.dest = :dest")
    List<Operation> findAllByOrderVirRecu(String dest);
}