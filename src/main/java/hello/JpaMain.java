package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    Member create(Long id, String name){
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        return member;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

//            Member member = em.find(Member.class, 1L); //find 메소드
//            System.out.println("id =" + member.getId());
//            System.out.println("name = "+ member.getName());
//            member.setName("updatedName");//이름 변경
//            em.persist(member); //해당 인스턴스 DB추적 시작
//             em.remove(member); // 해당 인스턴스 삭제
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)//skip
                    .setMaxResults(8)//limit
                    .getResultList();
            for(Member member: result)System.out.println("member.name = " + member.getName());
            tx.commit();
        }catch (Exception e){
            tx.rollback();//롤백
        }finally {
            em.close();

        }

        emf.close();
    }
}
