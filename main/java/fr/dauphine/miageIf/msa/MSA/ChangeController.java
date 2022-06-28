package fr.dauphine.miageIf.msa.MSA;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import java.util.Collection;
import java.util.Optional;

@RestController
public class ChangeController {

  // Spring se charge de la création d'instance
  @Autowired
  private Environment environment;

  // Spring se charge de la création d'instance
  @Autowired
  private ListIbanRepository repository_iban;

  @Autowired
  private OperationRepository repository_operation;

  @GetMapping("/iban/{iban}")
  public ListIban retrouveIban(@PathVariable String iban) {
    ListIban listIban = repository_iban.findByIban(iban);
    return listIban;
  }

  @GetMapping("/iban/list")
  public List<ListIban> retrouveListIban() {
    List<ListIban> listIban = repository_iban.findAll();
    return listIban;
  }

  @GetMapping("/mesoperations/{iban}")
  public List<Operation> getByIban(@PathVariable String iban) {
    List<Operation> operation = repository_operation.findByIban(iban);
    return operation;
  }



  @PostMapping("/iban")
  public ListIban newIban(@RequestBody @NotNull ListIban newIban) {
    return repository_iban.save(newIban);
  }



  @Transactional
  @DeleteMapping("/iban/{iban}")
  public Long deleteIban(@PathVariable String iban) {
    return repository_iban.deleteByIban(iban);
  }

  @PutMapping("/iban/{iban}")
  public ListIban newIban(@RequestBody ListIban newIban,@PathVariable String iban)
  {
    ListIban listIban = repository_iban.findByIban(iban);
    if (newIban.getSolde() != null)
      listIban.setSolde(newIban.getSolde());
    if (newIban.getInteret() != null)
      listIban.setInteret(newIban.getInteret());
    if (newIban.getFrais() != "")
      listIban.setFrais(newIban.getFrais());
    if (newIban.getType() != "")
      listIban.setFrais(newIban.getFrais());
    return repository_iban.save(listIban);
  }

}