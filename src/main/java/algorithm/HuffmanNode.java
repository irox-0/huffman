package algorithm;

import lombok.Getter;

@Getter
public class HuffmanNode implements Comparable<HuffmanNode>{
    private char ch;
    private int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.freq = left.freq + right.freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq;
    }

}