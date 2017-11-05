package me.wonwoo.sorter;

import net.sf.cglib.util.ParallelSorter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SorterTests {

  @Test
  void testParallelSorter() throws Exception {
    Integer[][] value = {
        {4, 3, 9, 0},
        {2, 1, 6, 0}
    };
    ParallelSorter.create(value).mergeSort(0);
    for(Integer[] row : value) {
      int former = -1;
      for(int val : row) {
        assertTrue(former < val);
        former = val;
      }
    }
  }
}
