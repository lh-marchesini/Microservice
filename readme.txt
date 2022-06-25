Pour faire marcher l'application, il faut :

1 - executer MsaApplication dans l'éditeur de spring boot (ex IntelliJ) :
2 - se diriger vers la console : http://localhost:8000/h2-console/
3 - se connecter avec l'URL : jdbc:h2:mem:testdb
4 - récuperer les tables souhaités, soit ListIban ou Operation.
4 - ouvrir le lien pour executer les requêtes REST: ( il est préferable d'utiliser Google Chrome et utiliser l'extention proposé par le site Web suivant)  

https://reqbin.com/req/c-w7oitglz/convert-curl-to-http-request#:~:text=Create%20a%20Curl%20POST%20request,%2DF%20command%2Dline%20option.&text=Click%20the%20%22Run%22%20to%20execute,see%20the%20generated%20HTTP%20request.&text=Click%20the%20%22Raw%22%20tab%20on%20the%20right%20pane%20to,see%20the%20server's%20HTTP%20response.

5 - requêtes possibles (les deux premiers "--" sont à supprimer): 

#création d'un nouveau IBAN avec une dest vide, type, interet, frais et solde.
--curl -X POST localhost:8000/iban -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567855555", "dest": "", "type":"Compte courant", "interet":0.0, "frais":"gratuit", "solde":6363636.33}'

récuperer les informations d'un Iban :
--curl -X GET localhost:8000/iban/FR7630004000030987654321098

lister la liste des Ibans :
--curl -X GET localhost:8000/iban/list

supprimer un Iban de la liste :
--curl -X DELETE localhost:8000/iban/FR7630004000030987654321098

mettre à jour les information d'un Iban :
--curl -X PUT localhost:8000/iban/FR7630004000030987654321098 -H 'Content-type:application/json' -d '{"type":"", "interet":1.0, "frais":"", "solde": ""}'


faire (ajouter) une opération ed type virement de 100 :
--curl -X POST localhost:8000/operation -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567890143", "dest": "FR7630004000030987654321098", "type":"Virement", "montant":100.0, "date": "24/06/2022 12:21:12"}'

récuperer la 3ème opération dans la liste :
--curl -X GET localhost:8000/operation/3

supprimer l'opération 0 : 
--curl -X DELETE localhost:8000/operation/0

modifier l'opération 3 :
--curl -X PUT localhost:8000/operation/3 -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567890143", "dest": "FR7630004000030987654321098", "type":"Virement", "montant":100.0, "date": "24/06/2022 12:21:12"}'

Lister toutes les opérations effectuées :
--curl -X GET localhost:8000/operation/getAllOp

lister mes opérations faites :
--curl -X GET localhost:8000/mesoperations/FR7630004000031234567890143

Lister les opérations par date:
--curl -X GET localhost:8000/operation/getopbydate/FR7630004000031234567890143

Lister les opérations par type:
--curl -X GET localhost:8000/operation/getopbytype/FR7630004000031234567890143

récuperer la liste des virements reçus :
--curl -X GET localhost:8000/operation/virementrecu/FR7630004000030987654321098