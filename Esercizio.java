
import java.util.Scanner;


class Esercizio {
    public class Main {
    public static void main(String[] args) {
        Lista<String> rubrica = new Lista<>();
        
        System.out.println("=== RUBRICA CONTATTI ===\n");
        
        // Aggiungo contatti con vari metodi
        rubrica.aggiungiInTesta("Alice");
        rubrica.aggiungiInCoda("Bob");
        rubrica.aggiungiInCoda("Charlie");
        rubrica.aggiungiInTesta("Zoe");
        rubrica.aggiungiInPosizione("Marco", 2);
        
        System.out.println("Lista iniziale:");
        System.out.println(rubrica);
        System.out.println("Dimensione: " + rubrica.size());

        String cercato = "Marco";
        int posizione = rubrica.indiceDi(cercato);
        System.out.println("\nPosizione di " + cercato + ": " + posizione);

        System.out.println("\nRimuovo 'Bob'...");
        rubrica.cancella("Bob");
        System.out.println(rubrica);

    }

    import java.util.NoSuchElementException;

public class Lista<T> {
    private Nodo<T> head;

    public Lista() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void aggiungiInTesta(T dato) {
        Nodo<T> nuovoNodo = new Nodo<>(dato);
        nuovoNodo.next = head;
        head = nuovoNodo;
    }

    public void aggiungiInCoda(T dato) {
        Nodo<T> nuovoNodo = new Nodo<>(dato);
        
        if (head == null) {
            head = nuovoNodo;
            return;
        }
        
        Nodo<T> corrente = head;
        while (corrente.next != null) {
            corrente = corrente.next;
        }
        corrente.next = nuovoNodo;
    }

    public void aggiungiInPosizione(T dato, int posizione) {
        if (posizione < 0) {
            throw new IndexOutOfBoundsException("Posizione negativa");
        }
        
        if (posizione == 0) {
            aggiungiInTesta(dato);
            return;
        }
        
        Nodo<T> corrente = head;
        int i = 0;
        
        while (corrente != null && i < posizione - 1) {
            corrente = corrente.next;
            i++;
        }
        
        if (corrente == null) {
            throw new IndexOutOfBoundsException("Posizione oltre la fine della lista");
        }
        
        Nodo<T> nuovoNodo = new Nodo<>(dato);
        nuovoNodo.next = corrente.next;
        corrente.
        next = nuovoNodo;
    }

    public T leggiTesta() {
        if (head == null) {
           throw new NoSuchElementException("Lista vuota");
        }
        return head.dato;
    }

    public T leggiCoda (){
        if (head == null){
           throw new NoSuchElementException("Lista vuota");
        }
        Nodo<T> current =head;
        while(current.next!=null){
            current=current.next;
        }
        return current.dato;

    }

    public boolean cancella(T dato) {
    if (head == null) {
        return false;
    }

    if (head.dato.equals(dato)) {
        head = head.next;
        return true;
    }
    
    Nodo<T> precedente = head;
    Nodo<T> corrente = head.next;
    
    while (corrente != null) {
        if (corrente.dato.equals(dato)) {
            precedente.next = corrente.next;
            return true;
        }
        precedente = corrente;
        corrente = corrente.next;
    }
    
    return false;
}
```


public T cancellaInPosizione(int posizione) {
    if (posizione < 0 || head == null) {
        throw new IndexOutOfBoundsException("Posizione non valida");
    }

    if (posizione == 0) {
        T dato = head.dato;
        head = head.next;
        return dato;
    }
    
    Nodo<T> precedente = head;
    int i = 0;
    
    while (precedente.next != null && i < posizione - 1) {
        precedente = precedente.next;
        i++;
    }
    
    if (precedente.next == null) {
        throw new IndexOutOfBoundsException("Posizione oltre la fine della lista");
    }
    
    T dato = precedente.next.dato;
    precedente.next = precedente.next.next;
    return dato;
}

public void concatena(Lista<T> altraLista) {
    if (altraLista == null || altraLista.head == null) {
        return;
    }
    
    if (head == null) {
        head = altraLista.head;
        return;
    }
    
    Nodo<T> corrente = head;
    while (corrente.next != null) {
        corrente = corrente.next;
    }
    corrente.next = altraLista.head;
}

ublic String toString() {
    StringBuilder sb = new StringBuilder();
    Nodo<T> corrente = head;
    
    while (corrente != null) {
        sb.append(corrente.dato);
        sb.append(" -> ");
        corrente = corrente.next;
    }
    sb.append("NULL");
    
    return sb.toString();
}