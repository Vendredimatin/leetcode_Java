# Array

## Easy

### 283. Move Zeroes

#### method 1

两根指针法

left标识着为0的元素，right标识着非0元素，每当right指针标识非0时，就和left交换，这样保证了在原数组上交换，同时只遍历一次

```java
public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;

        while (left < nums.length && nums[left] != 0) {
            left++;
            right++;
        }

        while (right < nums.length && nums[right] == 0) right++;

        while (right < nums.length && left < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                while (left < nums.length && nums[left] != 0) left++;
            }
            right++;
        }
    }	
```

#### method 2

对method　1 的改进，操作次数最少

遍历时的right作为标识非０的指针，left为标示为０的指针，当nums[right] != 0时就交换，left,right会一起自增，当nums[right] = 0 时，left就会停在０的位置，right会自增到非０的位置

```java 
public void moveZeroes1(int[] nums){
        for (int left = 0,right = 0; right < nums.length ; right++) {
            if (nums[right] != 0){
                swap(nums,left,right);
                left++;
            }
        }
    }
```

#### method  3 

时间和空间都是O（n）的解法，先遍历一次记录0的个数，再遍历一次将非0的按原来的顺序放到新数组中，然后在往新数组的尾部添加0

```java
public void moveZroes2(int[] nums){
        int n = nums.length;
        int numsOfZero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) numsOfZero++;
        }

        Queue<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) stack.offer(nums[i]);
        }

        while (numsOfZero-- > 0)
            stack.offer(0);

        for (int i = 0; i < n; i++) {
            nums[i] = stack.poll();
        }
    }
```



#### method 4

根据题目的意思，可以将数组分为两类，为０的情况和非０的情况，那么就可以使用两根指针，将遇到的为０的情况先全部用后面的非０数填充，最后再将lastNonZeroFoundAt之后的位置全部赋值为０

```java
public void moveZroes3(int[] nums){
    int lastNonZeroFoundAt = 0;
    // If the current element is not 0, then we need to
    // append it just in front of last non 0 element we found.
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            nums[lastNonZeroFoundAt++] = nums[i];
        }
    }
    // After we have finished processing new elements,
    // all the non-zero elements are already at beginning of array.
    // We just need to fill remaining array with 0's.
    for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
        nums[i] = 0;
    }
}
```

#### summary

1. 暂且将该数组问题分类为＂数组分类＂问题，可以用两根指针法处理数组分类问题（使用交换swap）
2. 同时也可以先处理一类，再处理另一类，如method4

### 169. Majority Element

#### method 1

sort, 然后直接取中间，代码略

#### method 2

hashMap, 统计每个数字出现的次数，然后取最大

#### method 3

分治法，以中间为线，分别找到左边和右边的主元素，如果两个数相同，说明此就为主元素．如果不同，那分别统计他们出现的次数，次数多的为主元素

```java
/**
 * 采用分治法，分别得到数组左边的主元素和右边的主元素，如果两边主元素是同一个数，那直接return
 * 如果不一样，就需要计算搜索范围看哪一个出现的次数更多就返回哪一个
 * @param nums
 * @return
 */
public int majorityElement2(int[] nums) {
    return majorityElementRec(nums, 0, nums.length - 1);
}
private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }
```

#### method 4 

Boyer-Moore Voting Algorithm 多数投票算法

一旦两个相邻的元素不同，就把这两个元素对冲抵消掉。由于众数的出现频次大于数据其他元素出现频率次之和，所以这种抵消最后剩下的一定是众数

```java
public int majorityElement(int[] nums){
    int count = 1;
    int res = nums[0];
    for(int i = 1; i< nums.length; i++){
        if(count == 0)
            res = nums[i];
        
       if(res == nums[i])
           count++;
        else{
            count--;
        }
    }
    
    return res;
}
```

#### method 5

位操作法：该法是我从一篇博客中学到了（忘记了原博的地址了）

因为一个整数在32为机器上只有32位，那么可以使用一个长度为32的数组来记录输入数组中每1位中1的个数和0的个数，由于存在一个出现次数超过一半的元素，那么取第i位中出现次数多的（0或者1）即可以构成超过数组一半元素。 

