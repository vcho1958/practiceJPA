package hello;

import javax.persistence.*;

@Entity
//@Entity(name = "JPA에서 인식할 이름(쿼리 등에서)")
//기본 생성자 필수
//final 클래스, enum, interface, inner클래스 사용 x
// 저장할 필드에 final 사용 x
//@Table(name = "USER") 테이블 명 별도 지정
//@Table(catalog ,schema = ,uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNUQUE", columnNames = {"NAME", "AGE"})})
public class Member {
    public Long getId() {
        return id;
    }

    //Column(name = "username") 컬럼명 별도 지정
    @Column(unique = true, length = 10)
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    private Long id;
    private String name;
}
