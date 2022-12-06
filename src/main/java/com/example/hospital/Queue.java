package com.example.hospital;

//import static com.sun.beans.introspect.ClassInfo.clear;

class Queue {

    private int maxSize, front, rear, nItems;
    private Antrian[] queArray;

    public Queue(int size) {
        this.maxSize = size;
        queArray = new Antrian[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(int noantrian, String nama, String poli, String dokter, String tanggal, String jenis) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = new Antrian(noantrian, nama, poli, dokter, tanggal, jenis);
        nItems++;
    }

    public Antrian remove() {
        Antrian temp = queArray[front++]; //10
        if (front == maxSize) {
            front = 0;
        }
        nItems--; //1
        return temp;
    }

//    public void removeSpecific(Antrian antrianToRemove) {
//        Antrian[] array = toArray();
//        clear();
//        for (Antrian antrian : array) {
//            if (!antrian.equals(antrianToRemove)) {
//                insert(antrian);
//            }
//        }
//    }

    public Antrian peekFront() {
        return queArray[front];
    }

    public Antrian lastAdded() {
        return queArray[nItems-1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int getnItems() {
        return nItems+1;
    }

    public Antrian[] toArray() {
        Antrian[] array = new Antrian[nItems];
        for (int i = 0; i < nItems; i++) {
            array[i] = queArray[(front + i) % maxSize];
        }
        return array;
    }
}