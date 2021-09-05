package com.lh.base;

/**
 * LeetCode 200 岛屿数量
 *      网格类 DFS 搜索
 *
 * @author lh
 */
public class NumIslands {

    public int numIslands(char[][] grid) {

        // dfs 模板
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // 岛屿
                if(grid[i][j] == '1'){
                    count++;
                    // 遍历后 被标记为 2
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    void dfs(char[][] grid, int r, int c){
        // 1. base case
        if(!inArea(grid, r, c)){
            return;
        }
        if(grid[r][c] != '1'){
            return;
        }
        // 标记
        grid[r][c] = '2';

        // 2. 访问相邻结点
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);

    }

    boolean inArea(char[][] grid, int r, int c){
        return r>=0 && r<grid.length
                && c>=0 && c<grid[0].length;
    }

}
