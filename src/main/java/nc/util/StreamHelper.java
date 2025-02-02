package nc.util;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamHelper {
	
	public static <T, U> U[] map(T[] array, Function<? super T, ? extends U> function, IntFunction<U[]> generator) {
		return Arrays.stream(array).map(function).toArray(generator);
	}
	
	public static <T, U> List<U> map(T[] array, Function<? super T, ? extends U> function) {
		return Arrays.stream(array).map(function).collect(Collectors.toList());
	}
	
	public static <T, U> List<U> map(List<T> list, Function<? super T, ? extends U> function) {
		return list.stream().map(function).collect(Collectors.toList());
	}
	
	public static <T, U> Set<U> map(Set<T> set, Function<? super T, ? extends U> function) {
		return set.stream().map(function).collect(Collectors.toSet());
	}
	
	public static <T, U> U[] flatMap(T[] array, Function<? super T, ? extends Collection<? extends U>> function, IntFunction<U[]> generator) {
		return Arrays.stream(array).flatMap(x -> function.apply(x).stream()).toArray(generator);
	}
	
	public static <T, U> List<U> flatMap(T[] array, Function<? super T, ? extends Collection<? extends U>> function) {
		return Arrays.stream(array).flatMap(x -> function.apply(x).stream()).collect(Collectors.toList());
	}
	
	public static <T, U> List<U> flatMap(List<T> list, Function<? super T, ? extends Collection<? extends U>> function) {
		return list.stream().flatMap(x -> function.apply(x).stream()).collect(Collectors.toList());
	}
	
	public static <T, U> Set<U> flatMap(Set<T> set, Function<? super T, ? extends Collection<? extends U>> function) {
		return set.stream().flatMap(x -> function.apply(x).stream()).collect(Collectors.toSet());
	}
	
	public static <T> T[] flatten(T[][] array, IntFunction<T[]> generator) {
		return flatMap(array, Arrays::asList, generator);
	}
}
