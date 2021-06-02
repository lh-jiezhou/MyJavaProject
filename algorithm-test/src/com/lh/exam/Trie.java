package com.lh.exam;

/**
 * LeetCode 208. 实现 Trie (前缀树/字典树)
 * Trie 树（又叫「前缀树」或「字典树」）是一种用于快速查询「某个字符串/字符前缀」是否存在的数据结构。
 * 其核心是使用「边」来代表有无字符，使用「点」来记录是否为「单词结尾」以及「其后续字符串的字符是什么」。
 *
 * 关于 Trie 的应用场景，8 个字：一次建树，多次查询。
 */
class Trie {

    // 维护一个前缀树
    Trie[] children;
    boolean isEnd; // 标记

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26]; // 26个字母
        isEnd = false; // 是否为单词结尾
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this; // 当前结点
        for(char c: word.toCharArray()){
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new Trie();
            }
            node = node.children[c-'a'];
        }
        node.isEnd = true; // 完成插入并标记
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for(char c: word.toCharArray()){
            if(node.children[c-'a'] == null){
                return false; // 查找匹配失败
            }
            node = node.children[c-'a'];
        }
        return node.isEnd; // 是否为结尾
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

        Trie node = this;
        for(char c: prefix.toCharArray()){
            if(node.children[c-'a'] == null){
                return false;
            }
            node = node.children[c-'a'];
        }
        return true; // prefix匹配完即可
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True
    }

}

