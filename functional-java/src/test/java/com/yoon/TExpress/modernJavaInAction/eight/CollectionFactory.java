package com.yoon.TExpress.modernJavaInAction.eight;

import com.yoon.TExpress.modernJavaInAction.four.Transaction;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.yoon.TExpress.modernJavaInAction.five.PracticeTest.transactions;

public class CollectionFactory {


    @Test
    void step1_list_factory_로_생성하면_변경_불가능하다() {
        List<String> friends = List.of("냥냥", "멍멍", "음메");

        System.out.println(friends);
        // friends.add("꿀꿀"); java.lang.UnsupportedOperationException
    }

    @Test
    void step2_set_factory_로_생성하면_변경_불가능하다() {
        Set<String> friends = Set.of("냥냥", "멍멍", "음메");

        System.out.println(friends);
        // friends.add("꿀꿀"); java.lang.UnsupportedOperationException
    }

    @Test
    void step3_map_factory_로_생성하면_변경_불가능하다() {
        Map<String, Integer> friends = Map.of("냥냥", 1, "멍멍", 4, "음메", 7);

        System.out.println(friends);
       // friends.put("꿀꿀", 8); java.lang.UnsupportedOperationException
    }


    @Test
    void step4_mapOfEntries_로_생성하면_변경_불가능하다_with_entry() { // 열개 이상의 쌍 (map) 을 만들 때에는 가변 인수로 구현된 ofEntries 이용하는 것이 좋음 with entry()
        Map<String, Integer> friends = Map.ofEntries(Map.entry("냥냥", 1), Map.entry("멍멍", 4), Map.entry("음메", 7));

        System.out.println(friends);
        // friends.put("꿀꿀", 8); java.lang.UnsupportedOperationException
    }

    @Test
    void step5_removeIf_with_UnsupportedOperationException() {
        for (Transaction transaction : transactions){
            if(Character.isDigit(transaction.getReferenceCode().charAt(0))){
                transactions.remove(transaction);
            }
        }

        // 왜 에러가 나냐. 위 코드는 아래와 같이 해석된다
        // 반복하면서 별도의 두 객체를 통해 컬렉션을 바꾸고 있음.
        for(Iterator<Transaction> iterator = transactions.iterator(); // Iterator : 소스 질의
                                    iterator.hasNext(); ){
            Transaction transaction = iterator.next();
            if(Character.isDigit(transaction.getReferenceCode().charAt(0))){
                transactions.remove(transaction); // Collection : 요소 삭제
            }
        }

        // 문제는 반복자의 상태는 컬렉션의 상태와 서로 동기화되지 않는다는 것.

        System.out.println(transactions);
    }

    @Test
    void step6_removeIf_solve_step5() {
        for(Iterator<Transaction> iterator = transactions.iterator(); // Iterator 객체를 명시적으로 사용 !
            iterator.hasNext(); ){
            Transaction transaction = iterator.next();
            if(Character.isDigit(transaction.getReferenceCode().charAt(0))){
                iterator.remove(); //
            }
        }

        System.out.println(transactions);
    }

    @Test
    void step7_removeIf_최종개선() {
        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));

        System.out.println(transactions);
    }

    @Test
    void step8_replaceAll() {
        List<String> referenceCodes = List.of("a13", "E15", "b50");

        referenceCodes.stream().map(code -> Character.toUpperCase(code.charAt(0)) +
                code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println); // 해당 코드는 새 문자열을 만든다.
    }

    @Test
    void step9_replaceAll() {
        List<String> referenceCodes = List.of("a13", "E15", "b50");

        for(ListIterator<String> referenceCodesIterator = referenceCodes.listIterator();
            referenceCodesIterator.hasNext(); ) {
            String code = referenceCodesIterator.next();
            referenceCodesIterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }
    }

    private static Map<String, Integer> ageOfFriends = new HashMap<>();
    static {
        ageOfFriends.put("kevin", 20);
        ageOfFriends.put("oliver", 27);
    }

    @Test
    void step10_map_forEach() {
        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old.");
        }
    }

    @Test
    void step11_map_forEach_with_BiConsumer() {
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old."));
    }
}
