package br.com.alura.mvc.mudi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        final boolean b = "Palavra" instanceof String;
        System.out.print(b);

        int array[] = {1, 2 ,3 ,4};
        for(int i=0; i<array.length;i++){
            System.out.print(array[i]);
        }

        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(1);
        list.add(10);
        System.out.println(list);
    }
}
