package com.hai.ijavase.sort;

public class QuickSort {

    //一般认为 7 - 50 个元素范围的数组为小数组
    private static final int MAX_LENGTH_SORT = 50;

    public void quickSort(int[] a) {
        qSort1(a, 0, a.length - 1);
        //打印输出
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void qSort1(int[] a, int low, int high) {
        int pivot = 0;
        if (low < high) {
            //将数组a一分为二
            pivot = partition1(a, low, high);
            //对第一部分进行递归排序
            qSort1(a, low, pivot);
            //对第二部分进行递归排序
            qSort1(a, pivot + 1, high);
        }
    }

    private int partition1(int[] a, int low, int high) {
        //用第一个元素作为枢轴记录
        int pivotkey = a[low];
        while (low < high) {
            //将比枢轴记录小的交换到低端
            while (low < high && a[high] >= pivotkey) {
                high--;
            }
            swap(a, low, high);
            //将比枢轴记录大的交换到高端
            while (low < high && a[low] <= pivotkey) {
                low++;
            }
            swap(a, low, high);
        }
        //返回枢轴所在的位置
        return low;
    }

    //    快速排序算法的优化
//    优化一：优化枢轴的选取位置
//    可以发现我们上面的算法中，枢轴的选取是在第一个位置的，这种选取枢轴位置的最大弊端就是很可能在调用partition函数的时候，只更换了两个元素的位置，而其他位置的元素并没有发生变化，
//    这就是上面分析中提到的最坏的情况，发现导致这种情况的根源在于选取的枢轴的大小要么太大要么太小（因为太大或者太小，high或low的值只能减小一个位置或者增加一个位置），那么优化的思路就是让枢轴既不会太大又不会太小。
//    所以可以采用三数取中法：这种方法就是取三个关键字先进行排序，将中间的数作为枢轴，一般是取左端、右端和中间的三个数。这样排序之后就能基本保证枢轴既不会太大又不会太小。
    private int partition2(int[] a, int low, int high) {
        //用第一个元素作为枢轴记录
        int pivotkey = 0;
        //计算数组中间的下标
        int m = low + (high - low) / 2;
        if (a[low] > a[high]) {
            swap(a, low, high);
        }
        if (a[m] > a[high]) {
            swap(a, m, high);
        }
        if (a[low] > a[m]) {
            swap(a, low, m);
        }
        pivotkey = a[low];
        while (low < high) {
            //将比枢轴记录小的交换到低端
            while (low < high && a[high] >= pivotkey) {
                high--;
            }
            swap(a, low, high);
            //将比枢轴记录大的交换到高端
            while (low < high && a[low] <= pivotkey) {
                low++;
            }
            swap(a, low, high);
        }
        //返回枢轴所在的位置
        return low;
    }

    //    优化二：优化不必要的交换
//    经过上面的优化，我们选取枢轴关键字基本是适中大小的，分析枢轴的位置变化过程，从最后一个位置到第一个位置，再到中间那个位置。
//    其实枢轴的目标就是中间那个位置，所以在枢轴到达中间位置的很多交换其实是不必要的。
    private int partition3(int[] a, int low, int high) {
        //用第一个元素作为枢轴记录
        int pivotkey = 0;
        //计算数组中间的下标
        int m = low + (high - low) / 2;
        if (a[low] > a[high]) {
            swap(a, low, high);
        }
        if (a[m] > a[high]) {
            swap(a, m, high);
        }
        if (a[low] > a[m]) {
            swap(a, low, m);
        }
        pivotkey = a[low];
        //把枢轴元素备份到一个临时变量中
        int temp = pivotkey;
        while (low < high) {
            //将比枢轴记录小的交换到低端
            while (low < high && a[high] >= pivotkey) {
                high--;
            }
            //采用替换而不是交换的方式操作
            a[low] = a[high];
            //将比枢轴记录大的交换到高端
            while (low < high && a[low] <= pivotkey) {
                low++;
            }
            //采用替换而不是交换的方式操作
            a[high] = a[low];
        }
        //将枢轴的值替换回给a[low]
        a[low] = temp;
        //返回枢轴所在的位置
        return low;
    }

    //    优化三：优化数据量较小时的排序
//    在数据量较小的时候使用简单排序算法反而更简单，更高效，正如排序数组{2,1,3}的时候，使用简单排序算法比如直接插入排序只要两次比较久搞定了，使用快速排序的话还要划分，明显效率低一些。
//    所以我们可以对partition函数进行小数组的优化，有资料认为当数组的长度不大于7的时候算是小数组，也有认为是50，这个其实不是最重要的，这个时候就使用直接插入排序算法就很好用。
    private void qSort2(int[] a, int low, int high) {
        int pivot = 0;
        if (low < high && (high - low) > MAX_LENGTH_SORT) {
            //MAX_LENGTH_SORT暂且定为50
            //将数组a一分为二
            pivot = partition3(a, low, high);
            //对第一部分进行递归排序
            qSort2(a, low, pivot);
            //对第二部分进行递归排序
            qSort2(a, pivot + 1, high);
        } else {
            //小数组的时候使用直接插入排序
            new InsertSort().insertSort(a);
        }
    }

    //    优化四：优化递归操作
//    递归对性能较大，如果递归的层次很多的话将会造成栈溢出，因此如果能够能够减少递归次数的话将会对函数的性能提高不少。
    private void qSort3(int[] a, int low, int high) {
        int pivot = 0;
        if (low < high && (high - low) > MAX_LENGTH_SORT) {
            while (low < high) {
                //将数组a一分为二
                pivot = partition3(a, low, high);
                //对第一部分进行递归排序
                qSort3(a, low, pivot);
                //对第二部分进行尾递归,这里之所以可以将pivot+1赋给low，是因为一/次递归结束
                //之后，low的值就没有用处了。下一次递归调用的执行的就是qSort(a,pivot + 1,high)
                low = pivot + 1;
            }
        } else {
            //小数组的时候使用直接插入排序
            new InsertSort().insertSort(a);
        }
    }

    private void swap(int[] a, int low, int high) {
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
    }

    public static void main(String[] args) {
        new QuickSort().quickSort(new int[]{50, 10, 90, 30, 70, 40, 80, 60, 20});
    }
}