```java
public int majorityElement5(int[] nums) {
    int ones = 0;
    int zeros = 0;

    int res = 0;
    for (int i = 0; i < 32; i++) {
        for (int j = 0; j < nums.length; j++) {
            int num = nums[i];
            if ((num & (1 << i)) != 0)
                ones++;
            else zeros++;
        }

        if (ones > zeros)
            res |= (1 << i);
    }
    
    return res;
}
```

#### summary

1. 找数量上的问题时，如果数量上是占一定优势，可以考虑多数投票算法
2. 分治算法，分的每一部分都是对题目直接求答案，如本题是对各自部分求主元素，而不是求什么其他辅助条件，将大问题分为许多小问题
3. 位操作，数字数量上的问题，最后会反应到３２位上的每一位，可以借鉴这种解法

### 268. Missing Number

tag: array, math, bit manipulation

#### method 1

因为数组内是连续的０～ｎ，所以立马想到可以先求出０～ｎ的和，减去数组所有数的和，剩下的就是缺失的数

```java
public int missingNumber(int[] nums) {
        int n = nums.length + 1;
        int sum = n*(n-1)/2;

        for (int num:nums) {
            sum -= num;
        }

        return sum;
    }
```

#### method 2

位操作法：使用此法的关键在于发现了数组位置ｉ及其存储的值nums[i] 之间的关系，因为一个数异或它本身为０，而数组位置０－（ｎ－１），数组存储的值包含０－ｎ，又因为异或具有交换律，所以将数组位置ｉ与nums[i]异或，最后的结果便是确实的数

```java
public int missingNumber4(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i^nums[i];
        }

        return missing;
    }
```

#### summary:

1. 使用位操作的一种场景，数组位置ｉ与数组值nums[i] 存在间接相等的关系，而一个数异或其本身为０

### 1. Two Sum

tag: array, hash table

#### method 1

two-pass hash table　第一边遍历，将每一个数放进map中，第二次遍历时，遍历ｍａｐ中是否存在target-nums[i]有就取索引直接返回

```java
public int[] twoSum(int[] nums, int target) {
    Set<Integer> set = new HashSet<>();
    int[] res = new int[2];
    int anotherNum = 0;
    for (int i = 0; i < nums.length; i++) {
        if (set.contains(target-nums[i])){
            res[1] = i;
            anotherNum = target-nums[i];
            break;
        }
        set.add(nums[i]);
    }

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == anotherNum){
            res[0] = i;
            break;
        }
    }

    return res;
}
```

#### method 2

one-pass hash table,　其实一次遍历就够，每次遍历的时候检查map中是否有target-nums[i]的数，没有就放进map中，总会找到的

```java
public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
```









## MEDIUM

### 73 Set Matrix Zeroes

将每一个０所在行列全部设置为０，难点在于，一行／列中可能会有多个０，如果立马把所在行列全设置为０，可能会干扰其他行列

#### method1

使用辅助数组，标注好哪个位置上是０，然后第二遍循环的时候把位置对应的行列全设置为０

#### method2

使用辅助标记，在遍历时，就将０位置所在行／列全部标注为非０的其他数，然后第二次遍历时将这个marker设置为０．虽然保证了space O(1)但唯一的缺点是当数组中出现了这个marker可能这个测试用例会失败

#### method3

对method2的改进，如果遍历时该位置为０，那么就将对应列和对应行的第一个cell置为０，作为flag，然后每次遍历的时候根据flag判断是否需要将该行该列置为０．要注意的是第一行第一列要特殊处理，所以设置一个变量判断第一列是否需要设置为０，而matrix\[0][0]判断第一行是否需要设置为０

```java
public void setZeroes(int[][] matrix) {
    Boolean isCol = false;
    int R = matrix.length;
    int C = matrix[0].length;

    for (int i = 0; i < R; i++) {

      // Since first cell for both first row and first column is the same i.e. matrix[0][0]
      // We can use an additional variable for either the first row/column.
      // For this solution we are using an additional variable for the first column
      // and using matrix[0][0] for the first row.
      if (matrix[i][0] == 0) {
        isCol = true;
      }

      for (int j = 1; j < C; j++) {
        // If an element is zero, we set the first element of the corresponding row and column to 0
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }

    // Iterate over the array once again and using the first row and first column, update the elements.
    for (int i = 1; i < R; i++) {
      for (int j = 1; j < C; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    // See if the first row needs to be set to zero as well
    if (matrix[0][0] == 0) {
      for (int j = 0; j < C; j++) {
        matrix[0][j] = 0;
      }
    }

    // See if the first column needs to be set to zero as well
    if (isCol) {
      for (int i = 0; i < R; i++) {
        matrix[i][0] = 0;
      }
    }
  }
}
```

