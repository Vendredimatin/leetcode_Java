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

### 53. Maximum Subarray

#### method 1

使用贪心，声明一个变量curMax代表当前连续子序列的最大和，每往后加一个数就判断是否＜０，如果小于０，那就没必要继续往下加，令curMax = 0

```java
public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);

            if (cur <= 0)
                cur = 0;

        }
        return max;
    }
```



#### method 2

使用动态规划，dp[i]表示以nums[i]结尾的最大子数组和，所以dp[i] = (dp[i-1] + nums[i],nums[i])
*而不是表示为dp[i,j]表示从i到j的最大子数组，有两个参数的时候更灵活，计算更复杂，而dp[i]只需考虑是dp[i-1]大还是dp[i-1]+nums[i]大

但要注意，在最大连续乘积和问题中，需要对转移方程作出一点改进，因为可能出现两个负数夹杂１个正数的情况

```java
public int maxSubArray2(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }
```

#### method 3

分治法

将一个数组分为两份，那么最大子数组只能从三个地方得到
* 1、在左边的最大子数组
* 2、在右边的最大子数组
* 3、穿过中间节点的最大子数组

这个问题使用分治法倒是很意外，没想到这样的问题也可以用分治法．如果能将大问题化为小问题，那么都应该尝试一下分治法

```java
public int maxSubArray3(int[] nums){
        return maxSubArraySum(nums,0,nums.length-1);
    }

    private int maxSubArraySum(int[] nums, int l, int h){
        if (l == h)
            return nums[l];

        int m = (l+h)/2;

        return Math.max(Math.max(maxSubArraySum(nums,l,m),maxSubArraySum(nums,m+1,h)),
                crossMidSum(nums,l,m,h));
    }

    private int crossMidSum(int[] nums,int l, int m, int h){
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum,sum);
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = m+1; i <= h; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum,sum);
        }

        return leftSum+rightSum;
    }
```

#### summary;

1.  动态规划dp[i]含义收集：dp[i]表示以nums[i]结尾的最大子数组和
2. 在开始想问题之前，思考是不是能用分治法

### 26. Remove Duplicates from Sorted Array

tag: array, two pointer

#### method 1

用两根指针，快慢指针法，慢指针left指示要被覆盖的位置，快指针right 判断是否出现不同的数

```java
public int method1(int[] nums){
    int res = 1;
    for (int right = 1,left = 1; right < nums.length; right++) {
        if (nums[right] != nums[right-1]){
            nums[left++] = nums[right];
            res++;
        }
    }

    return res;
}
```

####　summary:

1. 两根指针法中的快慢指针：两个指针的方向相同
2. 

### 88. Merge Sorted Array

tag: array, two pointers

#### method 1

O(1) 空间复杂度的方法，两根指针法

如果从前往后进行排序，那么要将后面的点依次往后排，如果从后往前排，就能解决这个问题

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m-1;
        int right = n-1;

        for (int i = m+n-1; i >= 0 && left>= 0 && right >= 0; i--) {
            if (nums1[left] > nums2[right]){
                nums1[i] = nums1[left];
                left--;
            }else {
                nums1[i] = nums2[right];
                right--;
            }
        }

        if (right >= 0){
            for (int i = right; i >= 0; i--) {
                nums1[i] = nums2[i];
            }
        }

        System.out.println(Arrays.toString(nums1));
    }
