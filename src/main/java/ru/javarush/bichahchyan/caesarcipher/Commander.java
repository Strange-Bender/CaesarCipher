package ru.javarush.bichahchyan.caesarcipher;

import ru.javarush.bichahchyan.caesarcipher.Constans.Alphabets;
import ru.javarush.bichahchyan.caesarcipher.Tools.ConsoleManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Commander {

    public static void encryptCommand(){
        CaesarEncoder rusShiphor = new CaesarEncoder(Alphabets.RUS_ALPHABET);

        Path inFile = ConsoleManager.getInnerPathFromConsole();
        Path outFile = ConsoleManager.getOutPathFromConsole();
        int key = ConsoleManager.getKeyForAlphabet(rusShiphor.getAlphabet());


        try {
            BufferedReader br = new BufferedReader(new FileReader(inFile.toString()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.toString()));
            char[] buf = new char[100];
            while(br.ready()){

                int point = br.read(buf);
                String s = String.valueOf(buf,0,point);
                String encryptedString = rusShiphor.ecnrypt(s,key);
                bw.write(encryptedString);

            }
            br.close();
            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Файл успешно зашифрован!");

    }

    public static void decryptCommand(){
        CaesarEncoder rusShiphor = new CaesarEncoder(Alphabets.RUS_ALPHABET);

        Path inFile = ConsoleManager.getInnerPathFromConsole();
        Path outFile = ConsoleManager.getOutPathFromConsole();
        int key = ConsoleManager.getKeyForAlphabet(rusShiphor.getAlphabet());

        try{
            BufferedReader br = new BufferedReader(new FileReader(inFile.toString()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.toString()));
            char[] buf = new char[100];
            while(br.ready()){

                int point = br.read(buf);
                String s = String.valueOf(buf,0,point);
                String decrytedtedString = rusShiphor.decrypt(s,key);
                bw.write(decrytedtedString);

            }

            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Файл успешно расшифрован!");

    }

    public static void brutForceCommand(){

        CaesarEncoder cezarShiphor = new CaesarEncoder(Alphabets.RUS_ALPHABET);
        cezarShiphor.setDictionary("C:\\Users\\Bender\\IdeaProjects\\EcryptionTest\\src\\dictionary.txt");
        Set<String> dictionary = cezarShiphor.getDictionary();

        Path inFile = ConsoleManager.getInnerPathFromConsole();
        int size = cezarShiphor.getAlphabetSize();
        int[] masCounter = new int[size];
        int maxIdentity = 0;
        for (int i = 0; i < size; i++) {
            int count = cezarShiphor.numOfIdentWord(i,inFile,dictionary);
            masCounter[i] = count;
            maxIdentity = count > maxIdentity ? count : maxIdentity;

        }
        System.out.print("Подходящие ключи для расшифровки: ");
        int bestKey = 0;
        for (int i = 0; i < masCounter.length; i++) {
            if(masCounter[i] == maxIdentity){
                bestKey = i;
                System.out.print(i + " ");
            }
        }
        cezarShiphor.decryptFile(inFile,Paths.get("C:\\Users\\Bender\\IdeaProjects\\EcryptionTest\\src\\BrutForceRes.txt"),bestKey);
        System.out.println("\n рузультат BrutForce лежит в файлу: C:\\Users\\Bender\\IdeaProjects\\EcryptionTest\\src\\BrutForceRes.txt");




    }


    public static void showMenu(){
        System.out.println("Выберите один из видов команд:\n" +
                "1. Зашифровать файл с использованием ключа.\n" +
                "2. Расшифровать файл с использованием ключа.\n" +
                "3. Расшифровка с помощью BrutForce метода.\n"+
                "Или введите \"exit\" для выхода!");
    }

}