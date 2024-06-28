package com.mycompany.radix;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.DecimalFormat;

public class Arquivo {
    String[] palavras = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "maçã", "banana", "laranja", "abacaxi", "uva",
            "morango", "pera", "melancia", "kiwi", "manga",
            "pêssego", "ameixa", "cereja", "framboesa", "limão",
            "abacate", "goiaba", "caju", "pitanga", "figo",
            "jabuticaba", "maracujá", "melão", "tangerina", "coco",
            "acerola", "romã", "graviola", "caqui", "amora"
        };
    
    public List<Item> readInputFile(String filename) {
        List<Item> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");
                int codigo = Integer.parseInt(fields[0]);
                String descricao = fields[1];
                String unidade = fields[2];
                float valor = Float.parseFloat(fields[3]);
                items.add(new Item(codigo, descricao, unidade, valor));
            }
            
            return items;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("Error ao ler o arquivo no caminho '"+filename+"', certifique-se que o arquivo ENTRADA.txt existe e está formatado conforme os padrões. EXEMPLO: CODIGO tab DESCRICAO tab UNIDADE tab VALOR");
            return null;
        }
    } 
    
    public void writeOutputFile(List<Item> items, String filename) {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : items) {
                writer.write(item.getCodigo() + "\t" + item.getDescricao() + "\t" +
                        item.getUnidade() + "\t" + item.getValor());
                writer.newLine(); // Pula para a próxima linha
            }
            System.out.println("\nDados gravados com sucesso no arquivo " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    public void gerarValoresAleatorios(String path, int tamanho) {
        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < tamanho; i++) {
                int codigo = random.nextInt(100);
                double randomIndex1 = (random.nextDouble() * 56);
                double randomIndex2 = (random.nextDouble() * 56);
                double randomValor = random.nextDouble(1000);
                
                BigDecimal valor = new BigDecimal(randomValor).setScale(2, BigDecimal.ROUND_HALF_UP);
                
                writer.write((int)codigo + "\t" + this.palavras[(int)randomIndex1] + "\t" +
                        this.palavras[(int)randomIndex2] + "\t" + valor);
                writer.newLine(); // Pula para a próxima linha
            }
            System.out.println("\nDados gravados com sucesso no arquivo " + path);
         } catch (IOException e) {
            e.printStackTrace();
         }  
    }
}
