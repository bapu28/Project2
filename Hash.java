/**
 * Stub for hash table class
 *
 * @author CS3114 staff
 * @version August 2018
 */

public class Hash {

    private String[] hashTable;
    private int counter;
    private int size;
    private boolean resized;


    /**
     * Create a new Hash object.
     * 
     */
    public Hash(int size) {
        hashTable = new String[size];
        counter = 0;
        this.size = size;
        resized = false;
    }


    /**
     * 
     * @return
     */
    public String[] getHashTable() {
        return hashTable;
    }


    /**
     * 
     * @return
     */
    public int getCounter() {
        return counter;
    }


    /**
     * 
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * This method inserts the String into the hashTable with the String as the
     * key.
     * 
     * @param name
     *            The name of the String
     * @param value
     *            The value of the String.
     */
    public void hashInsert(String name) {
        if (counter == (size / 2)) {
            resized = true;
            resize();
            resized = false;
            System.out.println("Name hash table size doubled to " + Integer
                .toString(size) + " slots.");
        }
        insert(name);
    }


    /**
     * This is a helper method of hashInsert which does the whole process of the
     * insert.
     * 
     * @param name
     *            The name of the String
     * @param value
     *            The value of the String.
     */
    public boolean insert(String name) {
        int home = h(name, size);
        int pos = home;
        int i = 1;
        // int x = pos;
        // int y = -1;
        while (hashTable[pos] != null) {
            // if (hashTable[pos].equals("TOMBSTONE")) {
            // if (y == -1) {
            // x = pos;
            // }
            // y = 1;
            // }
            if (hashTable[pos].equals(name)) {
                return false;
            }
            pos = (home + (i * i)) % size;
            i++;

        }
        // if (y == 1) {
        // hashTable[x] = name;
        // }
        // else {
        // hashTable[pos] = name;
        // }
        hashTable[pos] = name;
        counter++;
        return true;
        // if (!resized) {

        // }
    }


    /**
     * This method resizes the hashTable when the number of inputs exceeds 50%
     * of the size.
     */
    private void resize() {
        int newSize = 2 * size;
        int temp = size;
        String[] oldHashTable = hashTable;
        hashTable = new String[newSize];
        counter = 0;
        size = newSize;
        for (int i = 0; i < temp; i++) {
            if (oldHashTable[i] != null) {
                insert(oldHashTable[i]);
            }
        }
    }


    /**
     * This function deletes a particular string from the hashTable.
     * 
     * @param name
     *            The name of the String to be removed from the hashTable.
     */
    public boolean hashDelete(String name) {
        int home = h(name, size);
        int pos = home;
        for (int i = 0; i < size; i++) {
            pos = (home + (i * i)) % size;
            if (hashTable[pos] == null) {
                i++;
                continue;
            }
            if (hashTable[pos].equals("TOMBSTONE")) {
                continue;
            }
            if (hashTable[pos].equals(name)) {
                hashTable[pos] = "TOMBSTONE";
                counter--;
                return true;
            }
        }
        return false;
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    // You can make this private in your project.
    // This is public for distributing the hash function in a way
    // that will pass milestone 1 without change.
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % m);
    }
}