```

#### summary:

1. 在和array、有序相关的问题背景下，如果正向遍历难度比较大，可以反向思维，考虑反向遍历

### 189. Rotate Array

tag: array

#### method 1 使用额外数组

#### method 2 Using Cyclic Replacements 

使用循环替代，这个办法中要使用4个变量，current代表当前要移动的数，curIndex代表当前要移动的数的位置，next代表 current要移动到的位置上原本的数，nextIndex代表 current要被移动到的位置。基于的规则是这样的，每一次把当前数移到下一个它应该在的位置nextIndex上，为了避免使用额外数组存储，把nextIndex上的数重新赋给current，此时的next变为下一轮移动的current

但使用这个方法要注意一点，或者说对数组循环都要注意点，当n是k的倍数时，很可能循环回原来的位置，举例如下：

nums = {1,2,3,4,5,6}, k=2

我们从位置0开始，移动三次，得到{5,2,1,4,3,6}，此时cureentIndex=2重新指回了1，然后就会一直循环下去，因此为了解决这个问题，要特殊处理，如下算法，for中多一层循环，检测是否回到了当初的位置

```java
public void rotate(int[] nums, int k) {
        int nextIndex;
        int current;
        for (int i = 0; i < nums.length; curIndex++) {
            int start = curIndex;
            current = nums[curIndex];
            do {
                int nextIndex = (curIndex + k) % nums.length;
                int next = nums[nextIndex];
                nums[nextIndex] = current;
                current = next;
                curIndex = nextIndex;
                i++;
            }while (start != curIndex);
    }
}
```
#### method 3

先反转整个数组，然后再翻转前k个元素，再翻转后n-k个元素

```java
public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
```



#### summary:

1. 想要讲一组数往后/前移动k个位置，可以先移动整组数，然后再移动前k个，再移动后n-k个
2. 循环替代中要注意是否会回到原位的情况





## MEDIUM

### 238. Product of Array Except Self

tag: array

#### method 1

动态规划，使用两个动态数组。leftDp[i] 表示i左边的数的乘积，rightDp[i] 表示i右边的数的乘积，那么res[i] = leftDp[i] * leftDp[i]

```java
public int[] productExceptSelf1(int[] nums) {
    int i, j;

    int[] leftDP = new int[nums.length];
    int[] rightDP = new int[nums.length];

    int leftProduct = 1;
    int rightProduct = 1;
    leftDP[0] = 1;
    rightDP[nums.length-1] = 1;

    for (i = 1, j = nums.length-2; i < nums.length; i++,j--) {
        leftProduct *= nums[i-1];
        rightProduct *= nums[j+1];
        leftDP[i] = leftProduct;
        rightDP[j] = rightProduct;
    }

    int[] answer = new int[nums.length];
    for (int k = 0; k < nums.length; k++) {
        answer[k] = leftDP[k]*rightDP[k];
    }

    return answer;
}
```

#### method 2

观察method 1，其实不算严格意义上的动态规划，method 1使用了两个额外的数组，深入考虑我们发现，其实res[i] 相当于乘了左边的乘积之后乘右边的乘积，method 1是同时乘，这里可以把这两个过程分开，在一次循环中遍历两次

```java
public int[] productExceptSelf2(int[] nums) {
    int i, j;
    int leftProduct = 1;
    int rightProduct = 1;

    int[] answer = new int[nums.length];

    for (int k = 0; k < answer.length; k++) {
        answer[k] = 1;
    }

    for (i = 1, j = nums.length-2; i < nums.length; i++,j--) {
        leftProduct *=nums[i-1];
        rightProduct *= nums[j+1];

        answer[i] *= leftProduct;
        answer[j] *= rightProduct;
    }

    return answer;
}
```

#### method 3

相当于将method 2 中的一个循环拆成两个循环

```java
public int[] productExceptSelf3(int[] nums) {
    int[] result = new int[nums.length];
    for (int i = 0, tmp = 1; i < nums.length; i++) {
        result[i] = tmp;
        tmp *= nums[i];
    }
    for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
        result[i] *= tmp;
        tmp *= nums[i];
    }
    return result;
}
```

#### summary：

1. 优化时考虑能否将诸如储存累乘结果的数组简化为一个变量
2. 一次循环两次遍历



### 78. Subsets

tag： array, backtracking,bit manipulation

#### method 1

使用回溯/递归的办法，list中每新添加一个数，就将它加入结果集中
subsets里面的循环是寻找递归的起点
find里面的循环是寻找第n层递归的起点，依次从前往后添加元素，以避免重复，第n层递归时，添加长度为n的子集

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();

    res.add(new ArrayList<>());
    for (int i = 0; i < nums.length; i++) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[i]);
        res.add(list);
        find(list,nums,i+1,res);
    }

    return res;
}

public void find(List<Integer> list,int[] nums, int start,List<List<Integer>> res){
    if (start == nums.length)
        return;

    for (int i = start; i < nums.length; i++) {
        List<Integer> newTmp = new ArrayList<>(list);
        newTmp.add(nums[i]);
        res.add(newTmp);
        find(new ArrayList<>(newTmp),nums,i+1,res);
    }
}
```



#### method 2

对解法一的优化，这样就少些声明，少用些空间并且简洁，和permutation一样，remove新添加的元素以避免创建新的数组

