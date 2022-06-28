insert into list_iban(iban, type_compte, interet, frais_compte, solde)
values('FR7630004000031234567890143',  'Compte courant', 0.0, 'gratuit', 10087.54);
insert into list_iban(iban, type_compte, interet, frais_compte, solde)
values('FR7630004000030987654321098',  'Compte courant', 0.0, 'gratuit', 638373.87);

insert into operation(id, iban, iban_dest, type, montant, dates)
values(0, 'FR7630004000031234567890143', 'FR7630004000030987654321098', 'Virement', 10.0, '21/04/2022 16:20:69');
insert into operation(id, iban, iban_dest, type, montant, dates)
values(1, 'FR7630004000031234567890143', 'FR7630004000030987654321098', 'Virement', 20.0, '21/04/2022 21:02:45');
insert into operation(id, iban, iban_dest, type, montant, dates)
values(2, 'FR7630004000031234567890143', '', 'Retrait', 20.0, '21/04/2022 21:02:45');

--https://reqbin.com/req/c-w7oitglz/convert-curl-to-http-request#:~:text=Create%20a%20Curl%20POST%20request,%2DF%20command%2Dline%20option.&text=Click%20the%20%22Run%22%20to%20execute,see%20the%20generated%20HTTP%20request.&text=Click%20the%20%22Raw%22%20tab%20on%20the%20right%20pane%20to,see%20the%20server's%20HTTP%20response.
--curl -X POST localhost:8000/iban -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567855555", "dest": "", "type":"Compte courant", "interet":0.0, "frais":"gratuit", "solde":6363636.33}'
--curl -X GET localhost:8000/iban/FR7630004000030987654321098
--curl -X GET localhost:8000/iban/list
--curl -X DELETE localhost:8000/iban/FR7630004000030987654321098
--curl -X PUT localhost:8000/iban/FR7630004000030987654321098 -H 'Content-type:application/json' -d '{"type":"", "interet":1.0, "frais":"", "solde": ""}'

--curl -X POST localhost:8000/operation -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567890143", "dest": "FR7630004000030987654321098", "type":"Retrait", "montant":100.0, "date": "24/06/2022 12:21:12"}'
--curl -X GET localhost:8000/operation/4
--curl -X DELETE localhost:8000/operation/0
--curl -X PUT localhost:8000/operation/4 -H 'Content-type:application/json' -d '{"iban": "FR7630004000031234567890143", "dest": "FR7630004000030987654321098", "type":"Virement", "montant":100.0, "date": "24/06/2022 12:21:12"}'

--curl -X GET localhost:8000/operation/getAllOp
--curl -X GET localhost:8000/mesoperations/FR7630004000031234567890143
--curl -X GET localhost:8000/operation/getopbydate/FR7630004000031234567890143
--curl -X GET localhost:8000/operation/getopbytype/FR7630004000031234567890143
--curl -X GET localhost:8000/operation/virementrecu/FR7630004000030987654321098


