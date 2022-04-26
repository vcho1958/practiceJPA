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
@Entity
public class Member {

    @Id
    private Long id;

    //unique는 Tagble에서 uniqueConstraints로 하고 컬럼에서하면 랜덤 문자열이기 때문에 사용하지 않는다.
    @Column(name="name", insertable = false,
            updatable = false, nullable = false,
            unique = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

//    @Column(precision = "소수점포함 전체 자릿수", scale = "소수점 자릿수")
    private BigDecimal age;

    //ORDINAL은 enum변경시 기존 로우들이 변경되지 않아 이슈가 생길 수 있다.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)//DATE 날짜 TIME 시간 TIMESTAMP 모두 포한
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob//큰 varchar를 외부파일로 저장
    private String description;

    @Transient//임시필드 DB저장x
    private int temp;

    public Member(){}
}
