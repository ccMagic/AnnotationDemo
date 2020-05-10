以此编码格式加载资源
```
-encoding UTF-8
```

-cp <路径> 指定查找用户类文件和注释处理程序的位置。
```
-cp build/classes/java/main/
```


-processor <class1>[,<class2>,<class3>…]要运行的注释处理程序的名称；绕过默认的搜索进程
```
-processor com.github.ccmagic.generateinterface.CreateInterfaceProcessor
```

-d <目录> 指定存放生成的类文件的位置。
```
-d build/classes/java/main
```
-s <目录> 指定存放生成的源文件的位置。
```
-s src/main/java/
```
此次测试最终命令格式
```
javac -encoding UTF-8 -cp build/classes/java/main/ -processor com.github.ccmagic.generateinterface.CreateInterfaceProcessor -d build/classes/java/main -s src/main/java/ src/main/java/com/github/ccmagic/generateinterface/*.java
```