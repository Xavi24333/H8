import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorHeapTest {

    @Test
    public void testAgregarYOrdenarPacientes() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        Paciente p1 = new Paciente("Ana", "Apendicitis", 'A');
        Paciente p2 = new Paciente("Luis", "Dolor de cabeza", 'C');
        Paciente p3 = new Paciente("Sofía", "Fractura", 'B');

        heap.add(p2);
        heap.add(p1);
        heap.add(p3);

        assertEquals(p1, heap.remove()); // prioridad A
        assertEquals(p3, heap.remove()); // prioridad B
        assertEquals(p2, heap.remove()); // prioridad C
    }

    @Test
    public void testIsEmpty() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty());

        heap.add(new Paciente("Carlos", "Fiebre", 'D'));
        assertFalse(heap.isEmpty());

        heap.remove();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testRemoveFromEmptyHeap() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertNull(heap.remove());
    }

    @Test
    public void testOrdenConPacientesDeIgualPrioridad() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        Paciente p1 = new Paciente("Emma", "Dolor muscular", 'C');
        Paciente p2 = new Paciente("Leo", "Dolor de garganta", 'C');

        heap.add(p1);
        heap.add(p2);

        // Se mantiene el orden de inserción si tienen la misma prioridad
        assertEquals(p1, heap.remove());
        assertEquals(p2, heap.remove());
    }
}
