import algorithm.HuffmanNode;
import algorithm.HuffmanUtils;

import java.util.*;

public class Main {

    public static void encodeMode(Scanner sc) {
        System.out.println("Enter your text:");
        sc.nextLine();
        String text = sc.nextLine();
        int[] freq = HuffmanUtils.buildFrequencyArray(text);
        Queue<HuffmanNode> queue = HuffmanUtils.buildPriorityQueue(freq);
        Map<Character, String> huffmanCodes = new HashMap<>();
        HuffmanNode root = HuffmanUtils.buildTree(queue);
        HuffmanUtils.generateCodes(root, "", huffmanCodes);
        String code = HuffmanUtils.encodeText(text, huffmanCodes);
        System.out.println("Code table:");
        System.out.println(Arrays.stream(freq).filter(a -> a > 0).count() + " " + code.length());
        huffmanCodes.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("Code: " + code);
    }

    public static void decodeMode(Scanner sc) {
        System.out.println("Enter the number of letters:");
        sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();
        Map<String, Character> map = new HashMap<>();
        System.out.println("Enter the code table line by line in the format \"letter: code\"");
        for (int i = 0; i < n; i++) {
            String[] keyValue = sc.nextLine().split(":\\s");
            map.put(keyValue[1], keyValue[0].charAt(0));
        }
        System.out.println("Enter code:");
        String code = sc.nextLine();
        System.out.println("Decoded text: " +HuffmanUtils.decodeCode(code, map));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose mode:");
        System.out.println("1.Encode text;");
        System.out.println("2.Decode code.");
        System.out.print("Enter 1 or 2: ");
        int mode = sc.nextInt();

        if (mode == 1) {
            encodeMode(sc);
        } else if (mode == 2) {

            decodeMode(sc);
        } else {
            System.out.println("Incorrect mode");
        }
    }

}