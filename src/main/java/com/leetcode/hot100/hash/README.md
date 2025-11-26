# 哈希

## HashMap
常用方法：
```java
map.put(key, value);       // 插入/更新
map.get(key);              // 获取value
map.containsKey(key);      // 是否包含key
map.remove(key);           // 删除对应key的键值对
map.getOrDefault(key, 0);  // 不存在时返回默认值
map.values(); // 获取全部的值
```

遍历一个 map

```java
Map<String, Integer> map = new HashMap<>();
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
}
```
只遍历key
```java
for (String key : map.keySet()) {
    System.out.println(key);
}
```
只遍历value
```java
for (Integer value : map.values()) {
    System.out.println(value);
}
```
使用 forEach（Java 8+）
```java
map.forEach((key, value) -> {
    System.out.println(key + " = " + value);
});
```

## HashSet

```java
// 构造器
new HashSet<>()                         // 默认初始容量与负载因子
new HashSet<>(Collection<? extends E>)  // 以已有集合初始化
new HashSet<>(int initialCapacity)      // 指定初始容量
new HashSet<>(int initialCapacity, float loadFactor) // 指定容量与负载因子
// 基本增删改查
boolean add(E e)                        // 添加元素（若已存在返回 false）
boolean remove(Object o)                // 删除元素，存在则返回 true
void clear()                            // 清空所有元素
boolean contains(Object o)              // 是否包含元素
int size()                              // 集合大小
boolean isEmpty()                       // 是否为空
```

遍历一个set
```java
HashSet<String> set = new HashSet<>();
for (String s : set){} //增强 for 循环
Iterator<String> it = set.iterator();//使用 Iterator（可在遍历中删除元素）
while (it.hasNext()) {
String s = it.next();
    System.out.println(s);
}
set.forEach(System.out::println);//使用 Stream（更简洁，但不可改元素和结构）
```
# String

String 转为字符数组

```java
String s = "hello";
s.toCharArray() // 直接转化
// 手动构造
char[] arr = new char[s.length()];
for (int i = 0; i < s.length(); i++) {arr[i] = s.charAt(i);}
```

# List 

构造时
```java
List<String> list = new ArrayList<>(); //使用构造器创建可变 List
List<String> list = Arrays.asList("a", "b");//使用 Arrays.asList（固定大小 List） 这个 list 不能增删元素
List<String> list = List.of("a", "b");//使用 List.of（不可变 List，JDK 9+） 这个 list 是 不可变的，不能增删改。



```