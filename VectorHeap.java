import java.util.Vector;

/**
 * Implementación de una cola de prioridad mínima usando Heap.
 */
public class VectorHeap<E extends Comparable<E>> {
    protected Vector<E> data;

    public VectorHeap() {
        data = new Vector<>();
    }

    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    public E remove() {
        if (data.isEmpty()) return null;
        E min = data.get(0);
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            percolateDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private void percolateUp(int index) {
        E value = data.get(index);
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (value.compareTo(data.get(parent)) >= 0) break;
            data.set(index, data.get(parent));
            index = parent;
        }
        data.set(index, value);
    }

    private void percolateDown(int index) {
        int child;
        E value = data.get(index);
        while (index * 2 + 1 < data.size()) {
            child = index * 2 + 1;
            if (child + 1 < data.size() && data.get(child + 1).compareTo(data.get(child)) < 0) {
                child++;
            }
            if (data.get(child).compareTo(value) >= 0) break;
            data.set(index, data.get(child));
            index = child;
        }
        data.set(index, value);
    }
}
