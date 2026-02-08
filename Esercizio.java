
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

        System.out.println("Primo contatto: " + rubrica.leggiTesta());
        System.out.println("Ultimo contatto: " + rubrica.leggiCoda());
        System.out.println("Contatto in posizione 2: " + rubrica.leggiInPosizione(2));

        System.out.println("\nRimuovo 'Bob'...");
        rubrica.cancella("Bob");
        System.out.println(rubrica);

        System.out.println("\nRimuovo posizione 1...");
        String rimosso = rubrica.cancellaInPosizione(1);
        System.out.println("Rimosso: " + rimosso);
        System.out.println(rubrica);

        System.out.println("\nContiene 'Alice'? " + rubrica.contiene("Alice"));
        System.out.println("Contiene 'Bob'? " + rubrica.contiene("Bob"));

        System.out.println("\nDimensione finale: " + rubrica.size());
        System.out.println("Lista vuota? " + rubrica.isEmpty());
        

        Lista<String> altriContatti = new Lista<>();
        altriContatti.aggiungiInCoda("Diana");
        altriContatti.aggiungiInCoda("Eva");
        
        System.out.println("\nConcateno altri contatti...");
        rubrica.concatena(altriContatti);
        System.out.println(rubrica);
        System.out.println("Dimensione finale: " + rubrica.size());
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
        corrente.next = nuovoNodo;
    }

    public T leggiTesta() {
        if (head == null) {
            throw new NoSuchElementException("Lista vuota");
        }
        return head.dato;
    }

    public T leggiCoda() {
        if (head == null) {
            throw new NoSuchElementException("Lista vuota");
        }
        
        Nodo<T> corrente = head;
        while (corrente.next != null) {
            corrente = corrente.next;
        }
        return corrente.dato;
    }

    public T leggiInPosizione(int posizione) {
        if (posizione < 0) {
            throw new IndexOutOfBoundsException("Posizione negativa");
        }
        
        Nodo<T> corrente = head;
        int i = 0;
        
        while (corrente != null && i < posizione) {
            corrente = corrente.next;
            i++;
        }
        
        if (corrente == null) {
            throw new IndexOutOfBoundsException("Posizione oltre la fine della lista");
        }
        
        return corrente.dato;
    }

    public int size() {
        int count = 0;
        Nodo<T> corrente = head;
        
        while (corrente != null) {
            count++;
            corrente = corrente.next;
        }
        
        return count;
    }

    public boolean contiene(T dato) {
        Nodo<T> corrente = head;
        
        while (corrente != null) {
            if (corrente.dato.equals(dato)) {
                return true;
            }
            corrente = corrente.next;
        }
        
        return false;
    }

    public int indiceDi(T dato) {
        Nodo<T> corrente = head;
        int indice = 0;
        
        while (corrente != null) {
            if (corrente.dato.equals(dato)) {
                return indice;
            }
            corrente = corrente.next;
            indice++;
        }
        
        return -1;
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

    @Override
    public String toString() {
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
}
}
}

