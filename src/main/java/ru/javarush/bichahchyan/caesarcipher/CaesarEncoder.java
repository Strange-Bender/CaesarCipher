package ru.javarush.bichahchyan.caesarcipher;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CaesarEncoder {
    public List<Character> alphabet;
    public Set<String> dictionary;
    public CaesarEncoder(List<Character> alphabet){
        this.alphabet = alphabet;
    }

    public List<Character> getAlphabet() {
        return alphabet;
    }
    public int getAlphabetSize() {
        return alphabet.size();
    }

    public  String ecnrypt(String text, int key){

        StringBuilder copyOfText = new StringBuilder(text.toLowerCase());

        List<Character> alphabetList = alphabet;

        for (int i = 0; i < copyOfText.length(); i++) {
            Character charinText = copyOfText.charAt(i);

            if( alphabetList.contains(charinText)){
                int indexInAlphabet = alphabetList.indexOf(charinText);
                int resIndex = (indexInAlphabet + key) % alphabetList.size();
                copyOfText.setCharAt(i,alphabetList.get(resIndex));

            }

        }

        return copyOfText.toString();

    }

    public  String decrypt(String text, int key){
        StringBuilder copyOfText = new StringBuilder(text.toLowerCase());

        List<Character> alphabetList = alphabet;

        for (int i = 0; i < copyOfText.length(); i++) {
            char charinText = copyOfText.charAt(i);
            if( alphabetList.contains(Character.valueOf(charinText) )){
                int indexInAlphabet = alphabetList.indexOf(Character.valueOf(charinText));
                int resIndex = indexInAlphabet - key >= 0 ? indexInAlphabet - key : (indexInAlphabet - key ) + alphabetList.size() ;

                copyOfText.setCharAt(i,alphabetList.get(resIndex));

            }

        }

        return copyOfText.toString();
    }

    public  void decryptFile(Path filePath,Path desPath, int key){
        try {
            BufferedReader bufFileReader = new BufferedReader(new FileReader(filePath.toString()));
            BufferedWriter bufFileWriter = new BufferedWriter(new FileWriter(desPath.toString()));

            while(bufFileReader.ready()){
                char[] buffer = new char[100];
                int point = bufFileReader.read(buffer);
                String bufferString = String.valueOf(buffer,0,point);
                String deryptedString = decrypt(bufferString,key);
                bufFileWriter.write(deryptedString);
            }
            bufFileWriter.close();
            bufFileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  int numOfIdentWord(int key, Path filePath, Set<String> dictionary){
        //"C:\\Users\\Bender\\IdeaProjects\\EcryptionTest\\src\\dest.txt"
        int counter =0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));

            while (br.ready()) {
                String line = br.readLine();
                String resLine = decrypt(line, key);
                for (String s : resLine.split(" ")) {
                    if(dictionary.contains(s)){
                        counter++;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return counter;
    }

    public void setDictionary(String pathToDictionary){
        Set<String> listDictionary = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToDictionary));

            while(br.ready()){
                String[]  splElem = br.readLine().split(" ");
                for(String s : splElem){
                    listDictionary.add(s.toLowerCase());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dictionary = listDictionary;
    }
    public  Set<String> getDictionary(){
        return this.dictionary;
    }

}