#### summary

当时做的时候只想到了m1和m2，ｍ3真的非常奇妙，设置该行该列的第一个元素，就不会受到下面遍历的影响，所以设置ｆｌａｇ的时候，要将flag设置到已经遍历过的地方，比如行首，列首

### 34. Find First and Last Position of Element in Sorted Array

#### method 1 

线性搜索，然后找最小和最大

#### method 2

改进的binarySearch, 当找到target时，可能mid-1和mid+1也是target，所以此时要分别往两边方向进行binarySearch

```java
public int[] searchRange(int[] nums, int target) {
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        int[] ans = searchRange(nums, target, 0, nums.length - 1,first,last);

        if (ans[0] == Integer.MAX_VALUE)
            return new int[]{-1,-1};
        return ans;
    }

    public int[] searchRange(int[] num, int target, int l, int r, int first, int last) {
        if (l > r) return new int[]{first, last};

        int mid = (l + r) / 2;
        if (num[mid] == target){
          first = Math.min(first,mid);
          last = Math.max(last,mid);
          int[] ans = new int[2];
          if (mid > 0 && num[mid-1] == target){
              ans = searchRange(num,target,l,mid-1,first,last);
              first = Math.min(first,ans[0]);
          }
          if (mid < num.length-1 && num[mid+1] == target){
              ans = searchRange(num,target,mid+1,r,first,last);
              last = Math.max(last,ans[1]);
          }
          return new int[]{first,last};
        } else if (num[mid] > target)
            return searchRange(num, target, l, mid-1, first, last);
        else return searchRange(num, target, mid+1, r, first, last);
    }
```

标准解法中的binarySearch更简洁

引入了一个left的左参数，代表在得到target == nums[mid]时，要往左数组继续查找，如果是ｆａｌｓｅ的话代表要往右子数组继续查找

```java
private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }
public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
```

#### summary:

当target == nums[mid]，不代表查找已经结束了，还要往左子数组或右子数组继续

算法中在方法参数中引入boolean并无什么不妥

### 55 Jump Game

#### method1 

递归回溯的解法,　从左往右递归，如果递归到pos+nums[pos]>终点，那么代表可以跳到．

缺点就是每到一个点就要尝试nums[i]次，时间复杂度极高

```java
private boolean canJump(int[] nums, int start, int max) {
        if (nums[start] == 0 && start != nums.length-1)
            return false;
        if (start + max >= nums.length-1)
            return true;

        for (int i = max; i >= 1; i--) {
           if (canJump(nums,start+i,nums[start+i]))
                return true;
        }

        return false;
    }
```

#### method2

自上而下动态规划，针对m1进行改进，ｍ１中每到一个点都要尝试，但是这是可以存储的，在第一次尝试之后，后面再次达到ｉ位置都可以直接取值

```java
private boolean canJump2(int[] nums, int start) {
        start = Math.min(start,nums.length-1);

        if (index[start] != Index.UNKNOWN)
            return (index[start] == Index.GOOD)?true:false;

        int max = nums[start];
        for (int i = max; i >= 1; i--) {
            if (canJump2(nums,start+i)){
                index[start] = Index.GOOD ;
                return true;
            }
        }

        index[start] = Index.BAD;
        return false;
    }
```

#### method3

自下而上动态规划，递归改循环

#### method4

贪心，从右往左遍历，只要在位置ｉ上，ｉ＋nums[i]大于等于第一个可达good index，那么就代表ｉ也是可达的，遍历到起点０，看０是否是ｇｏｏｄ

```java
public boolean canJump４(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
```

#### summary

