# Redis Summary

------

- Redis是速度非常快的非关系型内存键值数据库，可以存储键跟五中不同类型的值之间的映射。
- 键的类型只能为字符串，值支持五种数据类型：字符串、列表、哈希、集合、有序集合。
- Redis 支持很多特性，例如将内存中的数据持久化到硬盘中，使用复制来扩展读性能，使用分片来扩展写性能。

## 一、Redis基本数据类型

数据类型|可以存储的值|操作|
:----:|:----:|:----|
String | 字符串、整数、浮点数 |  ①、对整个字符串或者字符串的一部分执行操作 <br/> ②、对整数和浮点数执行自增或者自减操作
List | 列表 | ①、从两端压入或者弹出元素 <br/> ②、对单个或者多个元素进行修剪，只保留某个范围的元素
Set | 无序集合 | ①、添加、获取、移除单个元素 <br/> ②、检查一个元素是否存在于集合中 <br/> ③、计算交集、并集、差集 <br/>  ④、从集合里随机获取元素
Hash | 包含键值对的无序散列表 | ①、添加、获取、移除单个键值对、获取所有键值对 <br/> ②、检查某个键是否存在
ZSet | 有序集合 | ①、添加、获取、删除元素 <br/> ②、根据分值范围或者成员来获取元素 <br/> ③、计算一个键的排名

### 1、String
```
常用命令： set, get, decr, incr, mget, setnx
```
- String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。<br/> 
- **常规key-value缓存应用； 常规计数：微博数，粉丝数等。**

### 2、List
```
常用命令: lpush, rpush, lpop, rpop, lrange, blpop
```

- List 就是链表，Redis list的应用场景非常多，也是Redis最重要的数据结构之一，比如**微博的关注列表，粉丝列表，消息列表**等功能都可以用Redis的 list 结构来实现。

- **Redis list 的实现为一个双向链表，可以通过rpush生产消息，lpop消费消息，实现一个消息队列。**

- 另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以**基于list实现分页查询**，这个很棒的一个功能，基于 redis 实现简单的高性能分页，可以做类似微博那种下拉不断分页的东西（一页一页的往下走），性能高。

### 3、Set
```
常用命令： sadd,spop,smembers,sunion 等
```
- set 对外提供的功能与list类似是一个列表的功能，特殊之处在于 **set 是可以自动排重**的。

- 当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了**判断某个成员是否在一个set集合内**的重要接口，这个也是list所不能提供的。可以**基于 set 轻易实现交集、并集、差集**的操作。

- 比如：在微博应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis可以非常方便的**实现如共同关注、共同粉丝、共同喜好**等功能。这个过程也就是求交集的过程，具体命令如下：
```
sinterstore key1 key2 key3     将交集存在key1内
```

### 4、Hash 

```
常用命令： hget,hset,hgetall 等。

```
- Hash 是一个 string 类型的 field 和 value 的映射表，**hash 特别适合用于存储对象**，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的值。 比如我们可以Hash数据结构来存储用户信息，商品信息等等。比如下面我就用 hash 类型存放了我本人的一些信息：
```
key=user9527
value={
  “id”: 1,
  “name”: “Test”,
  “age”: 20,
  “location”: “上海”
}
```

### 5、Sorted Set
```
常用命令： zadd,zrange,zrem,zcard,zrangebyscore等

```
- 和set相比，sorted set增加了一个权重参数score，使得集合中的元素能够按score进行有序排列。

- 举例： 在直播系统中，**实时排行信息包含直播间在线用户列表，各种礼物排行榜，弹幕消息（可以理解为按消息维度的消息排行榜）等信息**，适合使用 Redis 中的 SortedSet 结构进行存储。

## 二、Redis的使用场景
### 计数器
可以对 String 进行自增自减运算，从而实现计数器功能。

Redis 这种内存型数据库的读写性能非常高，很适合存储频繁读写的计数量。

### 缓存
将热点数据放到内存中，设置内存的最大使用量以及淘汰策略来保证缓存的命中率。

### 消息队列
List 是一个双向链表，可以通过 lpop 和 rpush 写入和读取消息。(blpop可阻塞等待消息)

不过最好使用 Kafka、RabbitMQ 等消息中间件。
### 会话缓存
可以使用 Redis 来统一存储多台应用服务器的会话信息。

当应用服务器不再存储用户的会话信息，也就不再具有状态，一个用户可以请求任意一个应用服务器，从而更容易实现高可用性以及可伸缩性。

### 分布式锁
#### 1、分布式锁具备条件:
> * 分布式环境下，同一段代码块同一时间只能被一个机器的一个线程执行
> * 高可用、高性能锁的获取与释放
> * 具备可重入特性
> * 具备锁的失效机制，防止死锁
> * 具备非阻塞锁的特征，即没有获取到锁直接返回锁失败

