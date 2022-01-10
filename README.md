# Programare-Web-Java 

## Proiect : Cabinet-Medical - [DEPRECATED - SEE FILE : documentatie.pdf]

Intitulat Sano-Med, proiectul isi propune sa creeze o aplicatie a unui nou cabinet medical, oferindu-le utilizatorilor posibilitatea de a se programa la diferiti medici, in functie de disponibilitatea acestora.

Functionalitatile necesare unei astfel de aplicatiei:
- existenta a 3 tipuri de utilizatori: adminul aplicatiei, pacienti si doctori. In acest mod, proiectul va avea implementata autentificare, crearea contului de utilizator si delogarea din aplicatie.

- Urmatoarele functionalitati vor fi impartite pe tipuri de utilizatori, dupa cum urmeaza:
  
- Adminul aplicatiei este responsabil de:
  - adaugarea doctorilor la nivelul aplicatiei
  - setarea programlui de lucru pentru fiecare doctor
  - stergerea unui doctor din aplicatie (eliminarea sa de la nivelul clinicii)
  
- Utilizatorul de tip pacient are acces la:
  - vizualizarea listei de doctori si informatiilor despre acestia
  - verificarea disponibilitatii unui medic intr-o anumita ora/zi
  - crearea unui programari intr-o anumita ora/zi
  - vizualizarea listei de programari: programarile vor fi grupate in functie de data in programari trecute, programari actuale(din ziua curenta) si programari viitoare(incepand de ziua urmatoare)
  - anularea unei programari viitoare
  - avand in vedere ca orice utilizator are un cont propriu, isi poate edita informatiile personale
  - vizualizarea retetelor/diagnosticului primit in urma unei programari direct pe platforma

- Utilizatorul de tip doctor are acces la:
  - editarea informatiilor sale personale(program de lucru,specializare, descriere, etc)
  - vizualizarea programarilor sale
  - adaugarea unui retete si a unui diagnostic in urma unei programari
  - vizualizarea istoricului medical al unui pacient(programarile sale ulterioare in cadrul aceleiasi specializari si diagnosticul acordat la momentul anterior)
  

Initial vor fi implementate functionalitati de baza si vor fi introduse doar 2 tipuri de utilizatori: adminul aplicatiei si utilizatorul de tip pacient. 
Functionalitatile ce vor fi implementate in functie de tipul de utilizator:
- Adminul:
  - adaugarea doctorilor (vor fi setate numele, prenumele, specializarea, experienta, adresa de email, numarul de telefon si descrierea acestora)
  - setarea programului de lucru pentru doctori (fiecare doctor va avea un program propriu in fiecare zi a saptamanii. Va fi permis setarea unui singur interval orar pentru fiecare- de exemplu, luni, 09-12. Zilele in care acesta este liber vor fi introduse sub forma unui string format dintr-o "-" )
  - editarea informatiilor despre acestia (existenta unui api care plecand de la id-ul dat ca si parametru, gaseste obiectul de tip doctor de la nivelul bazei de date si ii updateaza informatiile personale: nume, prenume, adresa, telefon, etc)
- Pacient/client:
  - crearea unui cont de tip utilizator, autentificare (la autentificare, utilizatorul isi va introduce numele, prenumele, adresa de email si parola)
  - vizualizarea listei de doctori 
  - verificarea disponibilitatii unui medic, intr-o zi/ora aleasa (id-ul medicului, ziua si ora se vor da ca parametru, iar raspunsul va reda daca medicul respectiv este disponibil in intervalul ales. Se presupune ca orice programare dureaza o ora, iar acestea se pot crea de la fix la fix)
  - adaugarea unei programari (se vor specifica id-ul medicului, ziua si ora si se va crea o programare clientului logat in aplicatie)
  - anularea unei programari (anularea se face dupa id-ul programarii, adaugat in parametrul request-ului)
  - vizualizarea listei sale de programari(anterioare, curente, viitoare)