1. 对于重复计算的结果，应该想到记忆化搜索dp
2. 可以引入定义辅助解决问题，例如good index
3. 贪心解法，转换问题的等价性，从结尾出发，不一定要覆盖到结尾，对于位置ｉ，只要能覆盖到这样的位置ｊ，而这个位置可以覆盖到结尾，那么说明ｉ也可以覆盖，等价转换问题

### 79 word search

tag: backtracking array

#### method1 : 

使用回溯的思想，引入一个辅助数组作为判断是否访问过的依据，思路还是挺清晰的

#### method2

天秀方法，减少了辅助空间的使用，思想其实仍然是将访问过的位置置为一个特殊的标志位，那么绝对不会重复，所以＾２５６

```java
private boolean exist2(char[][] board, String word, int index, int y, int x) {
        if (index == word.length()) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word.charAt(index)) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, word, index + 1, y, x + 1)
                || exist(board, word, index + 1, y, x - 1)
                || exist(board, word,index+1,y + 1, x)
                || exist(board, word,index+1,y - 1, x);
        board[y][x] ^= 256;
        return exist;
    }
```

#### summary

1. 当遍历完一个分支得出结果之后，如果为ｆａｌｓｅ，那么需要将辅助数组设置回遍历之前的结果，不然会产生干扰
2. 在数组中进行左右上下的遍历时，不要将判断条件放在递归方法之前，统一放在方法开始

### 54. Spiral Matrix

#### method1

```java
boolean right = true, down = false, left = false, up = false;
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];

        List<Integer> list = new ArrayList<>();
        int total = matrix.length * matrix[0].length;
        int i = 0, j = 0;
        while (true) {
            if (total == 0)
                break;

            if (right) {
                if (j == matrix[i].length || visit[i][j]) {
                    right = false;
                    down = true;
                    i++;
                    j--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    j++;
                }
            }

            if (down) {
                if (i == matrix.length || visit[i][j]) {
                    down = false;
                    left = true;
                    i--;
                    j--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    i++;
                }
            }

            if (left) {
                if (j == -1 || visit[i][j]) {
                    left = false;
                    up = true;
                    j++;
                    i--;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    j--;
                }
            }

            if (up) {
                if (i == -1 || visit[i][j]) {
                    up = false;
                    right = true;
                    i++;
                    j++;
                    continue;
                } else {
                    list.add(matrix[i][j]);
                    visit[i][j] = true;
                    i--;
                }
            }

            total--;
        }

        return list;
```



#### method2

同样的原理，但是对方向的选择进行了改进，使用数组存储方向，非常巧妙．当需要改变方向时，取数组中的下一个数，所以有di = (di + 1) % 4;

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}
```

#### summary:

1. 当题目是按着顺序从不同方向访问，可以通过数组控制

### 152. Maximum Product Subarray

tag: dp,array

####  wrong method

```java
public int wrongMethod(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] * nums[i] > 0)
                dp[i] = dp[i-1] * nums[i];
            else if (dp[i-1] * nums[i] < 0)
                dp[i] = nums[i];
            else if (nums[i] > 0 && dp[i-1] == 0)
                dp[i] = nums[i];
            else if (dp[i-1] == 0 && nums[i] <= 0)
                dp[i] = dp[i-1];
        }

        int max = Integer.MIN_VALUE;
        for (int num:dp) {
            max = Math.max(num,max);
        }
        return max;
    }
```

错在：

1. 没有考虑清楚两个负数，中间间隔正数的情况
2. 过多的if else 其实用嵌套的Math.max即可解决

#### method 1

因为存在两个负数夹杂正数的情况，所以使用两个ｄｐ，每个ｄｐ记录以ｉ位置为终点的最大最小乘积，那么最大的乘积和应该在nums[i] \*min[i-1],nums[i]\*max[i],nums[i]中选择出．

```java
public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(Math.max(dpMax[i-1]*nums[i],dpMin[i-1]*nums[i]),nums[i]);
            dpMin[i] = Math.min(Math.min(dpMax[i-1]*nums[i],dpMin[i-1]*nums[i]),nums[i]);
            res = Math.max(res,dpMax[i]);
        }
        return res;
    }
