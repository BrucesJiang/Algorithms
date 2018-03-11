# Algorithms-4
算法 第四版书中算法以及练习题代码实现

## 等价性
两个对象相等意味着什么？ 如果我们用相同类型的两个引用变量a和b进行等价性测试(a == b),
我们检测的是他们的`标识`是否相同，也就是**引用** 是否相同。一般用例希望能够检查数据
类型的值（对象的状态），是否相同或者实现某种针对该类型的规则是否相同。Java语言为Integer,
Double和String等标准数据类型以及一些如File和URL的复杂数据类型实现提供了实现。在处理这些
类型的数据时，可以直接使用内置的实现。例如，如果x和y均为String类型的值，那么当且仅当x和
y的长度相同为每个位置的字符均相同时x.equals(y)的返回值为true.当我们定义自己的数据类型时，
需要重载equals()方法（与之匹配的，同时需要重载hashCode()方法）。Java约定equals()必须是
一种`等价关系`。 它必须有：
1. `自反性` x.equals(x) 为true
2. `对称性` 当且仅当y.equals(x)为true时，x.equals(y)返回true
3. `传递性` 如果x.equals(y)和y.equals(z)均为true，则 x.equals(z)也将为true
另外， 它必须接受一个Object为参数并满足一下性质：
4. `一致性` 当两个对象均未被修改时，反复调用x.equals(y)总会返回相同的值
5. `非空性` x.equals(null)总是返回false

它通过以下步骤做到了以上几点：

- 如果该对象的引用和参数对象的引用相同，返回true.这项测试在成立时能够免去其他所有测试工作。
- 如果参数为空(null), 根据约定返回false（还可以避免在下面的代码中使用空引用）
- 如果两个对象的类不同，返回false。要得到一个对象的类，可以使用getClass()方法。请注意，
我们会使用==来判断Class类型的对象是否相等，因为同一中类型的所有对象的getClass()方法一定能够
返回相同的引用。
- 将参数对象的类型从Object转到我们的对象,例如Date中的代码(因为这项测试已经通过，这种转换必然
成功)
- 如果任意实例变量的值不相同，返回false.对于其他类，等价性测试方法可能不同。

```java
public class Date{
    private final int month;
    private final int day;
    private final int year;
    
    public Date(int m, int d, int y) {
        month = m; 
        day = d; 
        year = y;
    }
    
    public int month() {
        reutrn month;
    }
    
    public int day() {
        return day;
    }
    
    public int year(){
        return year;
    }
    
    public String toString() {
        return month() + "/" + day() + "/" + year();
    }
    
    public boolean equals(Object x) {
        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;
        Date that = (Date)x;
        if(this.day != that.day || this.month != that.month || this.year != that.year) return false;
        return true;
    }
}
``` 
