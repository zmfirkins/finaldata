import java.util.ArrayList;

public class InsertionSort {
    public static void insertionSort(ArrayList<Book> books) {
        int n = books.size();

        for (int i = 1; i < n; i++) {
            Book key = books.get(i);
            int j = i - 1;

            while (j >= 0 && books.get(j).getBookId() > key.getBookId()) {
                books.set(j + 1, books.get(j));
                j = j - 1;
            }
            books.set(j + 1, key);
        }
    }
}