```

#### method2

对method1 的空间进行改进，使用两个变量替换两个ｄｐ数组

```java
public int maxProduct2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
```

曾思考过只用一个变量取代一个ｄｐ数组适用于哪些情况，总觉得，比如说ｍａｘ，ｍａｘ记录的可能是０－ｉ之间的最大值，但dpMax[i]记录的是以ｉ为终点的最大值，可能会出现这样的情况．但在本解法中比较特殊，因为max,min是随着ｉ一直更新的，意味着ｍａｘ随着ｉ一直等于dpMax[i]

#### method3

两遍扫描法

```java
public int maxProduct3(int[] nums) {
        int max=nums[0], curMax=1;
        for(int i=0; i<nums.length; i++) {
            curMax*=nums[i];
             max=Math.max(max,curMax);
            if(curMax==0) curMax=1;
        }
        curMax=1;
        for(int i=nums.length-1; i>=0; i--) {
            curMax*=nums[i];
            max=Math.max(max,curMax);
            if(curMax==0) curMax=1;
        }
        return max;
    }
```

## HARD

### 84. Largest Rectangle in Histogram

#### method1

暴力解：两重循环，从ｉ出发，进行第二次遍历，第二次遍历中，每一步都比较高度，得到可以用来作为高的最小高度，然后计算面积，最后存储最大面积

```java
public int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = heights[i];
            int minHeight = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                area = Math.max(area, (j - i + 1) * minHeight);
            }

            max = Math.max(area, max);
        }
        return max;
    }
```

method2

对于每一个ｂａｒ　ｘ，将ｂａｒ　ｘ　看作最小的一个bar

思想与method1一样，但是更巧妙

以ｉ位置为中心，找到往左往右最后一个不小于height[i]的位置left,right,然后面积就为height[i]*(right-left-1)

但往左往右找的过程中，也要一个一个地遍历，这样的话仍然是ｎ＾２的复杂度，因此作出了改进

使用两个数组lessFromLeft记录往左最后一个不小于height[i]的位置，lessFromRight记录往右最后一个不小于height[i]的位置

The only line change shifts this algorithm from **O(n^2)** to **O(n)** complexity: we don't need to rescan each item to the left - we can reuse results of previous calculations and "jump" through indices in quick manner:

复用之前的计算结果，因为如果height[p]>=height[i]的话，那显然lessFromLeft[p]中存储的值ｘ也符合height[x]<height[i]

```java
public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i])
                p = lessFromLeft[p];

            lessFromLeft[i] = p;
        }

        for (int i = heights.length-2; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i])
                p = lessFromRight[p];

            lessFromRight[i] = p;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return max;
    }
```

#### method3 

使用栈，思想和method2一致，也是找到ｉ位置上往左往右最后一个大于height[i]的位置

所以遍历数组，如果当前i的高度大于栈顶索引的高度，那么将ｉ　push进去，如果当前ｉ的高度小于栈顶ｔｐ的高度，那么说明ｉ是tp往右找第一个小于height[tp]的位置，那往左找第一个小于height[tp]的位置在哪？就是当前的栈顶tp'．为什么是当前栈顶？可以联想为什么tp会被加入栈，就是因为for遍历到tp（ｉ）时，因为height[tp] > height[tp'] (tp'是当时的栈顶),所以tｐ才会压入栈，成为新栈顶．

只能说太神了，感觉将栈运用得灵活自如

```java
public int largestRectangleArea3(int[] height) {
    int len = height.length;
    Stack<Integer> s = new Stack<Integer>();
    int maxArea = 0;
    for(int i = 0; i <= len; i++){
        int h = (i == len ? 0 : height[i]);
        if(s.isEmpty() || h >= height[s.peek()]){
            s.push(i);
        }else{
            int tp = s.pop();
            maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
            i--;
        }
    }
    return maxArea;
}
```

#### method4

分治法

ＲＭＱ思想，区间最值查询

```java
public int largestRectangleArea4(int[] height) {
        if (height.length == 0)
            return 0;
        return largestRectangleArea(height, 0, height.length - 1);
    }

    private int largestRectangleArea(int[] height, int start, int end) {
        if (start == end)
            return height[start];

        int mid = start + (end - start) / 2;
		//计算左半边的面积
        int area = largestRectangleArea(height, start, mid);
		//计算右半边的面积
        area = Math.max(area, largestRectangleArea(height, mid + 1, end));
		//计算经过中间点的面积
        area = Math.max(area, largestRectangleArea(height, start, mid, end));

        return area;
    }

    private int largestRectangleArea(int[] height, int start, int mid, int end) {
        int i = mid, j = mid + 1;

        int area = 0;
        int h = Math.min(height[i],height[j]);
        while (i >= start && j <= end){
            h = Math.min(h,Math.min(height[i],height[j]));
            area = Math.max(area,h * (j-i+1));
            if (i == start)
                j++;
            else if (j == end)
                i--;
            else {
                if (height[i-1] > height[j+1])
                    i--;
                else j++;
            }
        }

        return area;
    }