```java
public List<List<Integer>> subsets2(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    list.add(new ArrayList<>(tempList));
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

#### method 3

遍历数组，每次遍历都在前一次迭代的每一个结果后面加上当前遍历的数，当然，加之前要保留之前迭代的每一个结果

```java
public List<List<Integer>> subsets3(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    list.add(new ArrayList<>());

    for (int num:nums) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            List<Integer> newList = new ArrayList<>(list.get(i));
            newList.add(num);
            list.add(newList);
        }
    }

    return list;
}
```

#### method 4

位操作

```java
/**
 * 位操作，没太看得懂，留着
 *https://leetcode.com/problems/subsets/discuss/27278/C%2B%2B-RecursiveIterativeBit-Manipulation
 * @param nums
 * @return
 */
public List<List<Integer>> subsets4(int[] nums) {
    int n = nums.length;
    List<List<Integer>> subsets = new ArrayList<>();
    for (int i = 0; i < Math.pow(2, n); i++)
    {
        List<Integer> subset = new ArrayList<>();
        for (int j = 0; j < n; j++)
        {
            if (((1 << j) & i) != 0)
                subset.add(nums[j]);
        }
        Collections.sort(subset);
        subsets.add(subset);
    }
    return subsets;
}
```

#### summary:

1. 对得到所有子集/所有排列的递归总结
   1. 递归内的循环是找第n层的的起点
   2. 为了避免重复，要严格从左到右遍历
2. 对得到所有自己/所有排列，也可以递归处理，每次都对所有的list添加当前数



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



### 287. Find the Duplicate Number

tag: array, binary search, two pointers

#### method 1 sort

#### method 2 set

#### method 3 环检测算法（Floyd's Tortoise and Hare）

这是链表环中的快慢指针迁移运用过来的算法，因为要找重复的数，并且不运用辅助的空间，那就是环检测算法。只是数组的环检测算法跟链表的环不一样，数组的环是由索引i和对应的值nums[i]构成的环, nums[i]作为下一步的i，因为有两个索引它们对应的值重复，所以构成了环

环检测算法的解释可看：https://blog.csdn.net/xyzxiaoxiong/article/details/78761940作了详细的解释
phase1 tortoise即第一次相遇点
 phase2 因为在h点第一次相遇，而为什么b=F在链接中也解释的很清楚，虽然这个公式不是太准确，但可以由小及大

 第一个循环就是hare一次走两步，tortoise一次走一步，那么在有环的情况下，他们迟早相遇，tortoise即为相遇点
 第二个循环，令ptr1为头指针，令ptr2为相遇的几点，因为b=F那么，那么他们每次走一步，那么将在入口节点相遇

```java
public int findDuplicate(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0];
    int hare = nums[0];
    do {
        tortoise = nums[tortoise];
        hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Find the "entrance" to the cycle.
    int ptr1 = nums[0];
    int ptr2 = tortoise;
    while (ptr1 != ptr2) {
        ptr1 = nums[ptr1];
        ptr2 = nums[ptr2];
    }

    return ptr1;
}
```

#### method 4 Binary Search

使用鸽笼原理，当数组内的数<= mid的个数多余mid时，将search范围放在mid～high，
当个数小于等于mid时，将search范围放在low～mid，最后返回low/high（指的是索引）
巧妙的运用二分法
如n=10,mid=5,如果数组内小于等于5的个数多余5个，那么根据鸽笼原理，重复的数必在1～5内

[原题解]: https://leetcode.com/problems/find-the-duplicate-number/discuss/72844/Two-Solutions-(with-explanation)%3A-O(nlog(n))-and-O(n)-time-O(1)-space-without-changing-the-input-array

```java
public int findDuplicate2(int[] nums) {
    int low = 1, high = nums.length - 1;
    while (low < high) {
        int mid = (low + high) / 2;
        int count = 0;

        for (int num : nums) {
            if (num <= mid) count++;
        }

        if (count <= mid) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }

    return high;
}
```

#### Summary:

1. 环检测算法构建环，找重复
2. 二分法和鸽笼原理的结合



### 48. Rotate Image

tag: array

#### method 1

规律：先根据斜对称轴交换，再根据y对称轴交换

```java
public void rotate(int[][] matrix) {
    int n = matrix.length;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }

    int mid = matrix.length/2;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < mid; j++) {
            int tmp = matrix[i][j];
            int symmetricalY = n-1-j;
            matrix[i][j] = matrix[i][symmetricalY];
            matrix[i][symmetricalY] = tmp;
        }
    }

}
```

#### summary:

1. summary:之前就找到了规律了，先根据斜对称轴对折，再根据中线y轴对折，但是想的太复杂，想一口气弄完两次对折，应该像这样，一次一次弄，分别弄开

###  62. Unique Paths

tag : array, dynamic programming

很显然就使用dp，因为下一步的解决方案显然等于经过right的上一步的解决方案+经过down的上一步解决方案

dp\[i][j] = dp\[i-1][j] + dp\[i][j-1]

#### method 1

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];

    dp[0][0] = 1;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (i != 0)
                dp[i][j] += dp[i-1][j];
            if (j != 0)
                dp[i][j] += dp[i][j-1];
        }
    }

    return dp[m-1][n-1];
}
```

