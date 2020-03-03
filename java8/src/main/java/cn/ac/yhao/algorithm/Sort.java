package cn.ac.yhao.algorithm;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 4, 8, 3, 9, 11, 14, 18, 12, 13, 15};
        System.out.println(Arrays.toString(array));
        //int[] arr =  insertSort(array);
        //quickSort(array, 0, array.length-1);
        int[] arr = bubbleSort(array);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * <li>冒泡排序</li>
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int temp;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序边界的位置，每次只需要比较到这里为止
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            //有序标记，每一轮的初始值为true,有交换则改为false
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            //如果没有减缓，数组就是有序的，跳出循环
            if (isSorted) {
                break;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @param array
     * @return arr
     */
    public static int[] insertSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length); //对array进行拷贝，不改变参数内容
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    arr[j + 1] = temp;
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * <li>快速排序</li>
     *
     * @param arr      数组
     * @param startIdx
     * @param endIdx
     * @return
     */
    public static void quickSort(int[] arr, int startIdx, int endIdx) {
        //递归结束条件：startIdx >= endIdx
        if (startIdx >= endIdx) {
            return;
        }
        //得到基准元素位置
        int pivoIndex = partition(arr, startIdx, endIdx);
        //根据基准元素，分成两部分递归
        quickSort(arr, startIdx, pivoIndex - 1);
        quickSort(arr, pivoIndex + 1, endIdx);
    }

    private static int partition(int[] arr, int startIdx, int endIdx) {
        //三数取中为基准元素
        int mid = startIdx + (endIdx - startIdx) / 2;
        //基准元素与最左边的left的元素交换int,把基准元素放到最左边
        int temp = arr[mid];
        arr[mid] = arr[startIdx];
        arr[startIdx] = temp;

        int pivot = arr[startIdx];
        int left = startIdx;
        int right = endIdx;

        while (true) {
            //控制right指针比较并左移
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            //控制left指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //如果left指针大于等于right指针，跳出循环
            if (left >= right) {
                break;
            }
            //交换left和right指向的元素
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        //把ar[left]与主元交换
        arr[startIdx] = arr[left];
        arr[left] = pivot;
        return left;
    }

    /**
     * <li>堆排序</li>
     *
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        //1、把无序数组构建成最大堆
        for (int i = (arr.length - 2) / 2; i > 0; i--) {
            downAdjust(arr, i, arr.length);
        }
        //2、循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶
        for (int i = arr.length - 1; i > 0; i++) {
            //最后一个元素和第一个元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //下沉调整最大堆
            downAdjust(arr, 0, i);
        }
        return arr;
    }

    /**
     * 下沉调整
     * 左子节点的下标为i,则右子节点的下标为2*i+1
     *
     * @param arr       带下沉的堆
     * @param parentIdx 要下沉的父节点
     * @param length    堆的有效长度
     */
    private static void downAdjust(int[] arr, int parentIdx, int length) {
        //temp保存父节点值，用于最后的赋值
        int temp = arr[parentIdx];
        int childIndex = 2 * parentIdx + 1;
        while (childIndex < length) {
            //如果有右子节点，且右子节点大于左子节点的值，则定位到右子节点
            if (childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            //如果父子节点大于等于子节点，直接跳出
            if (temp >= arr[childIndex]) {
                break;
            }
            //无需真正交换，单向赋值即可
            arr[parentIdx] = arr[childIndex];
            parentIdx = childIndex;
            childIndex = 2 * parentIdx + 1;
        }
        arr[parentIdx] = temp;
    }
}
