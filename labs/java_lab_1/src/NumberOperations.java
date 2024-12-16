import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NumberOperations {
    public static void main(String[] args) {
        ArrayList<Number> StartNumbers = new ArrayList<>(Arrays.asList(10f, 20.5f, 30f, 40.7f, 50f, 60.3f, 70f, 80.1f, 90f, 100.9f));
        ArrayList<Number> Numbers = new ArrayList<>(Arrays.asList(10f, 20.5f, 30f, 40.7f, 50f, 60.3f, 70f, 80.1f, 90f, 100.9f));

        byte byte_num = 42;
        short short_num = 152;
        int int_num = -156;
        double double_num = 824.342;
        float float_num = 67f;
        long long_num = 465;
        BigDecimal bigdecimal_num = new BigDecimal("1234567890.0987654321");

        Numbers.add(byte_num);
        Numbers.add(short_num);
        Numbers.add(int_num);
        Numbers.add(double_num);
        Numbers.add(float_num);
        Numbers.add(long_num);
        Numbers.add(bigdecimal_num);

        System.out.println(Numbers);

        System.out.print("\nInteger numbers: ");
        for (int i = 0; i < Numbers.size(); i++) {
            System.out.print(Numbers.get(i).intValue());
            if (i != Numbers.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("\nFloat numbers: ");
        for (int i = 0; i < Numbers.size(); i++) {
            if (Numbers.get(i) instanceof BigDecimal) {
                System.out.printf("%.2f", Numbers.get(i).doubleValue());
            }
            else {
                System.out.printf("%.2f", Numbers.get(i).floatValue());
            }
            if (i < Numbers.size() - 1) {
                System.out.print(", ");
            }
        }

        double sum = 0;
        for (Number number : StartNumbers) {
            sum += number.doubleValue();
        }
        System.out.print("\nSum: " + sum);

        System.out.println("\n\nSorted numbers: ");
        Map<Class<?>, List<Object>> map = new HashMap<>();
        for (int i = 0; i < Numbers.size(); i++) {
            Class<?> numberClass = Numbers.get(i).getClass();
            List<Object> list = map.computeIfAbsent(numberClass, k -> new ArrayList<>());
            list.add(Numbers.get(i));
        }
        for (Map.Entry<Class<?>, List<Object>> entry : map.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        }

    }}