#### 2、Redisson实现Redis分布式锁原理：
##### 1、加锁机制
    现在某个客户端要加锁。如果该客户端面对的是一个redis cluster集群，他首先会根据hash节点选择一台机器。这里注意，仅仅只是选择一台机器！这点很关键！紧接着，就会发送一段lua脚本到redis上，lua脚本如下所示：
```lua
/**
 * KEYS[1] :代表要加锁的key
 * ARGV[1] :代表锁key的默认生存时间，默认30s
 * ARGV[2] :客户端ID + 加锁线程随机UUID（任意不重复字符串）
 */
if(redis.call('exist',KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    resid.call('pexpire' , KEYS[1], ARGV[1]);
    return nil;
end;
if(redis.call('hexists', KEYS[1], AGRV[2]) == 1) then
    redis.call('hincrby', KEYS[1] , ARGV[2] ,1);
    redis.call('pexpire' , KEYS[1] , ARGV[1]);
    return nil;
end;
return redis.call('pttl', KE);
```
> * 为啥要用lua脚本呢？
**因为一个复杂的业务逻辑，可以通过封装在lua脚本中发送给redis，保证这段复杂业务逻辑`执行的原子性`。**
> * lua脚本解释：
①、第一个if，判断要加锁的key是否已存在，不存在的话，使用hset命令先创建一个名为key的hash对象，并把`AGRV[2]`作为hash的key，value为1，之后设置过期时间返回`（直接加锁成功的逻辑）`。
②、第二个if，运行到第二个if，说明要加锁的key对应的hash对象在缓存中已经存在了。所以直接判断在hash对象中key为`AGRV[2]`的键值对是否存在，如果存在，表示key对应的锁在当前客户端线程中已添加，只需将value自增1即可，之后重新设置过期时间后返回`（锁重入成功）`
③、如果上面两个if逻辑都未进入，则直接返回该锁的剩余生存时间 `(锁互斥加锁失败)`
> * example：执行伪代码 ```redis.getLock("test_distribute_lock")``` , redis中对应数据为:
```JSON
test_distribute_lock:{

    "1:8743c9c0-0795-4907-87fd-6c719a6b4586":1
    
}
```


##### 2、锁互斥机制
    见lua脚本解释③

##### 3、watch dog 自动续期机制
客户端1加锁的锁key默认生存时间才30秒，如果超过了30秒，客户端1还想一直持有这把锁，怎么办呢？只要客户端1一旦加锁成功，就会启动一个watch dog看门狗，**他是一个后台线程，会每隔10秒检查一下，如果客户端1还持有锁key，那么就会不断的延长锁key的生存时间**。

##### 4、可重入加锁机制
那如果客户端1都已经持有了这把锁了，结果可重入的加锁会怎么样呢？比如下面这种代码：
```java
RedisLock lock = redis.getLock("test_distribute_lock");
lock.lock();
/**一大段业务逻辑**/

lock.lock();
/**一大段业务逻辑**/

lock.unlock();
lock.unlock();
```
> * 按照上面对加锁lua脚本解释，第一个lock执行第一个if逻辑 。第二个lock执行第二个if逻辑
> * 重入加锁后，example中的hash数据变为下面这样，由此可见，`hash中的value为锁的加锁次数`：
```JSON
test_distribute_lock:{

    "1:8743c9c0-0795-4907-87fd-6c719a6b4586":2
    
}
```
##### 5、锁释放机制
如果执行lock.unlock()，就可以释放分布式锁，此时的业务逻辑也是非常简单的。就是每次都对test_distribute_lock数据结构中的那个加锁次数减1。如果发现加锁次数是0了，说明这个客户端已经不再持有锁了，此时就会用：“del test_distribute_lock”命令，从redis里删除这个key。另外的客户端2就可以尝试完成加锁了。这就是所谓的分布式锁的开源Redisson框架的实现机制。

##### 6、上述Redis分布式锁的缺点

其实上面那种方案最大的问题，就是如果你对某个redis master实例，写入了myLock这种锁key的value，此时会异步复制给对应的master slave实例。但是这个过程中一旦发生redis master宕机，主备切换，redis slave变为了redis master。

接着就会导致，客户端2来尝试加锁的时候，在新的redis master上完成了加锁，而客户端1也以为自己成功加了锁。此时就会导致多个客户端对一个分布式锁完成了加锁。这时系统在业务上一定会出现问题，导致各种脏数据的产生。

所以这个就是redis cluster，或者是redis master-slave架构的主从异步复制导致的redis分布式锁的最大缺陷：**在redis master实例宕机的时候，可能导致多个客户端同时完成加锁。**