### 289. Game of Life

tag: array

#### method 1

因为是同时发生的，所以不能先修改一部分，再去修改一部分，后面的数会受之前的修改影响，因此，不如使用一个数组，提前存储每个元素活邻居的数量。

 使用一个dp数组，记录每个元素的活邻居
* 1.少于两个活邻居的活细胞将死去
* 2.有两个活邻居或三个活邻居的活细胞能够继续活下去
* 3.任何有3三个以上的活邻居的活细胞将死去
* 4.有三个活邻居的死细胞能够复活

```java
public void gameOfLife(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dp[i][j] += searchLive(board,i-1,j-1)+searchLive(board,i-1,j)+searchLive(board,i-1,j+1);
                dp[i][j] += searchLive(board,i,j-1) + searchLive(board,i,j+1);
                dp[i][j] += searchLive(board,i+1,j-1)+searchLive(board,i+1,j)+searchLive(board,i+1,j+1);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //live cell
                if (board[i][j] == 1){
                    if (dp[i][j] < 2)
                        board[i][j] = 0;
                    else if (dp[i][j] > 3)
                        board[i][j] = 0;
                }else{
                    if (dp[i][j] == 3)
                        board[i][j] = 1;
                }
            }
        }

        System.out.println(Arrays.toString(board));
    }

    public int searchLive(int[][] dp, int i, int j){
        if (i == -1 || j == -1 || i == dp.length || j == dp[0].length)
            return 0;

        if (dp[i][j] == 0)
            return 0;
        else return 1;
    }
```



#### method 2

O(1)的space，既然无法借助额外空间，那么只能在数组本身标记，思考发现本题中只会有两个数0，1，因此可以用其他数字来代表修改状态，第二次遍历时将这些数字修改回0，1即可

更优秀的解法，通过位操作，解决了O（n）的空间问题
* 因为总共就四种状态0-》0（00），0-》1（10），1-》0（01），1-》1（11）
* 只需考虑10和11,即复活和保持生存，对应于十进制分别是2和3
* 巧妙地将原本的0和1值与代表转换的四个状态的值对应起来，非常smart

```java
//https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
```



#### summay 

1. 提前使用一个数组存储原始信息，防干扰
2. 要保证O（1）的space，可考虑对原数组位置上进行标记，标记的数不能和原来的数冲突
3. 使用Math.max/min来判断边界，减少if
4. method 2 中还考虑了有几种状态，从而使用位操作



### 11. Container With Most Water

tag: array, two pointers

#### method 1 brute force

暴力解，两层遍历循环. 简单地考虑每个可能的线对的面积，并找出其中的最大面积。

```java
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }
}
```

#### method 2 two pointers

找到最高的线，然后找到左边的最高的线和右边的最高的线，他们之间的面积就是最大的面积。如果有多个相同的最高线，可能要尝试多次

#### method 3

两根指针法，left和right，每次移动短的那一段，因为如果移动大的，那么在另一端不变的情况下，水量可能会变小，首先宽变小一位，而高度始终是以小的算的。相对来说移动小的，在宽度变小的情况下，更容易获得更大的面积

```java
public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right])
                    * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
```



#### summary:

1.  思考逻辑链：算面积->需要知道长宽-> 宽的话取决于两边短的一边-> 两边-> 两个指针法
2. 以什么样得标准遍历能得到更大的面积，移动短那一段



### 75. Sort Colors

tag: array, two pointers

#### method 1

two-pass 算法，第一遍记录0，1，2出现的次数，第二遍重写数组

#### method 2

