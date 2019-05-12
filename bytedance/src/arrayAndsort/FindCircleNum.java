package arrayAndsort;

/**
 * @program: leetcode
 * @description: 找朋友圈
 * @author: Liu Hanyi
 * @create: 2019-04-11 15:21
 **/

public class FindCircleNum {
    public static void main(String[] args) {
        int[][] M = {{1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        System.out.println(new FindCircleNum().findCircleNum(M));
    }

    /**
     * tag:dfs union-find
     * method1:dfs,相当于有Ｍ.length个朋友，深度优先遍历每一个朋友的朋友，如果找到有朋友，将该朋友的标志位置为ｔｒｕｅ
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        boolean[] found = new boolean[M.length];
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            if (!found[i]) {
                res++;
                findCircleNum(M, i, found);
            }
        }
        return res;
    }

    private void findCircleNum(int[][] M, int i, boolean[] found) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !found[j]) {
                found[j] = true;
                findCircleNum(M, j, found);
            }
        }
    }


    /**
     * 并查集的思路
     */
    class UnionFind {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }

        public int count() {
            return count;
        }
    }

    public int findCircleNum2(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }


}
