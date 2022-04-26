package hello;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


//@Entity(name = "JPA에서 인식할 이름(쿼리 등에서)")
//기본 생성자 필수
//final 클래스, enum, interface, inner클래스 사용 x
// 저장할 필드에 final 사용 x
//@Table(name = "USER") 테이블 명 별도 지정
//@Table(catalog ,schema = ,uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNUQUE", columnNames = {"NAME", "AGE"})})
//@Entity
public class Member {

    //IDENTITY: DB에 위임
//persist(insert) 시에 즉시 DB에 쿼리 실행 (key전략을 JPA에서 파악 불가)
//따라서 여러개를 한번에 INSERT가 불가ㅇㅇ


    //SEQUENCE: 시퀀스 객체를 만들어서 규칙 관리
    //엔티티 클래스 @SequenceGenerator 어노테이션을 사용해
    //name = "제너레이터 명", sequenceName = "MEMBER_SEQ"//DB내부 시퀀스 명
    //initialValue = 1, allocationSize = 50(증가량)
    //등의 매개변수로 지정 후
    //generator = "제너레이터 명"을 매개변수로 전달해 설정

    //TABLE: 테이블에서 지정된 전략사용
    //@TableGenerator(name="제너레이터 명", table="시퀀스 저장용 테이블명"
    // , pkColumnValue="sequence_name 컬렴명", allocationSize= 증감량)
    //대충 시퀀스 저장용 테이블에서 pkColumnValue에 입력한 값을 SEQUENCE_NAME컬럼에
    //저장하고 다음 key 값을 next_val에 저장하는 방식
    //valueColumnNa="next_val컬럼명"
    //uniqueConstraint = 시퀀스 테이블 제약조건

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Member(){}
}