one-pass 算法，两根指针法，交换0和2，这样当0和2排序好后，1也会自动排好序的

```java
public void sortColors(int[] nums) {
        int left=0,right = nums.length-1;

        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0 && left < right) swap(nums,i,left++);
            else if (nums[i] == 2 && left < right) swap(nums,i--,right--);
        }
    }

    public void swap(int[] nums,int desc,int source){
        int tmp = nums[desc];
        nums[desc] = nums[source];
        nums[source] = tmp;
    }
```

#### summary:

1. 需要在数组上交换而不引入额外空间-> 两根指针
2. 反面思考：在考虑的时候思考过于复杂，总是想着怎么排布三个数，其实只要排好0和2，那么1就可以自动排好。对于n维的问题，其实有时候思考处理n-1维就够了



### 105. Construct Binary Tree from Preorder and Inorder Traversal

tag : tree, Depth-first Search, tree

前序第一个肯定是当前根节点，所以在中序中查找这个根节点所在的位置，那么中序中根节点之前的数都是左子树，以后的数都是右子树

```java
 public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode head = buildTree(preorder,inorder,0,0,preorder.length-1);
        return head;
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart == inEnd)
            return new TreeNode(preorder[preStart]);
        else if (inStart > inEnd)
            return null;

        TreeNode head = new TreeNode(preorder[preStart]);
        int index = getIndex(inorder,preorder[preStart]);

        // preStart-1 即新左子树的根节点， index-1即当前根节点之前的数都属于左子树的节点
        head.left = buildTree(preorder,inorder,preStart+1,inStart,index-1);
        // index+preStart-instart+1 因为在前序中，根节点之后会先接左子树节点，然后才是右子树的节点，所以index-instart就是左子树节点的个数，preStart + 左子树节点数+1 就是在前序中右子树开始的位置，在中序中，从当前根节点的位置index出发，所以是index + 1
        head.right = buildTree(preorder,inorder,index+preStart-inStart+1, index+1, inEnd);

        return head;
    }

    public int getIndex(int[] order, int key){
        for (int i = 0; i < order.length; i++) {
            if (key == order[i]) return i;
        }
        return -1;
    }
```

### 79. Word Search

tag : array, backtracking

#### method 1

使用一个访问辅助数组，在每次访问时置为true，如果这次访问没有得到完整的目标word，就再把辅助访问数组对应的值置为false

```java
boolean[][] visit;

public boolean exist(char[][] board, String word) {
    visit = new boolean[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == word.charAt(0)) {
                if (exist2(board, word, 0, i, j))
                    return true;
            }
        }
    }
    return false;
}

private boolean exist(char[][] board, String word, int index, int i, int j) {
    if (index == word.length())
        return true;

    if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visit[i][j] || board[i][j] != word.charAt(index) || visit[i][j])
        return false;

    visit[i][j] = true;
    if (exist(board, word, index + 1, i + 1, j) || exist(board, word, index + 1, i, j + 1) || exist(board, word, index + 1, i - 1, j) || exist(board, word, index + 1, i, j - 1))
        return true;

    visit[i][j] = false;
    return false;
}
```

#### method 2

可以对method 1 进行优化，使得空间复杂度变为O(1)

即在访问时，将该位置的字符置为其他不存在干扰的字符，当访问结束没有得到完整的目标word时，再置回，运用了位操作

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

#### summary：

1. 需要记录访问呢轨迹时，使用一个访问数组，并且在这个分支回溯完成后，把访问数组中该位置置回原来的值
2. 也可以不用访问数组，那么就必须标记这个位置的数已经被访问过，可以
   1. 使用其他根本不会干扰的字符，结束之后再置回原来的数，使用位操作^256
   2. 在不会再被访问的位置标记该数（见73）



### 162. Find Peak Element

tag: array, binary search

#### method 1

线性扫描，遍历每一个数，比较是否左边的数要低一点，右边的数要高一点。

当然，如果发现右边的数要低一点，就没有必要再看右边的数是否符合，直接看右边的数的下一个数

```java
public int findPeakElement(int[] nums) {
    for (int i = 1; i < nums.length-1; i++) {
        if (nums[i] > nums[i+1]){
            if (nums[i] > nums[i-1])
                return i;
            else i++;
        }
    }

    return 0;
}
```

#### method 2  binary search

这样的题使用二分查找，一开始还是很惊奇的，因为印象中都是对有序数组才使用二分法，但这是对二分法的误解，要理解二分法的精髓在于在每一步都减少待搜索的区间

