package org.example.motivation;


public record Cons<T>(T head, List<T> tail) implements List<T> {
    
}
