import java.util.*;
/*
0) Считать ингридиенты в множество
1) Считать строки в массив (ArrayList)
1*) Создать класс, содержащий два множества
    для ингридиентов и продуктов реакции, хранить 
    в ArrayList экземпляры такого класса
2) Пробегать по массиву, проверяя для каждой реакции
   достаточно ли у вас ингридиентов для неё. Если да,
   добавить продукты к множеству ингридиентов
   * можно удалить реакцию, т.к. она больше не нужна
3) Продолжать до тех пор, пока реакции не перестанут
давать новых ингридиентов
*/

class Reaction {
    ArrayList<Integer> in, out;

    public Reaction(int[] in, int[] out) {
        this.in = new ArrayList<>();
        for (int i: in) {
            this.in.add(i);
        }
        this.out = new ArrayList<>();
        for (int i: in) {
            this.out.add(i);
        }

    }
}

public class Main {
    public static int[] strToInt(String[] numbers) {
        int[] n = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            n[i] = Integer.parseInt(numbers[i]);
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Integer> compounds = new TreeSet<>();
        int[] ncompounds = strToInt(sc.nextLine().split(" "));
        for (Integer c : ncompounds) {
            compounds.add(c);
        }
        ArrayList<Reaction> reactions = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] strings = line.split("->");
            String[] left = strings[0].split("\\+");
            String[] right = strings[1].split("\\+");
            reactions.add(new Reaction(strToInt(left), strToInt(right)));
        }
        boolean haveNewCompounds = false;
        do {
            haveNewCompounds = false;
            for (int i = 0; i < reactions.size(); i++) {
                Reaction r = reactions.get(i);
                if (compounds.containsAll(r.in)) {
                   compounds.addAll(r.out);
                   haveNewCompounds = true;
                   reactions.remove(i);
                   i--;
                }
            }

        } while (haveNewCompounds);
        System.out.println(compounds);
    }
}
