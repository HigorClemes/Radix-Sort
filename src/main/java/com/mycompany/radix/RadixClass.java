package com.mycompany.radix;
import java.util.*;

public class RadixClass {
       public static void radixSortByCodigo(List<Item> items, boolean ascending) {
        // Converte a lista de itens em um array de inteiros contendo os códigos
        int[] arr = items.stream().mapToInt(Item::getCodigo).toArray();
        // Aplica Radix Sort ao array
        radixSort(arr);
        // Inverte o array se a ordenação for descendente
        if (!ascending) {
            reverseArray(arr);
        }
        // Atualiza a lista original de itens com os códigos ordenados
        for (int i = 0; i < arr.length; i++) {
            items.set(i, new Item(arr[i], items.get(i).getDescricao(), items.get(i).getUnidade(), items.get(i).getValor()));
        }
    }

    public static void radixSortByDescricao(List<Item> items, boolean ascending) {
        // Converte a lista de itens em um array de strings contendo as descrições
        String[] arr = items.stream().map(Item::getDescricao).toArray(String[]::new);
        // Aplica Radix Sort ao array
        radixSort(arr);
        // Inverte o array se a ordenação for descendente
        if (!ascending) {
            reverseArray(arr);
        }
        // Atualiza a lista original de itens com as descrições ordenadas
        for (int i = 0; i < arr.length; i++) {
            items.set(i, new Item(items.get(i).getCodigo(), arr[i], items.get(i).getUnidade(), items.get(i).getValor()));
        }
    }

    public static void radixSortByUnidade(List<Item> items, boolean ascending) {
        // Converte a lista de itens em um array de strings contendo as unidades
        String[] arr = items.stream().map(Item::getUnidade).toArray(String[]::new);
        // Aplica Radix Sort ao array
        radixSort(arr);
        // Inverte o array se a ordenação for descendente
        if (!ascending) {
            reverseArray(arr);
        }
        // Atualiza a lista original de itens com as unidades ordenadas
        for (int i = 0; i < arr.length; i++) {
            items.set(i, new Item(items.get(i).getCodigo(), items.get(i).getDescricao(), arr[i], items.get(i).getValor()));
        }
    }

    public static void radixSortByValor(List<Item> items, boolean ascending) {
         // Converte a lista de itens em um array de doubles contendo os valores
        double[] arr = items.stream().mapToDouble(Item::getValor).toArray();
        // Aplica Radix Sort ao array
        radixSort(arr);
        // Inverte o array se a ordenação for descendente
        if (!ascending) {
            reverseArray(arr);
        }
        // Atualiza a lista original de itens com os valores ordenados
        for (int i = 0; i < arr.length; i++) {
            items.set(i, new Item(items.get(i).getCodigo(), items.get(i).getDescricao(), items.get(i).getUnidade(), arr[i]));
        }
    }

    private static void radixSort(int[] arr) {
        // Encontra o valor máximo no array para determinar o número de dígitos
        int max = Arrays.stream(arr).max().getAsInt();
        // Aplica Counting Sort para cada dígito, começando pelo menos significativo
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void radixSort(String[] arr) {
        // Encontra o comprimento máximo das strings
        int maxLength = Arrays.stream(arr).mapToInt(String::length).max().orElse(0);
        // Aplica Counting Sort para cada posição de caractere, começando pelo final
        for (int exp = maxLength - 1; exp >= 0; exp--) {
            countingSort(arr, exp);
        }
    }

    private static void radixSort(double[] arr) {
        // Converte valores decimais em longos, preservando a ordem usando um fator multiplicativo
        long[] longArr = new long[arr.length];
        double factor = Math.pow(10, getMaxDecimalPlaces(arr));
        for (int i = 0; i < arr.length; i++) {
            longArr[i] = (long) (arr[i] * factor);
        }
        // Aplica o Radix Sort
        radixSort(longArr);
        // Converte os longs de volta para valores decimais
        for (int i = 0; i < arr.length; i++) {
            arr[i] = longArr[i] / factor;
        }
    }

    private static void radixSort(long[] arr) {
        long max = Arrays.stream(arr).max().getAsLong();
        for (long exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // Array de saída que terá os números ordenados
        int[] count = new int[10]; // Array de contagem para armazenar a contagem de dígitos (0-9)
        
        // Inicializa o array de contagem com zeros
        Arrays.fill(count, 0);

        // Armazena a contagem de ocorrências de dígitos no array de contagem
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / exp) % 10;
            count[index]++;
        }

        // Altera count[i] para que count[i] agora contenha a posição real deste dígito no array de saída
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copia o array de saída para arr[], de modo que arr[] agora contenha os números ordenados
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static void countingSort(long[] arr, long exp) {
        int n = arr.length;
        long[] output = new long[n];
        int[] count = new int[10]; // Array de contagem para armazenar a contagem de dígitos (0-9)
        
        // Inicializa o array de contagem com zeros
        Arrays.fill(count, 0);

         // Armazena a contagem de ocorrências de dígitos no array de contagem
        for (int i = 0; i < n; i++) {
            int index = (int) ((arr[i] / exp) % 10);
            count[index]++;
        }

        // Altera count[i] para que count[i] agora contenha a posição real deste dígito no array de saída
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            int index = (int) ((arr[i] / exp) % 10);
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copia o array de saída para arr[], de modo que arr[] agora contenha os números ordenados
        System.arraycopy(output, 0, arr, 0, n);
    };;

    private static void countingSort(String[] arr, int exp) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[256]; // Array de contagem para armazenar a contagem de caracteres (ASCII)
        // Inicializa o array de contagem com zeros
        Arrays.fill(count, 0);

        // Armazena a contagem de ocorrências de caracteres no array de contagem
        for (int i = 0; i < n; i++) {
            int index = exp < arr[i].length() ? arr[i].charAt(exp) : 0;
            count[index]++;
        }

         // Altera count[i] para que count[i] agora contenha a posição real deste caractere no array de saída
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            int index = exp < arr[i].length() ? arr[i].charAt(exp) : 0;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copia o array de saída para arr[], de modo que arr[] agora contenha as strings ordenadas
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Função auxiliar para obter o número máximo de casas decimais em um array de doubles
    private static int getMaxDecimalPlaces(double[] arr) {
        int maxDecimalPlaces = 0;
        for (double value : arr) {
            String str = Double.toString(value);
            int decimalPlaces = str.length() - str.indexOf('.') - 1;
            if (decimalPlaces > maxDecimalPlaces) {
                maxDecimalPlaces = decimalPlaces;
            }
        }
        return maxDecimalPlaces;
    }

    // Função auxiliar para inverter um array de int
    private static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Função auxiliar para inverter um array de double
    private static void reverseArray(double[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            double temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

   // Função auxiliar para inverter um array de string
    private static void reverseArray(String[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            String temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
