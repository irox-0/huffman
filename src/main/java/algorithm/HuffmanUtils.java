package algorithm;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanUtils {

    public static int[] buildFrequencyArray(String text) {
        int[] freq = new int[256];
        for (char ch : text.toCharArray()) {
            if (ch < 256) freq[ch]++;
        }
        return freq;
    }

    public static Queue<HuffmanNode> buildPriorityQueue(int[] freq) {
        Queue<HuffmanNode> queue = new PriorityQueue<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0)
                continue;
            queue.offer(new HuffmanNode((char) i, freq[i]));
        }
        return queue;
    }

    public static HuffmanNode buildTree(Queue<HuffmanNode> queue) {
        while(queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode(left, right);
            queue.offer(parent);
        }
        return queue.poll();
    }

    public static void generateCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) return;

        if (node.getLeft() == null && node.getRight() == null) {
            huffmanCodes.put(node.getCh(), code.isEmpty() ? "0" : code);
            return;

        }
        generateCodes(node.getLeft(), code + "0", huffmanCodes);
        generateCodes(node.getRight(), code + "1", huffmanCodes);
    }

    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encoded = new StringBuilder();
        for(char ch : text.toCharArray()) {
            String code = huffmanCodes.get(ch);
            if (code == null) {
                throw new IllegalArgumentException("Unknown character: " + ch);
            }
            encoded.append(code);
        }
        return encoded.toString();
    }

    public static String decodeCode(String code, Map<String, Character> map) {
        StringBuilder text = new StringBuilder();
        StringBuilder subCode = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            String key = subCode.append(code.charAt(i)).toString();
            if (map.containsKey(key)) {
                text.append(map.get(key));
                subCode.setLength(0);
            }
        }
        return text.toString();
    }

}