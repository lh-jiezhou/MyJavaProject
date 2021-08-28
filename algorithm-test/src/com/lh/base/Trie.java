package com.lh.base;

/**
 * LeetCode 208. 实现 Trie (前缀树/字典树)
 * Trie 树（又叫「前缀树」或「字典树」）是一种用于快速查询「某个字符串/字符前缀」是否存在的数据结构。
 * 其核心是使用「边」来代表有无字符，使用「点」来记录是否为「单词结尾」以及「其后续字符串的字符是什么」。
 *
 * 关于 Trie 的应用场景，8 个字：一次建树，多次查询。
 */
class Trie {

    /**
     * 维护一个前缀树
     */
    Trie[] children;
    /**
     *  标记
    */
    boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        // 26个字母
        children = new Trie[26];
        // 是否为单词结尾
        isEnd = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        // 当前结点
        Trie node = this;
        for(char c: word.toCharArray()){
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new Trie();
            }
            node = node.children[c-'a'];
        }
        // 完成插入并标记
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for(char c: word.toCharArray()){
            if(node.children[c-'a'] == null){
                // 查找匹配失败
                return false;
            }
            node = node.children[c-'a'];
        }
        // 是否为结尾
        return node.isEnd;
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
        // prefix匹配完即可
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        // 返回 True
        trie.search("apple");
        // 返回 False
        trie.search("app");
        // 返回 True
        trie.startsWith("app");
        trie.insert("app");
        // 返回 True
        trie.search("app");
    }

}