考虑本题，一个数mid根据和其右边的数进行比较，可以得到该数是在下降的斜坡上还是在上升的斜坡，如果右边的数更小，可以认为是在下降的斜坡上，那么峰值一定是在mid的左边（包含其本身）；如果右边的数更大，可以认为是在上升的斜坡上，那么峰值一定是在mid的右边，所以搜索范围可以限制在mid及其右边的子数组

```java
public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
```

当然，比如右边的数比mid更大，是在上升的斜坡中，将舍弃mid左边的子数组中也可能存在peak元素，但本题不是求最大的数，本题只要求找到一个peak即可，甚至边界值（i=0，i=n-1）也算peak，所以才可以这样使用二分法

#### summary:

1. 使用二分法减少搜索空间





### 73. Set Matrix Zeros

tag： array

#### method 1 

使用辅助数组表示那些列和行会被置0

#### method 2

将原本为0的位置标记为其他数n，但在实际测试中，测试用例总会有原本数组中就出现这个数n，所以造成干扰，因此这个方法有一定缺陷

#### method 3

还是标记法，但为了降低干扰的程度，当一个数为0，就把其所在行和列的第一个数置为0，然后遍历第一行和第一列，如果有0，那么就将改行/该列置为0。这样，只会干扰到第一列和第一行，因此提前遍历第一列和第一行他们本身的数确定第一列和第一行会不会被置为0

```java
public void setZeroes(int[][] matrix) {
        boolean isRowZero = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) isRowZero = true;
        }

        boolean isColZero = false;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[j][0] == 0) isColZero = true;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0){
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isRowZero){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (isColZero){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        
    }
```

#### summary:

1. 为了避免使用辅助数组，而使用标记法时，要思考将当前数进行in place 标记会不会对后面的数造成误判，所以可以考虑将干扰的程度降低到常数级别，例如本题只会影响第一行和第一列这两个数组

### 56. Merge Intervals

#### method 1

将每个区间进行排序，按照start进行排序，start小的排在前面，遍历排序后的intervals，如果第n个interval的end < 第n+1个的interval的start，那么就将第n个interval当作一个新的interval加入结果中。如果第n个interval的end >= 第n+1个的interval的start，那么就进行合并，end取两个区间中最大那一个

```java
class Solution {
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        return merged;
    }
}
```

#### summary：

1. 排序后只要认真讨论情况，就能解决这个问题

### 34. Find First and Last Position of Element in Sorted Array

tag：array， binary search

#### method 1 

遍历数组，得到最小最大

#### method 2 binary search

首先，按照正常binary search首先在mid处找到一个target，因为数组已经排序好了，如果还有其他的target，那一定是在target的左边还有右边，如果有，那就分别以mid-1作为最右边界/mid+1作为最左边界继续查找，得到position更小/更大的数

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

#### summary

1. 有序数组中查找数，很自然地就想到binary search



### 33. Search in Rotated Sorted Array

tag : array, binary search

#### method 1 binary search

观察题目发现，因为数组被旋转了，所以可以确定，左边被旋转的那一部分的第一个数比右边被旋转的部分都大，右边被旋转的最后一个数也是比左边被旋转的所有数都小，所以每次二分查找时，判断mid的左边和右边是否正常，比较target和nums[start]的大小关系，如果nums[start] 比 target大，说明target应该位于mid的右边

```java
public int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    while (start <= end){
        int mid = (start + end) / 2;
        if (nums[mid] == target)
            return mid;

        if (nums[start] <= nums[mid]){
            if (target < nums[mid] && target >= nums[start])
                end = mid - 1;
            else
                start = mid + 1;
        }

        if (nums[mid] <= nums[end]){
            if (target > nums[mid] && target <= nums[end])
                start = mid + 1;
            else
                end = mid - 1;
        }
    }
    return -1;
}
```

#### summary

1. 虽然数组被改变过，但部分还是有序的，所以仍然可以使用二分查找

### 55. Jump Game

tag: array,greedy, dynamic programming

#### method 1 backtracking

回溯遍历，每跳到一个位置i，就遍历i~i+nums[i]这些位置之中是否能过跳到终点，如果有一个能达到终点，就返回true

但这个方法超时了

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



#### method 2

