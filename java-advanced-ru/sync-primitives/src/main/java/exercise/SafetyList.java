package exercise;

class SafetyList {
    // BEGIN
    private int[] elements;
    private int size = 0;

    public SafetyList() {
        elements = new int[10];
    }

    public synchronized void add(int element) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = element;
        size++;
    }

    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return elements[index];
    }

    public int getSize() {
        return size;
    }

    private void resize() {
        int newSize = elements.length * 2;
        int[] newArray = new int[newSize];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
    // END
}
