package ru.javarush.bichahchyan.caesarcipher.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsoleManager {

    public static int getKeyForAlphabet(List<Character> alphabet){
        boolean keyIsCorrect = false;
        int temKey = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!keyIsCorrect){
            try{

                System.out.println("Введите клю от 1 и до " + (alphabet.size() - 1));
                temKey =Integer.parseInt(br.readLine());


                if(Validator.isValidKey(temKey, alphabet)){
                    keyIsCorrect = true;

                    return temKey;
                }else{
                    System.out.println("Не верный ключ попробуйте ввести ключь снова");

                }

            }catch (IOException e) {

                e.printStackTrace();
            }catch(NumberFormatException e){
                System.out.println("Не верный ключ попробуйте ввести ключь снова");
            }

        }

        return 0;
    }

    public static Path getInnerPathFromConsole(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean pathIsCorrect = false;
        String tempPath ;

        while(!pathIsCorrect){
            try{
                System.out.println("Введите путь к файлу");
                tempPath =br.readLine();

                if (Validator.isFileExists(tempPath)) {
                    pathIsCorrect = true;

                    return Paths.get(tempPath);
                } else {
                    System.out.println("Не верный путь к файлу или файл не существует попробуйте еще раз ");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }catch(InvalidPathException e){
                System.out.println("Не верный путь к файлу попробуйте еще раз");
            }

        }

        return null;
    }

    public static Path getOutPathFromConsole(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean pathIsCorrect = false;
        String tempPath ;

        while(!pathIsCorrect){
            try{
                System.out.println("Введите путь к файлу куда нужно сохранить");
                tempPath =br.readLine();

                if (Validator.isFileExists(tempPath)) {
                    pathIsCorrect = true;

                    return Paths.get(tempPath);
                } else {
                    System.out.println("Не верный путь к файлу или файл не существует попробуйте еще раз");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }catch(InvalidPathException e){
                System.out.println("Не верный путь к файлу попробуйте еще раз");
            }

        }

        return null;

    }

    public static String getKeyForMenu(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean keyIsCorrect = false;
        while(!keyIsCorrect){
            try {
                String commandNumber;
                commandNumber =br.readLine();
                if(!commandNumber.equals("exit")){
                    int temNumber = Integer.parseInt(commandNumber);
                    if(temNumber < 4 && temNumber > 0){

                        return commandNumber;
                    }else {
                        System.out.println("Не правильный номер команды попробуйте еще раз\n" +
                                "Или введите 'exit' для выхода!");
                    }
                }else{

                    return commandNumber;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }catch (NumberFormatException e){
                System.out.println("Не правильный номер команды попробуйте еще раз\n" +
                        "Или введите 'exit' для выхода!");
            }
        }

        return null;
    }
}