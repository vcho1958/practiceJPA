package hello;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "USER") 테이블 명 별도 지정
public class Member {
    public Long getId() {
        return id;
    }

    //Column(name = "username") 컬럼명 별도 지정
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