```



#### summary

1. 通过复用之前的计算结果来避免扫描每一项，降低复杂度
2. 凡是涉及往左往右查找的，都可以尝试运用分治法

### 41. First Missing Positive

#### method1

在这类找具有连续性质的某个数字时，ｓｅｔ非常好用，而且速度还快，只是要求占用Ｏ（ｎ）的空间

将数组所有数都放进ｈａｓｈｓｅｔ中，然后从１开始查找，当查找不到时证明缺失

```java
public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int i = 1;
        while (set.contains(i))
            i++;
        return i;
    }
```

#### method2

为了避免Ｏ（ｎ）的空间使用，最好的办法就是将数字放在它该放的位置，即Ａ［ｉ］＝ｉ＋１，其中负数和大于数组长的数可以不用考虑（反正就让他们放在原位，遍历到那个位置发现不满足，那么自然结果就是那个位置）

难点在理解第二个ｉｆ上，nums[i]相当于ｉ（每个positive number），那么 nums[i]-1就相当于他们在数组中该放的位置，如果不想等，就说明不满足Ａ［ｉ］＝ｉ＋１，因此交换ｉ和nums[i]-1两个位置的值，确保nums[i]这个值在它该在的位置上

```java
public int firstMissingPositive2(int[] nums){
        int i = 0;
        while (i < nums.length){
            if (nums[i] == i+1 || nums[i] <= 0 || nums[i] > nums.length) i++;
            else if (nums[nums[i] - 1] != nums[i]) swap(nums,i,nums[i]-1);
            else i++;
        }

        int res = 0;
        while (res < nums.length && nums[res] == res+1){
            res++;
        }

        return res+1;
```

#### summary:

1. 在查找连续性质的数中,swap往往是避免Ｏ（ｎ）空间的好办法
2. ｓｅｔ也是

### 4. Median of Two Sorted Arrays

#### method1

算法的思想是使用getKth()，即得到第ｋ大的数．用ｋ表示两个数组中第ｋ大的数，可能不好理解的就是为什么是ｋ／２，这里应用了二分查找思想，aStart+k/2-1即是第ｋ大的数中左边的中间值，bStart+k/2-1即是第ｋ大的数中右边的数中的中间值，如果左边的中间值小于右边的中间值，证明左边的指针应该往右移动，而新起点自然就是这个左边的中间值，所以是aStart+k/2.因为少了k/2个数，所以新第ｋ大的数就是k-k/2

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getKth(nums1, 0, nums2, 0, l) + getKth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    private double getKth(int[] nums1, int aStart, int[] nums2, int bStart, int k) {
        if (aStart > nums1.length - 1) return nums2[bStart + k - 1];
        if (bStart > nums2.length - 1) return nums1[aStart + k - 1];
        if (k == 1) return Math.min(nums1[aStart], nums2[bStart]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k/2 - 1 < nums1.length) aMid = nums1[aStart + k/2 - 1];
        if (bStart + k/2 - 1 < nums2.length) bMid = nums2[bStart + k/2 - 1];

        if (aMid < bMid)
            return getKth(nums1, aStart + k/2, nums2, bStart, k-k/2);
        else return getKth(nums1, aStart,nums2,bStart+k/2,k-k/2);
    }
```

#### summary:

1. 在有序数组中查找某个值，自然想到二分查找，二分查找的推进是在两个中间值间推荐．现在突然看似乎很顺其自然的感觉？没什么可总结的．．．