![cmd-markdown-logo](https://www.zybuluo.com/static/img/logo.png)

除了您现在看到的这个 Cmd Markdown 在线版本，您还可以前往以下网址下载：

### [Windows/Mac/Linux 全平台客户端](https://www.zybuluo.com/cmd/)

> 请保留此份 Cmd Markdown 的欢迎稿兼使用说明，如需撰写新稿件，点击顶部工具栏右侧的 <i class="icon-file"></i> **新文稿** 或者使用快捷键 `Ctrl+Alt+N`。

------

## 什么是 Markdown

Markdown 是一种方便记忆、书写的纯文本标记语言，用户可以使用这些标记符号以最小的输入代价生成极富表现力的文档：譬如您正在阅读的这份文档。它使用简单的符号标记不同的标题，分割不同的段落，**粗体** 或者 *斜体* 某些文字，更棒的是，它还可以

### 1. 制作一份待办事宜 [Todo 列表](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#13-待办事宜-todo-列表)

- [ ] 支持以 PDF 格式导出文稿
- [ ] 改进 Cmd 渲染算法，使用局部渲染技术提高渲染效率
- [x] 新增 Todo 列表功能
- [x] 修复 LaTex 公式渲染问题
- [x] 新增 LaTex 公式编号功能

### 2. 书写一个质能守恒公式[^LaTeX]

$$E=mc^2$$

### 3. 高亮一段代码[^code]

```python
@requires_authorization
class SomeClass:
    pass

if __name__ == '__main__':
    # A comment
    print 'hello world'
```

### 4. 高效绘制 [流程图](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#7-流程图)

```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end

st->op->cond
cond(yes)->e
cond(no)->op
```

### 5. 高效绘制 [序列图](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#8-序列图)

```seq
Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Bob-->Alice: I am good thanks!
```

### 6. 高效绘制 [甘特图](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#9-甘特图)

```gantt
    title 项目开发流程
    section 项目确定
        需求分析       :a1, 2016-06-22, 3d
        可行性报告     :after a1, 5d
        概念验证       : 5d
    section 项目实施
        概要设计      :2016-07-05  , 5d
        详细设计      :2016-07-08, 10d
        编码          :2016-07-15, 10d
        测试          :2016-07-22, 5d
    section 发布验收
        发布: 2d
        验收: 3d
```

### 7. 绘制表格

| 项目        | 价格   |  数量  |
| --------   | -----:  | :----:  |
| 计算机     | \$1600 |   5     |
| 手机        |   \$12   |   12   |
| 管线        |    \$1    |  234  |

### 8. 更详细语法说明

想要查看更详细的语法说明，可以参考我们准备的 [Cmd Markdown 简明语法手册][1]，进阶用户可以参考 [Cmd Markdown 高阶语法手册][2] 了解更多高级功能。

总而言之，不同于其它 *所见即所得* 的编辑器：你只需使用键盘专注于书写文本内容，就可以生成印刷级的排版格式，省却在键盘和工具栏之间来回切换，调整内容和格式的麻烦。**Markdown 在流畅的书写和印刷级的阅读体验之间找到了平衡。** 目前它已经成为世界上最大的技术分享网站 GitHub 和 技术问答网站 StackOverFlow 的御用书写格式。

---

## 什么是 Cmd Markdown

您可以使用很多工具书写 Markdown，但是 Cmd Markdown 是这个星球上我们已知的、最好的 Markdown 工具——没有之一 ：）因为深信文字的力量，所以我们和你一样，对流畅书写，分享思想和知识，以及阅读体验有极致的追求，我们把对于这些诉求的回应整合在 Cmd Markdown，并且一次，两次，三次，乃至无数次地提升这个工具的体验，最终将它演化成一个 **编辑/发布/阅读** Markdown 的在线平台——您可以在任何地方，任何系统/设备上管理这里的文字。

### 1. 实时同步预览

我们将 Cmd Markdown 的主界面一分为二，左边为**编辑区**，右边为**预览区**，在编辑区的操作会实时地渲染到预览区方便查看最终的版面效果，并且如果你在其中一个区拖动滚动条，我们有一个巧妙的算法把另一个区的滚动条同步到等价的位置，超酷！

### 2. 编辑工具栏

也许您还是一个 Markdown 语法的新手，在您完全熟悉它之前，我们在 **编辑区** 的顶部放置了一个如下图所示的工具栏，您可以使用鼠标在工具栏上调整格式，不过我们仍旧鼓励你使用键盘标记格式，提高书写的流畅度。

![tool-editor](https://www.zybuluo.com/static/img/toolbar-editor.png)

### 3. 编辑模式

完全心无旁骛的方式编辑文字：点击 **编辑工具栏** 最右侧的拉伸按钮或者按下 `Ctrl + M`，将 Cmd Markdown 切换到独立的编辑模式，这是一个极度简洁的写作环境，所有可能会引起分心的元素都已经被挪除，超清爽！

### 4. 实时的云端文稿

为了保障数据安全，Cmd Markdown 会将您每一次击键的内容保存至云端，同时在 **编辑工具栏** 的最右侧提示 `已保存` 的字样。无需担心浏览器崩溃，机器掉电或者地震，海啸——在编辑的过程中随时关闭浏览器或者机器，下一次回到 Cmd Markdown 的时候继续写作。

### 5. 离线模式

在网络环境不稳定的情况下记录文字一样很安全！在您写作的时候，如果电脑突然失去网络连接，Cmd Markdown 会智能切换至离线模式，将您后续键入的文字保存在本地，直到网络恢复再将他们传送至云端，即使在网络恢复前关闭浏览器或者电脑，一样没有问题，等到下次开启 Cmd Markdown 的时候，她会提醒您将离线保存的文字传送至云端。简而言之，我们尽最大的努力保障您文字的安全。

### 6. 管理工具栏

为了便于管理您的文稿，在 **预览区** 的顶部放置了如下所示的 **管理工具栏**：

![tool-manager](https://www.zybuluo.com/static/img/toolbar-manager.jpg)

通过管理工具栏可以：

<i class="icon-share"></i> 发布：将当前的文稿生成固定链接，在网络上发布，分享
<i class="icon-file"></i> 新建：开始撰写一篇新的文稿
<i class="icon-trash"></i> 删除：删除当前的文稿
<i class="icon-cloud"></i> 导出：将当前的文稿转化为 Markdown 文本或者 Html 格式，并导出到本地
<i class="icon-reorder"></i> 列表：所有新增和过往的文稿都可以在这里查看、操作
<i class="icon-pencil"></i> 模式：切换 普通/Vim/Emacs 编辑模式

### 7. 阅读工具栏

![tool-manager](https://www.zybuluo.com/static/img/toolbar-reader.jpg)

通过 **预览区** 右上角的 **阅读工具栏**，可以查看当前文稿的目录并增强阅读体验。

工具栏上的五个图标依次为：

<i class="icon-list"></i> 目录：快速导航当前文稿的目录结构以跳转到感兴趣的段落
<i class="icon-chevron-sign-left"></i> 视图：互换左边编辑区和右边预览区的位置
<i class="icon-adjust"></i> 主题：内置了黑白两种模式的主题，试试 **黑色主题**，超炫！
<i class="icon-desktop"></i> 阅读：心无旁骛的阅读模式提供超一流的阅读体验
<i class="icon-fullscreen"></i> 全屏：简洁，简洁，再简洁，一个完全沉浸式的写作和阅读环境

### 8. 阅读模式

在 **阅读工具栏** 点击 <i class="icon-desktop"></i> 或者按下 `Ctrl+Alt+M` 随即进入独立的阅读模式界面，我们在版面渲染上的每一个细节：字体，字号，行间距，前背景色都倾注了大量的时间，努力提升阅读的体验和品质。

### 9. 标签、分类和搜索

在编辑区任意行首位置输入以下格式的文字可以标签当前文档：

标签： 未分类

标签以后的文稿在【文件列表】（Ctrl+Alt+F）里会按照标签分类，用户可以同时使用键盘或者鼠标浏览查看，或者在【文件列表】的搜索文本框内搜索标题关键字过滤文稿，如下图所示：

![file-list](https://www.zybuluo.com/static/img/file-list.png)

### 10. 文稿发布和分享

在您使用 Cmd Markdown 记录，创作，整理，阅读文稿的同时，我们不仅希望它是一个有力的工具，更希望您的思想和知识通过这个平台，连同优质的阅读体验，将他们分享给有相同志趣的人，进而鼓励更多的人来到这里记录分享他们的思想和知识，尝试点击 <i class="icon-share"></i> (Ctrl+Alt+P) 发布这份文档给好友吧！

------

再一次感谢您花费时间阅读这份欢迎稿，点击 <i class="icon-file"></i> (Ctrl+Alt+N) 开始撰写新的文稿吧！祝您在这里记录、阅读、分享愉快！

作者 [@ghosert][3]     
2016 年 07月 07日    

[^LaTeX]: 支持 **LaTeX** 编辑显示支持，例如：$\sum_{i=1}^n a_i=0$， 访问 [MathJax][4] 参考更多使用方法。

[^code]: 代码高亮功能支持包括 Java, Python, JavaScript 在内的，**四十一**种主流编程语言。

[1]: https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown
[2]: https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#cmd-markdown-高阶语法手册
[3]: http://weibo.com/ghosert
[4]: http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference

