# Magazin online

## *1. Principale Features :*
- Exista o varietate de produse.
- Clientul poate sa cumpere orice produs disponibil.
- Clientul poate sa aleaga cantitatea dorita, din fiecare produs.
- Clientul poate elimina un produs ales anterior.
- Functionalitati pentru adaugarea, editarea si stergerea produselor din baza de date, inclusiv gestionarea stocurilor si preturilor.
- Sistem pentru clienti de a vizualiza si gestionare a comenzilor plasate.
- Consexiune la baza de date.

## *2. Proiectare :*
Proiectul este un sistem Java Spring dezvoltat pentru gestionarea produselor, clientilor si comenzilor unei companii. Spring Boot este un framework care simplifica procesul de construire si implementare a aplicatiilor, oferind un set de instrumente si dependente preconfigurate.
Pentru a lucra cu baze de date in Spring Boot, vom folosi modulul Spring Data JPA (Java Persistence API), care este o specificatie pentru gestionarea datelor relationale in aplicatiile Java. Spring Data JPA ofera un set de abstractii pe langa JPA, care faciliteaza lucrul cu bazele de date in Spring Boot.

 Acesta este impartit in mai multe pachete care urmaresc principiile arhitecturii de proiectare a software-ului si separarea responsabilitatilor pentru o dezvoltare mai eficienta si o intretinere mai usoara.
#### *Structura Pachetelor :*
- Entities: Acest pachet contine clasele care reprezinta entitatile principale ale aplicatiei, cum ar fi Product, Client si Order. Aceste clase sunt utilizate pentru a defini structura obiectelor care vor fi manipulate in aplicatie.

- Controller: Pachetul Controller contine clasele care gestioneaza cererile primite de la clienti si le directioneaza catre serviciile corespunzatoare pentru prelucrare. Aceste clase sunt responsabile pentru gestionarea rutei si a logicii de control pentru diversele operatii CRUD (Create, Read, Update, Delete) pe entitati.

- Services: In acest pachet sunt implementate clasele care contin logica de afaceri a aplicatiei. Acestea sunt utilizate pentru a manipula datele si pentru a efectua operatiile specifice asupra entitatilor. De exemplu, ProductService poate gestiona operatiile legate de produse, cum ar fi adaugarea, actualizarea sau stergerea acestora.

- Repositories: Pachetul Repositories contine interfetele sau clasele care definesc operatiile de persistenta a datelor cu baza de date. Acestea folosesc Spring Data JPA sau alte tehnologii de persistenta pentru a facilita interactiunea cu baza de date.

Proiectul este dezvoltat in conformitate cu principiile programarii orientate pe obiecte (OOP). Utilizarea claselor si a obiectelor ajuta la organizarea si structurarea codului in entitati logice, iar folosirea de interfete faciliteaza testarea unitara si schimbul de implementari. De asemenea, utilizarea anotarilor Spring faciliteaza integrarea cu alte componente ale aplicatiei si furnizeaza functionalitati extinse, cum ar fi gestionarea dependentelor, injectarea de dependente si gestionarea tranzactiilor.

## *3. Implementare :*
In pachetul Entities, am definit trei clase: Orders, Products si Clients, care reprezinta datele cu care vom popula baza de date. Aceste clase sunt esentiale pentru modelarea structurii de date si relatiilor dintre entitati in cadrul aplicatiei.
Pentru a marca aceste clase ca entitati JPA, am folosit anotarea @Entity. Aceasta specifica faptul ca fiecare clasa reprezinta un tabel in baza de date. Prin anotarea @Table, putem specifica numele tabelului asociat fiecarei clase in cazul in care acesta difera de numele clasei.
Pentru a defini cheia primara a fiecarui tabel, am folosit anotarea @Id. Aceasta indica ca campul marcat este cheia primara a tabelului din baza de date. Pentru a specifica modul in care valorile cheii primare sunt generate, am utilizat anotarea @GeneratedValue. Am folosit GenerationType.IDENTITY, ceea ce inseamna ca valorile cheii primare sunt generate automat de catre baza de date.
In plus, am creat o interfata Repository care extinde JpaRepository furnizata de Spring Data JPA. Aceasta interfata defineste un set de metode pentru a efectua operatiile CRUD (Create, Read, Update, Delete) pe baza de date. Prin adnotarea @Repository, indicam ca aceasta interfata este o componenta Spring care gestioneaza interactiunea cu baza de date. Aceasta faciliteaza integrarea si gestionarea operatiunilor de persistenta in aplicatie. Folosind aceasta interfata, putem accesa si manipula datele in baza de date folosind operatiuni standardizate.

