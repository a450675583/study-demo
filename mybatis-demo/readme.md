# MyBatis Summary

###  1、MyBatis 编程步骤

>1. 创建 SqlSessionFactory 对象。 
>2. 通过 SqlSessionFactory 获取 SqlSession 对象。
>3. 通过 SqlSession 获得 Mapper 代理对象。
>4. 通过 Mapper 代理对象，执行数据库操作。
>5. 执行成功，则使用 SqlSession 提交事务。
>6. 执行失败，则使用 SqlSession 回滚事务。
>7. 最终，关闭会话。

### 2、#{} 和 ${} 的区别是什么？

`${}` 是 Properties 文件中的变量占位符，它可以用于 `XML 标签属性值`和 `SQL内部`，属于**字符串替换**。例如将 `${driver}` 会被静态替换为 `com.mysql.jdbc.Driver` ：

```xml
<dataSource type="UNPOOLED">
    <property name="driver" value="${driver}"/>
    <property name="url" value="${url}"/>
    <property name="username" value="${username}"/>
</dataSource>
```

`${}` 也可以对传递进来的参数**原样拼接**在 SQL 中。代码如下：

```xml
<select id="getSubject3" parameterType="Integer" resultType="Subject">
    SELECT * FROM subject
    WHERE id = ${id}
</select>
```

- 实际场景下，不推荐使用${} 处理拼接SQL。因为，可能有 SQL 注入的风险。

<hr/>

`#{}`是 SQL 的参数占位符，MyBatis 会将 SQL 中的 `#{}` 替换为 `?` 号，在 SQL 执行前会使用 PreparedStatement 的参数设置方法，按序给 SQL 的 `?` 号占位符设置参数值，比如 `ps.setInt(0, parameterValue)` 。 所以，`#{}` 是**预编译处理**，可以有效防止 SQL 注入，提高系统安全性。

### 3、当实体类中的属性名和表中的字段名不一样 ，怎么办？

第一种， 通过在查询的 SQL 语句中`定义字段名的别名`，让字段名的别名和实体类的属性名一致。代码如下：

```xml
<select id="selectOrder" parameterType="Integer" resultType="Order"> 
    SELECT order_id AS id, order_no AS orderno, order_price AS price 
    FROM orders 
    WHERE order_id = #{id}
</select>
```

第二种，是第一种的特殊情况。大多数场景下，数据库字段名和实体类中的属性名差，主要是前者为**下划线风格**，后者为**驼峰风格**。在这种情况下，可以直接配置如下，实现自动的下划线转驼峰的功能。

```xml
<setting name="logImpl" value="LOG4J"/>
    <setting name="mapUnderscoreToCamelCase" value="true" />
</settings>
```

第三种，通过 `<resultMap>` 来映射字段名和实体类属性名的一一对应的关系。代码如下：

```xml
<resultMap type="me.gacl.domain.Order" id=”OrderResultMap”> 
    <!–- 用 id 属性来映射主键字段 -–> 
    <id property="id" column="order_id"> 
    <!–- 用 result 属性来映射非主键字段，property 为实体类属性名，column 为数据表中的属性 -–> 
    <result property="orderNo" column ="order_no" /> 
    <result property="price" column="order_price" /> 
</resultMap>

<select id="getOrder" parameterType="Integer" resultMap="OrderResultMap">
    SELECT * 
    FROM orders 
    WHERE order_id = #{id}
</select>
```

###  4、XML 映射文件中，除了常见的 select | insert | update | delete标 签之外，还有哪些标签？

> - `<cache />` 标签，给定命名空间的缓存配置。
>
>
>  - `<cache-ref />` 标签，其他命名空间缓存配置的引用。
>
> - `<resultMap />` 标签，是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象。
>
>
> -  `<sql />`  标签，可被其他语句引用的可重用语句块。
>
>
> - `<include />` 标签，引用 `<sql />` 标签的语句。
>
> - `<selectKey />` 标签，不支持自增的主键生成策略标签。
>
> - 动态SQL相关标签：
>
> - `<if />`
>
> - `<choose />`、`<when />`、`<otherwise />`
>
> - `<trim />`、`<where />`、`<set />`
>
> - `<foreach />`
>
> - `<bind />`
>

### 5、MyBatis 动态 SQL 是做什么的？都有哪些动态 SQL ？能简述一下动态 SQL 的执行原理吗？

- Mybatis 动态 SQL ，可以让我们在 XML 映射文件内，以 XML 标签的形式编写动态 SQL ，完成逻辑判断和动态拼接 SQL 的功能。
- Mybatis 提供了 9 种动态 SQL 标签：`<if />`、`<choose />`、`<when />`、`<otherwise />`、`<trim />`、`<where />`、`<set />`、`<foreach />`、`<bind />` 。
- 其执行原理为，使用 **OGNL** 的表达式，从 SQL 参数对象中计算表达式的值，根据表达式的值动态拼接 SQL ，以此来完成动态 SQL 的功能。

### 6、最佳实践中，通常一个 XML 映射文件，都会写一个 Mapper 接口与之对应。请问，这个 Mapper 接口的工作原理是什么？Mapper 接口里的方法，参数不同时，方法能重载吗？

