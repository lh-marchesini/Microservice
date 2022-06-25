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
  private TauxChangeRepository repository;

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

  @GetMapping("/operation/{id}")
    public Operation getOperation(@PathVariable int id) {
      Operation operation = repository_operation.findByNumber(id);
      return operation;
  }

  @GetMapping("/mesoperations/{iban}")
  public List<Operation> getByIban(@PathVariable String iban) {
    List<Operation> operation = repository_operation.findByIban(iban);
    return operation;
  }

  @GetMapping("/operation/getAllOp")
  public List<Operation> getListOperation() {
    List<Operation> operation = repository_operation.findAll();
    return operation;
  }

  @GetMapping("/operation/getopbydate/{iban}")
  public List<Operation> getListOperationByDate(@PathVariable String iban) {
    List<Operation> operation = repository_operation.findAllByOrderDateDesc(iban);
    return operation;
  }

  @GetMapping("/operation/getopbytype/{iban}")
  public List<Operation> findAllByOrderType(@PathVariable String iban) {
    List<Operation> operation = repository_operation.findAllByOrderType(iban);
    return operation;
  }

  @GetMapping("/operation/virementrecu/{dest}")
  public List<Operation> findAllByOrderVirRecu(@PathVariable String dest) {
    List<Operation> operation = repository_operation.findAllByOrderVirRecu(dest);
    return operation;
  }

  @PostMapping("/iban")
  public ListIban newIban(@RequestBody @NotNull ListIban newIban) {
    return repository_iban.save(newIban);
  }


  @PostMapping("/operation")
  public Operation newOperation(@RequestBody @NotNull Operation newOp) {
    ListIban compt_src = repository_iban.findByIban(newOp.getIban());
    ListIban compt_dest = repository_iban.findByIban(newOp.getDest());
    double value1, value2, ans;
    value2 = newOp.getMontant();
    if (newOp.getType().equals("Virement")){
      value1 = compt_src.getSolde();
      compt_src.setSolde(value1 - value2);
      value1 = compt_dest.getSolde();
      compt_dest.setSolde(value1 + value2);
      repository_iban.save(compt_src);
      repository_iban.save(compt_dest);
    } else if (newOp.getType().equals("Retrait")) {
      value1 = compt_src.getSolde();
      compt_src.setSolde(value1 - value2);
      repository_iban.save(compt_src);
    } else if (newOp.getType().equals("Versement")) {
      value1 = compt_dest.getSolde();
      compt_dest.setSolde(value1 + value2);
      repository_iban.save(compt_dest);
    }
    return repository_operation.save(newOp);
  }

  @Transactional
  @DeleteMapping("/iban/{iban}")
  public Long deleteIban(@PathVariable String iban) {
    return repository_iban.deleteByIban(iban);
  }

  @Transactional
  @DeleteMapping("/operation/{id}")
  public void deleteByOpId(@PathVariable int id) { repository_operation.deleteById(id); }


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

  @PutMapping("/operation/{id}")
  public Operation newOperation(@RequestBody Operation newOp,@PathVariable int id)
  {
    //Optional<Operation> operation = repository_operation.findById(op);
    Operation operation = repository_operation.findByNumber(id);
    if (newOp.getMontant() != null)
      operation.setMontant(operation.getMontant());
    if (newOp.getDest() != "")
      operation.setDest(newOp.getDest());
    if (newOp.getIban() != "")
      operation.setIban(newOp.getIban());
    if (newOp.getType() != "")
      operation.setType(newOp.getType());
    if (newOp.getDate() != "")
      operation.setDate(newOp.getDate());
    return repository_operation.save(operation);
  }

  public TauxChange retrouveTauxChange
          (@PathVariable String source, @PathVariable String dest){
    TauxChange tauxChange =
            repository.findBySourceAndDest(source, dest);
    /*tauxChange.setPort(
        Integer.parseInt(environment.getProperty("local.server.port")));*/
    return tauxChange;
  }

}