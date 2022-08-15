package com.yoon.TExpress.completableFuture;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    @Test
    void thenApply_결과_값을_받아서_다른_타입으로_변경() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toLowerCase();
        });

        future.get();

    }

    @Test
    void then_Accept_결과_값을_받아서_또_다른_실행을_하는_그러나_return_값이_없는() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        future.get();
    }

    @Test
    void then_Run_결과_값을_참조하지_않고_단순_실행() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        future.get();
    }

    @Test
    void then_compose_결과_값으로_조합하여_실행() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> future = hello.thenCompose(this::getFutureWorld);

        System.out.println(future.get());

    }

    @Test
    void then_combine_결과_값으로_결합하여_실행() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> yoonJi = getFutureWorld("YoonJi");

        CompletableFuture<String> future = hello.thenCombine(yoonJi, (h, w) -> h + " " + w);
        /**  hello 의 결과 world 의 결과를 각각 받아서 새로운 결과 를 출력*/

        System.out.println(future.get());
    }

    @Test
    void example() throws ExecutionException, InterruptedException {
        CompletableFuture<Sample> person1 = CompletableFuture
                .supplyAsync(() -> new Sample("person1", "대구", 35));


        CompletableFuture<Sample> person2 = CompletableFuture
                .supplyAsync(() -> new Sample("person2", "서울", 25));

        CompletableFuture<String> future = person1.thenCombine(person2, (p1, p2) -> "합친 나이" + (p1.age + p2.age));

        System.out.println(future.get());
    }

    @Test
    void 두_개_이상일때_여러_테스크를_합쳐가지고_실행_allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = getFutureWorld("!!");

        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] array = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture<List<String>> listCompletableFuture = CompletableFuture.allOf(array)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        listCompletableFuture.get().forEach(System.out::println);

        /** 둘 중 아무거나 하나 먼저 오는 결과를 받아와서 처리 */
        CompletableFuture<Void> thenAccept = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        thenAccept.get();
    }

    @Test
    void 에러가_발생했을_때의_처리_exceptionnally() throws ExecutionException, InterruptedException {
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if(throwError){
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(hello.get());
    }

    @Test
    void 에러가_발생했을_때의_처리2_handle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error!";
            }
            return result;
        });

        /** 에러가 발생했을 경우와, 아닌 경우를 한꺼번에 핸들링하는 handle .  */
        System.out.println(hello.get());
    }

    private CompletableFuture<String> getFutureWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + "World";
        });
    }

    @AllArgsConstructor
    class Sample {
        String name;
        String address;
        Integer age;
    }


}
