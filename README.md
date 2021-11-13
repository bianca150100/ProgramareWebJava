# Programare-Web-Java 

## Proiect : Cabinet-Medical

Intitulat Sano-Med, proiectul isi propune sa creeze o aplicatie a unui nou cabinet medical, oferindu-le utilizatorilor posibilitatea de a se programa la diferiti medici, in functie de disponibilitatea acestora.

Functionalitatile necesare unei astfel de aplicatiei:
- existenta a 3 tipuri de utilizatori: adminul aplicatiei, pacienti si doctori. In acest mod, proiectul va avea implementata autentificare, crearea contului de utilizator si delogarea din aplicatie.

- Urmatoarele functionalitati vor fi impartite pe tipuri de utilizatori, dupa cum urmeaza:
  
- Adminul aplicatiei:
  - este responsabil de adaugarea doctorilor la nivelul aplicatiei
  - setarea programului de lucru pentru fiecare doctor
  - stergerea unui doctor din aplicatie (eliminarea sa de la nivelul clinicii)
  
- Utilizatorul de tip pacient:
  - vizualizarea listei de doctori si informatiilor despre acestia
  - verificarea disponibilitatii unui medic intr-o anumita ora/zi
  - crearea unui programari intr-o anumita ora/zi
  - vizualizarea listei de programari: programarile vor fi grupate in functie de data in programari trecute, programari actuale(din ziua curenta) si programari viitoare(incepand de ziua urmatoare)
  - anularea unei programari viitoare
  - avand in vedere ca orice utilizator are un cont propriu, el are acces la acesta isi poate edita informatiile personale
  - vizualizarea retelor/diagnosticului primit in urma unei programari direct pe platforma

- Utilizatorul de tip doctor:
  - editarea informatiilor sale personale(program de lucru,specializare, descriere, etc)
  - vizualizarea programarilor sale
  - adaugarea unui retete si a unui diagnostic in urma unei programari
  - vizualizarea istoricului medical al unui pacient(programarile sale ulterioare in cadrul aceleiasi specializari si diagnosticului acordat la momentul anterior)
  

Initial vor fi implementate functionalitati de baza si vor fi introduse doar 2 tipuri de utilizatori: adminul aplicatiei si utilizatorul de tip pacient. 
Functionalitatile ce vor fi implementate in functie de tipul de utilizator:
- Adminul:
  - adaugarea doctorilor
  - setarea programului de lucru pentru doctori
  - editarea informatiilor despre acestia
- Pacient/client:
  - crearea unui cont de tip utilizator, autentificare
  - vizualizarea listei de doctori
  - verificarea disponibilitatii unui medic, intr-o zi/ora aleasa
  - adaugarea unei programari 
  - anularea unei programari
  - vizualizarea listei sale de programari(anterioare, curente, viitoare)

