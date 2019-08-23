package com.crx.streamdemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		List<String> list=Arrays.asList("1g","e","ggg");
		List<String> list1=list.stream().map((s)->s+"haha").collect(Collectors.toList());
		list1.stream().forEach(System.out::println);
		//使用并发流
		System.out.println(list.parallelStream().count());
		//使用flatMap处理流的嵌套
		//集合套集合时，处理最底层集合的内容
		System.out.println(list.stream().flatMap(s->Arrays.stream(s.split(""))).distinct().collect(Collectors.toList()));
	}

}
