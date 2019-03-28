package Utils;

import Model.Chromosome;

public class MergeSort {
    public static void sort(Chromosome[] a, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        Chromosome[] l = new Chromosome[mid];
        Chromosome[] r = new Chromosome[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }

        sort(l, mid);
        sort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            Chromosome[] a,
            Chromosome[] l,
            Chromosome[] r,
            int left,
            int right
    ) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getFitness() <= r[j].getFitness()) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