自顶向下动态规划，从method 1中我们可以看出，有些位置会重复遍历，因此我们引入记忆化搜索，还会回溯，但每到一个位置i时，先查看该位置是否是能够到达终点的位置(GOOD), 还是不能到达终点的位置(BAD),如果是不知道的位置(UNKNOWN)，则遍历这个位置

通过使用dp降低了复杂度，存储了index i是否能到达终点的结果，当再经过这些index的时候不再需要计算

```java
enum Index {
        GOOD, BAD, UNKNOWN
}

Index[] index;

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

#### method 3

自底向上，从右边开始跳，递归转循环.先将每一个Index置为UNKNOWN，再把最后一个INDEX置为GOOD

```java
public boolean canJump３(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
```

#### method 4 Greedy

从method 3 中再次得到启发，如果在位置i，且i~nums[i]+i这之中有一个GOOD INDEX，那么代表位置i也是个GOOD INDEX

遍历完如果lastPos = 0 则表示起点也是good Index，那么从起点也可以跳到终点

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

#### summary：

1. （当方法超时的时候）发现是不是有些步骤被重复计算，如果有重复计算，那么就引入动态规划
2. 反向思考，问的是从起点到终点，如果能从终点到起点，结果也是等价的
3. 使用greedy 简化动态规划，进一步看清问题的本质

### 54. Spiral Matrix

tag：array

#### method 1

使用一个辅助数组表示某个位置(i,j)是否被访问过，如果下一个位置越界或者是访问过的，那说明该换方向了

```java
public List<Integer> spiralOrder(int[][] matrix) {
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
    }
```

#### method 2

上面的方法中，if和else过多过于繁杂，观察题目我们可以发现，遍历的方向是有顺序的，永远是往右，往下，往左，往上。因此，我们可以通过加法+求余来得到下一个方向，因此，也能够知道x，y该加还是该减，还是该不变

dr 表示row上的操作，dc 表示在column上的操作，当往左或往右时，行数不变，因此dr[0]/dr[2]都为0

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



#### summary：

1. 如果题目的方向是有规律的，是有循环的，那么可以使用加法和求余来控制方向！

### 15. 3Sum

tag: array, two pointers

#### method 1

可以将3sum问题转换为2sum问题，然后再用2sum的办法解决。这里使用两个指针的方法

先对数组排序，这是为了让指针更好地移动，让指针有方向

遍历每一个索引i上的数nums[i]，然后讨论i+1~length-1这个范围内是否有数的和等于nums[i]的负数

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < nums.length-2; i++) {

        if (i == 0 || (i > 0 && nums[i] != nums[i-1])){
            int low = i+1, high = nums.length-1, sum = -nums[i];
            while (low < high){
                if (nums[low] + nums[high] == sum){
                    res.add(Arrays.asList(nums[i],nums[low],nums[high]));
                    while (low < high && nums[low] == nums[low+1]) low++;
                    while (low < high && nums[high] == nums[high-1]) high--;
                    low++;high--;
                }else if (nums[low] + nums[high] < sum) low++;
                else high--;
            }
        }
    }

    return res;
}
```

#### summary :

1. 在使用两根指针的时候，考虑一下是否需要排序，让指针的移动更具有方向性
2. 当处理去除重复性问题时，既可以使用set自动去重，也可以进行排序，当数重复的时候便跳过

### 152. Maximum Product Subarray

#### method 1

单纯用Maximum Sum Subarray的方法来做本道题是错的，问题就在于dp[1]可能不仅和dp[i-1]有关，甚至和dp[i-2]有关，例如负，正，负的情况，在这样的情况下，dp不仅需要记录以i-1结尾的最大积，也需要记录以i-1结尾的最小积，因为如果异数相乘，就变成了一个负数

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

#### method 2

对method 1的改进

使用两个变量来代替两个dp

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



#### method 3 two-pass

```html
https://leetcode.com/problems/maximum-product-subarray/discuss/48302/2-Passes-scan-beats-99
```

```java
public int maxProduct3(int[] nums) {
    int max = Integer.MIN_VALUE, product = 1;
    int len = nums.length;

    for(int i = 0; i < len; i++) {
        max = Math.max(product *= nums[i], max);
        if (nums[i] == 0) product = 1;
    }

    product = 1;
    for(int i = len - 1; i >= 0; i--) {
        max = Math.max(product *= nums[i], max);
        if (nums[i] == 0) product = 1;
    }

    return max;
}
```

#### summary:

1. 当动态规划数组中，dp[i]只与dp[i-1]相关，那么这个时候可以用一个变量代替dp数组
2. 考虑算法题的时候一定要尽量考虑清楚有哪些情况，不要忙着写代码，不然这样写出的代码也很难100%过测试用例的

####

####

####

####

####

####

### 

###





## HARD

### 42. Trapping Rain Water

tag: array, two pointers, stack

#### method 1 动态规划

使用两个dp数组，left_max[i]记录i位置上其左边的最高bar，right_max[i]记录i位置上其右边最高的bar

遍历的时候，计算在位置i能够存储多少水量，就用其两边高度最小的那个减去本身高度

```java
int trap3(int[] height) {
        if (height == null) return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size], right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }
```



#### method 2 

先找到数组中bar高度最高中最后一个的索引（因为可能存在多个高度一样的bar）

还是以每个位置i上能积累多少水量的思想计算，从左往右遍历时，每次遍历到位置i，只要在i+1~maxIndex这片区域中有连续的小于height[i]的bar（j），就证明这个bar可以积累水，并且积水量等于height[i]-height[j]

从右往左也是相同的

```java
 public int trap(int[] height) {
        int sum = 0;
        int size = height.length;
        int maxIndex = 0;
        int max = height[0];
        for (int i = 1; i < size; i++) {
            if (height[i] >= max) {
                max = height[i];
                maxIndex = i;
            }
        }


        for (int i = 0; i < maxIndex; i++) {
            int h = height[i];
            int des = i;
            for (int j = i + 1; j < maxIndex && height[j] <= h; j++) {
                sum += (h - height[j]);
                des = j;
            }
            i = des;
        }

        for (int i = size - 1; i > maxIndex; i--) {
            int h = height[i];
            int des = i;
            for (int j = i - 1; j > maxIndex && height[j] <= h; j--) {
                sum += (h - height[j]);
                des = j;
            }
            i = des;
        }

        return sum;
    }
```



#### method 3 two pointers

这个方法与我之前的误区就是，我之前想的是找到一个区间，然后遍历这个区间获得这个区间的积水量，但有个问题在于，方向和突然出现的更高峰会使判断复杂
这个方法精妙在，不是着眼于区间，而是着眼于ｉ上的位置能积多少水，将每个ｉ上位置积的水加起来就行
因此要求ｉ左边或右边的最大值，这里要求根据两边最大值中的小一个进行求解，因为如果根据大的一个进行求解，那么小的一边水就会溢出
虽然微观上只求ｉ位置上的积水，但宏观来看，还是求区间的积水
将上面暴力解的思想进行优化，就得到两根指针的解法
对两边中小的一边进行计算，如果左边最大bar高度大于Ａ［left］，那么就相当于在left可以存水，然后不断更新leftMax
右边也同样道理

```java
public int trap2(int[] A) {
        int leftMax = 0, rightMax = 0;
        int left = 0;
        int right = A.length - 1;

        int res = 0;
        while (left < right) {
            if (A[left] < A[right]) {
                leftMax = Math.max(A[left], leftMax);
                if (leftMax > A[left]) res += leftMax - A[left];
                left++;
            } else {
                rightMax = Math.max(A[right], rightMax);
                if (rightMax > A[rightMax]) res += rightMax - A[right];
                right--;
            }
        }

        return res;
    }
```

#### summary:

1. 计算每一个ｉ位置上的积水，而不要直接确定区间. 因为直接确定整个区间这种宏观的做法要考虑的因素很多，所以区繁就简，从每个位置i上的积水出发。也可以理解为分解

### 128. Longest Consecutive Sequence

tag: union find, array

#### method 1 sort

直接排序求解

#### method 2 set

利用set的特性，不断地remove

使用一个set，对每一个数，检查是否有它的前继和后继，如果有则推进，感觉相当于滑动窗口的变种，使用remove方法可以减少遍历的数据量
使用sort，实际上速度更快

```java
public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int num:nums) {
            if (set.remove(num)){
                int prev = num-1;
                int next = num+1;
                while (set.remove(prev)) prev--;
                while (set.remove(next)) next++;

                res = Math.max(res, next-prev-1);
            }
        }

        return res;
    }
```

#### summary:

1. set等效代替滑动窗口



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

对于每一个bar ｘ，将barｘ　看作最小的一个bar

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