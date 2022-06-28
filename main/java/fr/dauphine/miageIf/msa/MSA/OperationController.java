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
public class OperationController {

    // Spring se charge de la création d'instance
    @Autowired
    private Environment environment;

    // Spring se charge de la création d'instance
    @Autowired
    private ListIbanRepository repository_iban;

    @Autowired
    private OperationRepository repository_operation;

    @GetMapping("/operation/{id}")
    public Operation getOperation(@PathVariable int id) {
        Operation operation = repository_operation.findByNumber(id);
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
    @DeleteMapping("/operation/{id}")
    public void deleteByOpId(@PathVariable int id) { repository_operation.deleteById(id); }


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

}