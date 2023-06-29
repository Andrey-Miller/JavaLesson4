// чтобы быстро проверить, введите имена из файла names.txt

import java.util.*;

public class main {
    static Scanner scaner = new Scanner(System.in);
    public static ArrayList<String> lastname = new ArrayList<>();
    public static ArrayList<String> name = new ArrayList<>();
    public static ArrayList<String> surname = new ArrayList<>();
    public static ArrayList<Integer> age = new ArrayList<>();
    public static ArrayList<String> gender = new ArrayList<>();
    public static ArrayList<Integer> key = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Введи ФИО, возраст и пол через пробел (для завершения введи end):");
        inputData();

        while (true) {
            System.out.printf("Выбери действие: \n1 - сортировка по возрасту \n2 - сортировка по возрасту и полу \n0 - выход \n");
            String data = scaner.nextLine();
            if (data.equals("0")) { 
                System.exit(0); 
            }
            else if (data.equals("1")) { 
                System.out.println("Отсортировано по возрасту:");
                System.out.println("__________________");
                printData(sortAge());
                System.out.println("__________________");
            } 
            else if (data.equals("2")) { 
                System.out.println("Отсортировано по возрасту и полу:");
                System.out.println("__________________");
                printData(sortAgeGender());
                System.out.println("__________________");
            } 
            else { 
                System.out.println("Некоректный ввод, повторите попытку");
            }
        }
    }

    static void inputData() {
        String data = scaner.nextLine();
        int count = 0;
        while (true) {
            if (data.equals("end")) {
                break;
            }
            //проверяем вводимую строку на длину и на то, что в качестве возраста указано число
            else if (data.split(" ").length!=5 || data.split(" ")[3].toString().matches("[-+]?\\d+")==false){
                System.out.println("Некорректный формат, повторите ввод");
                inputData();
            } //проверяем, что пол указан m или f
            else if (data.split(" ")[4].equals("m")==false && 
            data.split(" ")[4].equals("f")==false) {
                System.out.println("Некорректный формат, повторите ввод");
                inputData();
            }
            else {
                List<String> split = Arrays.asList(data.split(" "));
                lastname.add(split.get(0));
                name.add(split.get(1));
                surname.add(split.get(2));
                age.add(Integer.parseInt(split.get(3)));
                gender.add(split.get(4));
                key.add(count);
                count++;
                inputData();
            }
            break;
        }
        return;
    }

    static void printData(ArrayList<Integer>key) {
        for (Integer i : key) {
            System.out.println(lastname.get(i)+" "+name.get(i)+" "+surname.get(i)+" "+age.get(i)+" "+gender.get(i));
        }
    }

    static ArrayList<Integer> sortAge() {
        ArrayList<Integer> a_key = new ArrayList<>();
        ArrayList<Integer> tmp_a = new ArrayList<>();
        tmp_a.addAll(age);
        for (int i = 0; i < age.size(); i++) {
            a_key.add(age.indexOf(Collections.min(tmp_a)));
            tmp_a.remove(tmp_a
                    .indexOf(Collections
                            .min(tmp_a)));
        }
        return a_key;
    }

    static ArrayList<Integer> sortAgeGender() {
        ArrayList<Integer> a_key = new ArrayList<>();
        a_key.addAll(sortAge());
        int tmp;
        int k = 0;
        ArrayList<Integer>ag_key = new ArrayList<>();
        ag_key.addAll(a_key);
        for (int i = 0; i < key.size(); i++) {
            if (gender.get(a_key.get(i)).toLowerCase().equals("f")) {
                tmp = ag_key.get(k);
                ag_key.remove(k);
                ag_key.add(tmp);
            } else k += 1;
        }
        return ag_key;
    }
}