Mapper 接口，对应的关系如下：

- 接口的全限名，就是映射文件中的 `"namespace"` 的值。
- 接口的方法名，就是映射文件中 MappedStatement 的 `"id"` 值。
- 接口方法内的参数，就是传递给 SQL 的参数。

Mapper 接口是没有实现类的，当调用接口方法时，接口全限名 + 方法名拼接字符串作为 key 值，可唯一定位一个对应的 MappedStatement 。举例：`com.mybatis3.mappers.StudentDao.findStudentById` ，可以唯一找到 `"namespace"` 为 `com.mybatis3.mappers.StudentDao` 下面 `"id"` 为 `findStudentById` 的 MappedStatement 。

> 总结来说，在 MyBatis 中，每一个 `<select />`、`<insert />`、`<update />`、`<delete />` 标签，都会被解析为一个 MappedStatement 对象。

另外，Mapper 接口的实现类，通过 MyBatis 使用 **JDK Proxy** 自动生成其代理对象 Proxy ，而代理对象 Proxy 会拦截接口方法，从而“调用”对应的 MappedStatement 方法，最终执行 SQL ，返回执行结果。整体流程如下图：

![](http://static2.iocoder.cn/images/MyBatis/2020_03_15/02.png)

其中，SqlSession 在调用 Executor 之前，会获得对应的 MappedStatement 方法。例如：`DefaultSqlSession#select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler)` 方法，代码如下：

```java
// DefaultSqlSession.java

@Override
public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
    try {
        // 获得 MappedStatement 对象
        MappedStatement ms = configuration.getMappedStatement(statement);
        // 执行查询
        executor.query(ms, wrapCollection(parameter), rowBounds, handler);
    } catch (Exception e) {
        throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
    } finally {
        ErrorContext.instance().reset();
    }
}
```

**最后，Mapper 接口里的方法，是不能重载的，因为是`全限名 + 方法名`的保存和寻找策略。**

###  7、Mapper 接口绑定有几种实现方式,分别是怎么实现的?

接口绑定有三种实现方式：

第一种，通过 **XML Mapper** 里面写 SQL 来绑定。在这种情况下，要指定 XML 映射文件里面的 `"namespace"` 必须为接口的全路径名。

第二种，通过**注解**绑定，就是在接口的方法上面加上 `@Select`、`@Update`、`@Insert`、`@Delete` 注解，里面包含 SQL 语句来绑定。

第三种，是第二种的特例，也是通过**注解**绑定，在接口的方法上面加上 `@SelectProvider`、`@UpdateProvider`、`@InsertProvider`、`@DeleteProvider` 注解，通过 Java 代码，生成对应的动态 SQL 。

**实际场景下，最最最推荐的是第一种方式。因为，SQL 通过注解写在 Java 代码中，会非常杂乱。而写在 XML 中，更加有整体性，并且可以更加方便的使用 OGNL 表达式。**

### 8、MyBatis 的 XML Mapper文件中，不同的 XML 映射文件，id 是否可以重复？

不同的 XML Mapper 文件，如果配置了 `"namespace"` ，那么 id 可以重复；如果没有配置 `"namespace"` ，那么 id 不能重复。毕竟`"namespace"` 不是必须的，只是最佳实践而已。

原因就是，`namespace + id` 是作为 `Map<String, MappedStatement>` 的 key 使用的。如果没有 `"namespace"`，就剩下 id ，那么 id 重复会导致数据互相覆盖。如果有了 `"namespace"`，自然 id 就可以重复，`"namespace"`不同，`namespace + id` 自然也就不同。

### 9、如何获取自动生成的(主)键值?

不同的数据库，获取自动生成的(主)键值的方式是不同的。

- **MySQL 有两种方式，但是必须是`自增主键`，代码如下：**

```xml
// 方式一，使用 useGeneratedKeys + keyProperty 属性
<insert id="insert" parameterType="Person" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO person(name, pswd)
    VALUE (#{name}, #{pswd})
</insert>
    
// 方式二，使用 `<selectKey />` 标签
<insert id="insert" parameterType="Person" useGeneratedKeys="true" keyProperty="id">
    <selectKey keyProperty="id" resultType="long" order="AFTER">
        SELECT LAST_INSERT_ID()
    </selectKey>
        
    INSERT INTO person(name, pswd)
    VALUE (#{name}, #{pswd})
</insert>
```

- 其中，**方式一**较为常用。

- **Oracle 有两种方式，`序列`和`触发器`。基于序列，根据 `<selectKey />` 执行的时机，也有两种方式，**代码如下：

```xml
// 这个是创建表的自增序列
CREATE SEQUENCE student_sequence
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
CACHE 10;

// 方式一，使用 `<selectKey />` 标签 + BEFORE
<insert id="add" parameterType="Student">
　　<selectKey keyProperty="student_id" resultType="int" order="BEFORE">
      select student_sequence.nextval FROM dual
    </selectKey>
    
     INSERT INTO student(student_id, student_name, student_age)
     VALUES (#{student_id},#{student_name},#{student_age})
</insert>

// 方式二，使用 `<selectKey />` 标签 + AFTER
<insert id="save" parameterType="com.threeti.to.ZoneTO" >
    <selectKey resultType="java.lang.Long" keyProperty="id" order="AFTER" >
      SELECT SEQ_ZONE.CURRVAL AS id FROM dual
    </selectKey>
    
    INSERT INTO TBL_ZONE (ID, NAME ) 
    VALUES (SEQ_ZONE.NEXTVAL, #{name,jdbcType=VARCHAR})
</insert>
```

###  10、Mybatis 都有哪些 Executor 执行器？它们之间的区别是什么？

Mybatis 有四种 Executor 执行器，分别是 SimpleExecutor、ReuseExecutor、BatchExecutor、CachingExecutor 。

- SimpleExecutor ：每执行一次 update 或 select 操作，就创建一个 Statement 对象，用完立刻关闭 Statement 对象。
- ReuseExecutor ：执行 update 或 select 操作，以 SQL 作为key 查找**缓存**的 Statement 对象，存在就使用，不存在就创建；用完后，不关闭 Statement 对象，而是放置于缓存 `Map<String, Statement>` 内，供下一次使用。简言之，就是重复使用 Statement 对象。
- BatchExecutor ：执行 update 操作（没有 select 操作，因为 JDBC 批处理不支持 select 操作），将所有 SQL 都添加到批处理中（通过 addBatch 方法），等待统一执行（使用 executeBatch 方法）。它缓存了多个 Statement 对象，每个 Statement 对象都是调用 addBatch 方法完毕后，等待一次执行 executeBatch 批处理。**实际上，整个过程与 JDBC 批处理是相同**。
- CachingExecutor ：在上述的三个执行器之上，增加**二级缓存**的功能。

通过设置 `<setting name="defaultExecutorType" value="">` 的 `"value"` 属性，可传入 SIMPLE、REUSE、BATCH 三个值，分别使用 SimpleExecutor、ReuseExecutor、BatchExecutor 执行器。

通过设置 `<setting name="cacheEnabled" value=""` 的 `"value"` 属性为 `true` 时，创建 CachingExecutor 执行器。

### 11、MyBatis 如何执行批量插入?

#### 批处理方式

首先，在 Mapper XML 编写一个简单的 Insert 语句。代码如下：

```xml
<insert id="insertUser" parameterType="String"> 
    INSERT INTO users(name) 
    VALUES (#{value}) 
</insert>
```

然后，然后在对应的 Mapper 接口中，编写映射的方法。代码如下：

```java
public interface UserMapper {
    
    void insertUser(@Param("name") String name);

}
```

最后，调用该 Mapper 接口方法。代码如下：

```java
private static SqlSessionFactory sqlSessionFactory;

@Test
public void testBatch() {
    // 创建要插入的用户的名字的数组
    List<String> names = new ArrayList<>();
    names.add("aa");
    names.add("bb");
    names.add("cc");
    names.add("dd");

    // 获得执行器类型为 Batch 的 SqlSession 对象，并且 autoCommit = false ，禁止事务自动提交
    // 如果单词数据量很大，可以在循环中分批提交
    try{
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)
        // 获得 Mapper 对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        // 循环插入
        for (String name : names) {
            mapper.insertUser(name);
        }
        // 提交批量操作
        session.commit();
    }
}
```

#### SQL 拼接方式

```mysql
INSERT INTO [表名]([列名],[列名]) 
VALUES
([列值],[列值])),
([列值],[列值])),
([列值],[列值]));
```

- 对于这种方式，需要保证单条 SQL 不超过语句的最大限制 `max_allowed_packet` 大小，默认为 1 M 。

###  12、简述 MyBatis 的插件运行原理？以及如何编写一个插件？

Mybatis 仅可以编写针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 这 4 种接口的插件。

Mybatis 使用 JDK 的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这 4 种接口对象的方法时，就会进入拦截方法，具体就是 InvocationHandler 的 `#invoke(...)`方法。当然，只会拦截那些你指定需要拦截的方法。

> 编写一个 MyBatis 插件的步骤如下：
>
> 1. 首先，实现 Mybatis 的 Interceptor 接口，并实现 `#intercept(...)` 方法。
> 2. 然后，在给插件编写注解，指定要拦截哪一个接口的哪些方法即可
> 3. 最后，在配置文件中配置你编写的插件。

### 13、Mybatis 的 XML 映射文件和 Mybatis 内部数据结构之间的映射关系

Mybatis 将所有 XML 配置信息都封装到 All-In-One 重量级对象Configuration内部。

在 XML Mapper 文件中：

- `<parameterMap>` 标签，会被解析为 ParameterMap 对象，其每个子元素会被解析为 ParameterMapping 对象。
- `<resultMap>` 标签，会被解析为 ResultMap 对象，其每个子元素会被解析为 ResultMapping 对象。
- 每一个 `<select>`、`<insert>`、`<update>`、`<delete>` 标签，均会被解析为一个 MappedStatement 对象，标签内的 SQL 会被解析为一个 BoundSql 对象。