## *4. Baza de date*
Baza de date cuprinde 4 tabele:
#### ***CLIENTS***
- id bigint (PK), 
- address varchar(255),
- email varchar(255),
- is_admin bit(1),
- password varchar(255),
- user_nane varchar(255)

#### ***PRODUCTS***
- id_prod bigint (PK),
- description varchar(255) 
- name_prod varchar(255) 
- price double 
- stock int

#### ***ORDERS***
- id_order bigint (PK) 
- order_date datetime(6) 
- price double 
- quantity int 
- client_id bigint

#### ***ORDERS_PRODUCT***
- order_id bigint 
- product_id bigint

Iar principalele relatii dintre ele sunt:
- many to many intre Orders si Products
- many to one intre Orders si Clients

![Screenshot (782)](https://github.com/IonasAndreea/Online_Store/assets/126804831/4458933e-8ace-4d43-afd9-4c28d2377a95)


Pentru a gestiona relatia de many to many intre comenzile si produsele lor, este folosita o tabela de legatura numita "orders_product". Aceasta tabela are o coloana pentru "order_id", care este foregin key catre "id_order" in tabelul "orders", si o coloana pentru "product_id", care este foregin key catre "id_prod" in tabelul "products".
Entitatea "Clients" este legata de entitatea "Orders" printr-o relatie de many to one, deoarece un client poate plasa mai multe comenzi, dar o comanda este plasata de un singur client.

Aceasta schema permite sa fie gestionate comenzile, produsele si clientii lor in cadrul unei aplicatii, stabilind relatii intre aceste entitati in baza de date pentru a asigura coerenta si integritatea datelor.

## *5. Observer Pattern*
Pattern-ul Observer este unul dintre cele mai utilizate pattern-uri de proiectare in programare, iar principalul sau scop este sa permita obiectelor sa fie notificate automat atunci cand se produce o schimbare intr-un alt obiect.

Acesta este util atunci cand avem un obiect (denumit subiect) care trebuie sa notifice alte obiecte (denumite observatori sau ascultatori) atunci cand starea sa se schimba, fara ca observatorii sa fie constienti de existenta altor observatori. Prin intermediul acestui pattern, subiectul si observatorii sunt decuplate, ceea ce duce la o structura modulara si usor de intretinut.

Pentru a realiza Observer pattern, voi notifica clientii in momentul in care stocul produselor urmarite se modifica.

Am creat un pachet in care am creat 2 interfete,  una pentru Subject(StockProduct)si una pentru Observer(StockObserver). Aceste interfete sunt implementate de clasele Cilent si Products. De asemenea, Product va avea o lista de clienti care sunt interesati daca stocul se modifica.

Un pattern Observer este unul dintre cele mai utile pattern-uri de design in programarea orientata pe obiecte si este adesea utilizat in dezvoltarea de aplicatii pentru a implementa un sistem de notificare asupra modificarilor de stare ale unui obiect (numit subiect) catre unul sau mai multi observatori.
In contextul relatiei dintre clienti si produse, implementarea unui pattern Observer ar putea fi benefica pentru a monitoriza schimbarile de stare ale produselor si pentru a notifica clientii atunci cand aceste schimbari au loc. 


## *6. API's*
In contextul dezvoltarii web, API-ul (Interfata de Programare a Aplicatiilor) defineste un set de reguli si protocoale care permit aplicatiilor software diferite sa comunice intre ele. In contextul serviciilor web RESTful, endpoint-urile API sunt URL-uri sau URI-uri (Identificatori Uniformi de Resurse) specifice care sunt expuse de un server pentru a interactiona cu diferite resurse sau pentru a efectua actiuni specifice.

### *Client API's: *
Endpoint-urile API folosite de client sunt:
GET /clients: Endpoint pentru a obtine o lista cu toti clientii.
GET /clients/{id}: Endpoint pentru a obtine un client specific dupa ID-ul sau.
POST /clients/insertClient: Endpoint pentru a crea un nou client.
PUT /clients/{id}: Endpoint pentru a actualiza un client existent dupa ID-ul sau.
DELETE /clients/{id}: Endpoint pentru a sterge un client existent dupa ID-ul sau.

Endpoint pentru Obtinerea Tuturor Clientilor:
Acest endpoint recupereaza o lista a tuturor clientilor inregistrti in prezent in sistem. In urma unei solicitari reusite, returneaza un array care contine detalii despre fiecare client. Acest endpoint este utilizat in mod obisnuit de catre administratori sau aplicatiile care necesita o vedere cuprinzatoare a tuturor clientilor inregistrati in sistem.

Endpoint pentru Obtinerea Clientului dupa ID:
Endpoint-ul pentru Obtinerea Clientului dupa ID permite obtinerea detaliilor unui client specific pe baza identificatorului lor unic.Acest endpoint este util pentru obtinerea detaliilor individuale ale clientului pentru afisare sau procesare ulterioara.

Endpoint pentru Crearea Clientului:
Endpoint-ul pentru Crearea Clientului faciliteaza adaugarea unui nou client in sistem. In urma crearii reusite, acest endpoint returneaza obiectul clientului nou creat impreuna cu un cod de stare care indica executarea reusita. Acest endpoint este utilizat in mod obisnuit atunci cand se adauga noi clienti in sistem prin solicitari API.

Endpoint pentru Actualizarea Clientului:
Endpoint-ul pentru Actualizarea Clientului permite modificarea informatiilor unui client existent in sistem. Prin furnizarea identificatorului unic al clientului si a unui obiect JSON care contine detaliile actualizate, acest endpoint actualizeaza informatiile clientului in consecinta. In urma actualizarii reusite, returneaza obiectul clientului actualizat impreuna cu un cod de stare care indica executarea reusita. Acest endpoint este utilizat in mod obisnuit pentru editarea detaliilor clientului.

Endpoint pentru Stergerea Clientului:
Endpoint-ul pentru Stergerea Clientului permite eliminarea unui client specific din sistem pe baza identificatorului lor unic. Prin furnizarea ID-ului clientului ca parametru de cale, acest endpoint sterge inregistrarea clientului corespunzatoare din sistem. In urma stergerii reusite, returneaza un cod de stare de succes care indica faptul ca clientul a fost eliminat cu succes. Acest endpoint este utilizat in mod obisnuit atunci cand clientii doresc sa se dezaboneze din sistem sau atunci cand actiunile administrative necesita eliminarea unor inregistrari specifice de clienti.

### *Product API's: *
Endpoint pentru Obtinerea Tuturor Produselor:
Endpoint-ul pentru Obtinerea Tuturor Produselor recupereaza o lista a tuturor produselor disponibile in prezent in sistem. In urma unei solicitari reusite, returneaza un array care contine detalii despre fiecare produs. Acest endpoint este utilizat in mod obisnuit de catre aplicatii care necesita o vedere cuprinzatoare a tuturor produselor disponibile in sistem.

Endpoint pentru Obtinerea Produsului dupa ID:
Endpoint-ul pentru Obtinerea Produsului dupa ID permite obtinerea detaliilor unui produs specific pe baza identificatorului sau unic. Prin furnizarea ID-ului produsului ca parametru de cale, acest endpoint returneaza informatii detaliate despre produs. Acest endpoint este util pentru obtinerea detaliilor individuale ale produsului pentru afisare sau procesare ulterioara.

Endpoint pentru Crearea Produsului:
Endpoint-ul pentru Crearea Produsului faciliteaza adaugarea unui nou produs in sistem. Acesta accepta un obiect JSON care reprezinta detaliile produsului. In urma crearii reusite, acest endpoint returneaza obiectul produsului nou creat impreuna cu un cod de stare care indica executarea reusita. Acest endpoint este utilizat in mod obisnuit atunci cand se adauga noi produse in sistem prin solicitari API.

Endpoint pentru Actualizarea Produsului:
Endpoint-ul pentru Actualizarea Produsului permite modificarea informatiilor unui produs existent in sistem. Prin furnizarea identificatorului unic al produsului si a unui obiect JSON care contine detaliile actualizate, acest endpoint actualizeaza informatiile produsului in consecinta. In urma actualizarii reusite, returneaza obiectul produsului actualizat impreuna cu un cod de stare care indica executarea reusita. Acest endpoint este utilizat in mod obisnuit pentru editarea detaliilor produsului.

Endpoint pentru Stergerea Produsului:
Endpoint-ul pentru Stergerea Produsului permite eliminarea unui produs specific din sistem pe baza identificatorului sau unic. Prin furnizarea ID-ului produsului ca parametru de cale, acest endpoint sterge inregistrarea produsului corespunzatoare din sistem. In urma stergerii reusite, returneaza un cod de stare de succes care indica faptul ca produsul a fost eliminat cu succes. Acest endpoint este utilizat in mod obisnuit atunci cand produsele sunt intrerupte sau atunci cand actiunile administrative necesita eliminarea unor inregistrari specifice de produse.

### *Order API's:*
Endpoint pentru Obtinerea Tuturor Comenzilor:
Endpoint-ul pentru Obtinerea Tuturor Comenzilor recupereaza o lista a tuturor comenzilor plasate in prezent in sistem. In urma unei cereri reusite, acesta returneaza un array ce contine detalii despre fiecare comanda. Acest endpoint este folosit in mod obisnuit de administratori sau aplicatii care necesita o vedere cuprinzatoare a tuturor comenzilor din sistem.

Endpoint pentru Obtinerea Comenzii dupa ID:
Endpoint-ul pentru Obtinerea Comenzii dupa ID permite obtinerea detaliilor unei comenzi specifice pe baza identificatorului sau unic. Prin furnizarea ID-ului comenzii ca parametru de cale, acest endpoint returneaza informatii detaliate despre comanda. Acest endpoint este util pentru obtinerea detaliilor individuale ale comenzii pentru afisare sau procesare ulterioara.

Endpoint pentru Crearea Comenzii:
Endpoint-ul pentru Crearea Comenzii faciliteaza plasarea unei noi comenzi in sistem. Acesta accepta un obiect JSON care reprezinta detaliile comenzii. In urma crearii reusite, acest endpoint returneaza obiectul comenzii nou create impreuna cu un cod de stare care indica executia reusita. Acest endpoint este folosit in mod obisnuit atunci cand clientii plaseaza noi comenzi prin solicitari API.

Endpoint pentru Actualizarea Comenzii:
Endpoint-ul pentru Actualizarea Comenzii permite modificarea informatiilor unei comenzi existente in sistem. Prin furnizarea identificatorului unic al comenzii si a unui obiect JSON care contine detaliile actualizate, acest endpoint actualizeaza informatiile comenzii in consecinta. In urma actualizarii reusite, acesta returneaza obiectul comenzii actualizate impreuna cu un cod de stare care indica executia reusita. Acest endpoint este folosit in mod obisnuit pentru editarea detaliilor comenzii, cum ar fi modificarile produselor, actualizarile clientilor sau ajustarile preturilor.

Endpoint pentru Stergerea Comenzii:
Endpoint-ul pentru Stergerea Comenzii permite eliminarea unei comenzi specifice din sistem pe baza identificatorului sau unic. Prin furnizarea ID-ului comenzii ca parametru de cale, acest endpoint sterge inregistrarea corespunzatoare a comenzii din sistem. In urma stergerii reusite, acesta returneaza un cod de stare de succes care indica faptul ca comanda a fost eliminata cu succes. Acest endpoint este folosit in mod obisnuit atunci cand comenzile sunt anulate sau atunci cand actiunile administrative necesita eliminarea unor inregistrari specifice de comenzi.



## *7. Diagrama UML:*

![Diagrama_Clase_Online_Store](https://github.com/IonasAndreea/Online_Store/assets/126804831/dbd4fb81-e324-44cb-9d19-7ae02c97bdbd)

In backend-ul aplicatiei dezvoltat in Java Spring Boot, am structurat codul pe baza principiului de separare a responsabilitatilor, ceea ce faciliteaza intretinerea si extinderea aplicatiei. Iata o descriere detaliata a fiecarei componente si a modului in care acestea colaboreaza pentru a oferi functionalitatile necesare.

### *Entity*
Entitatile reprezinta clasele de baza care corespund tabelelor din baza de date. Am clase de entitate pentru Client, Produs si Order, fiecare mapata la o tabela specifica din baza de date. Aceste clase contin campuri care corespund coloanelor din tabelele respective si sunt adnotate cu anotari precum @Entity, @Table, @Id, @GeneratedValue si alte adnotari JPA (Java Persistence API) pentru a defini relatiile dintre campuri si tabele. Entitatile sunt utilizate pentru a modela datele din aplicatie si pentru a facilita operatiile de persistare.

### *Services*
Serviciile reprezinta clasa intermediara care contine logica de afaceri a aplicatiei. Am cate un serviciu pentru fiecare entitate: ClientService, ProductService si OrderService. Aceste clase sunt adnotate cu @Service si contin metode care utilizeaza repository-urile pentru a efectua operatii complexe, cum ar fi salvarea, actualizarea, stergerea. Serviciile separa logica de afaceri de logica de acces la date, ceea ce faciliteaza testarea si intretinerea codului.

### *Repository*
Repository-urile sunt interfete care extind JpaRepository, furnizate de Spring Data JPA. Aceste interfete ofera metode CRUD (Create, Read, Update, Delete) implicite pentru a interactiona cu baza de date, fara a fi necesar sa scriem cod SQL manual.

### *Controller*
Controlerele sunt clase adnotate cu @RestController care gestioneaza cererile HTTP si trimit raspunsurile corespunzatoare. Fiecare controler este responsabil pentru un set de endpoint-uri RESTful care corespund operatiilor de creare, citire, actualizare si stergere pentru entitatile respective. De exemplu, ClientController contine metode mapate la endpoint-uri precum /clients, /clients/{id}, /clients/search. Aceste metode primesc cereri HTTP de la front-end, apeleaza metodele corespunzatoare din serviciu si returneaza raspunsuri JSON. Controlerele sunt punctele de intrare in aplicatie, gestionand direct interactiunea cu utilizatorii.

### *Principiul de funcionare*
Cand un utilizator interactioneaza cu front-end-ul aplicatiei, cum ar fi prin adaugarea unui nou client, o cerere HTTP POST este trimisa catre endpoint-ul relevant din ClientController. Controlerul primeste cererea si apeleaza metoda corespunzatoare din ClientService, care, la randul sau, utilizeaza ClientRepository pentru a salva noul client in baza de date. Dupa ce operatia este finalizata, controlerul trimite un raspuns de succes inapoi catre front-end. Acest flux de date este similar pentru toate celelalte operatii (vizualizare, cautare, editare, stergere) si entitati (Client, Produs, Order), asigurand o arhitectura clara si modulara.
Aceasta structura modulara faciliteaza itretinerea si scalabilitatea aplicatiei, permitand adaugarea usoara de noi functionalitati sau modificarea celor existente fara a afecta alte parti ale sistemului.


### *Use Case*

![Use_Case](https://github.com/IonasAndreea/Online_Store/assets/126804831/c9f9e06c-b5cf-4c66-bfa9-c8d22293c096)











