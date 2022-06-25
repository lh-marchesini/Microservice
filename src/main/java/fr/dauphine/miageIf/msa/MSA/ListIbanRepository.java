package fr.dauphine.miageIf.msa.MSA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

// Creation du JPA Repository basé sur Spring Data
//  Permet de "générer" toutes sortes de requêtes JPA, sans implementation
public interface ListIbanRepository extends JpaRepository<ListIban, Long>{
  // Query Method findBy
  @Query("SELECT i FROM ListIban i where i.iban = :iban")
  ListIban findByIban(String iban);


  //@Query("DELETE FROM ListIban where iban = :iban")
  Long deleteByIban(String iban);
  List<ListIban> findAll();
}