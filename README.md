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
Pentru a lucra cu baze de date in Spring Boot, vom folosi modulul Spring Data JPA (Java Persistence API), care este o specificatie pentru gestionarea datelor relationale în aplicatiile Java. Spring Data JPA ofera un set de abstractii pe langa JPA, care faciliteaza lucrul cu bazele de date in Spring Boot.

 Acesta este impatțit in mai multe pachete care urmaresc principiile arhitecturii de proiectare a software-ului si separarea responsabilitatilor pentru o dezvoltare mai eficienta si o intretinere mai usoara.
#### *Structura Pachetelor :*
- Entities: Acest pachet contine clasele care reprezinta entitatile principale ale aplicatiei, cum ar fi Product, Client si Order. Aceste clase sunt utilizate pentru a defini structura obiectelor care vor fi manipulate in aplicatie.

- Controller: Pachetul Controller contine clasele care gestioneaza cererile primite de la clienti si le directionează catre serviciile corespunzătoare pentru prelucrare. Aceste clase sunt responsabile pentru gestionarea rutei si a logicii de control pentru diversele operații CRUD (Create, Read, Update, Delete) pe entitati.

- Services: In acest pachet sunt implementate clasele care contin logica de afaceri a aplicatiei. Acestea sunt utilizate pentru a manipula datele si pentru a efectua operatiile specifice asupra entitatilor. De exemplu, ProductService poate gestiona operatiile legate de produse, cum ar fi adaugarea, actualizarea sau stergerea acestora.

- Repositories: Pachetul Repositories contine interfetele sau clasele care definesc operatiile de persistenta a datelor cu baza de date. Acestea folosesc Spring Data JPA sau alte tehnologii de persistenta pentru a facilita interactiunea cu baza de date.

Proiectul este dezvoltat in conformitate cu principiile programarii orientate pe obiecte (OOP). Utilizarea claselor si a obiectelor ajuta la organizarea si structurarea codului in entitati logice, iar folosirea de interfete faciliteaza testarea unitară si schimbul de implementari. De asemenea, utilizarea anotarilor Spring faciliteaza integrarea cu alte componente ale aplicației si furnizeaza functionalitati extinse, cum ar fi gestionarea dependentelor, injectarea de dependente si gestionarea tranzactiilor.

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
- user_nane varchar(255))

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

Pentru a gestiona relatia de many to many intre comenzile si produsele lor, este folosita o tabela de legatura numita "orders_product". Aceasta tabela are o coloana pentru "order_id", care este foregin key catre "id_order" in tabelul "orders", si o coloana pentru "product_id", care este foregin key catre "id_prod" in tabelul "products".
Entitatea "Clients" este legata de entitatea "Orders" printr-o relatie de many to one, deoarece un client poate plasa mai multe comenzi, dar o comanda este plasata de un singur client.

Aceasta schema permite să fie gestionate comenzile, produsele si clientii lor in cadrul unei aplicatii, stabilind relatii intre aceste entitati in baza de date pentru a asigura coerenta si integritatea datelor.

![Screenshot (782)](https://github.com/IonasAndreea/Online_Store/assets/126804831/4458933e-8ace-4d43-afd9-4c28d2377a95)


## *4. Diagrama UML *



![Diagrama_Clase_Online_Store](https://github.com/IonasAndreea/Online_Store/assets/126804831/59bd4a7f-8808-4c87-93c1-a33612493f4a)



















