package test140;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        String tiles = "AAABBC";
        System.out.println(new LetterTilePossibilities().numTilePossibilities(tiles));
    }

    Set<String> set = new HashSet<>();
    public int numTilePossibilities(String tiles) {
        int length = tiles.length();
        char[] chs = tiles.toCharArray();
        for (int i = 0; i < length; i++) {
            String letter = String.valueOf(chs[i]);
            boolean[] visited = new boolean[length];
            if (set.add(letter)){
                visited[i] = true;
                backtrack(chs, letter, visited);
            }
        }

        System.out.println(set);
        return set.size();
    }

    public void backtrack(char[] chs, String str, boolean[] visited){
        for (int j = 0; j < chs.length; j++) {
            if (visited[j]) continue;
            String newStr = str + chs[j];
            if (newStr.length() <= chs.length && set.add(newStr)){
                visited[j] = true;
                backtrack(chs, newStr, visited);
                visited[j] = false;
            }
        }
    }
}
