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
        //em.setFlushMode(FlushModeType.COMMIT); //commit시에만 flush
        //FlushType.AUTO//쿼리 실행시에도 flush 기본값
        tx.begin();
        try {
// 모든 DB 작업은 쓰기 지연 SQL 저장소에 쌓인 후 tx.commit()과정에서 시작함
//1차 캐시에 존재할 경우 캐시에서 조회
            //해당 시점에서 1차캐시의 스냅샷을 비교하여 다르다면 적절하게 update쿼리 날림
//            Member member = em.find(Member.class, 1L); //find 메소드
//            System.out.println("id =" + member.getId());
//            System.out.println("name = "+ member.getName());
//            member.setName("updatedName");//이름 변경
//            em.persist(member); //해당 인스턴스 DB추적 시작 em의 1차 캐시에 저장
//             em.remove(member); // 해당 인스턴스 삭제
            Member member = em.find(Member.class, "member1");
            //1차 캐시에서 조회 후 없으면 DB를 조회하여 있다면 1차캐시에 저장 후 반환
            //따라서 같은 객체를 불러오는 쿼리가 요구될 경우 캐시에서 조회하여 DB쿼리 절감
            //call by reference
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)//skip
                    .setMaxResults(8)//limit
                    .getResultList();
//            em.detach(member);//DB추적 분리(준영속으로 변경) em.flush에서 1차캐시에서 비교 안함
//            em.clear();//1차캐시 전체 detach
//            em.remove(member);//제거
            //em.flush() 지연 SQL실행
            tx.commit();
        }catch (Exception e){
            tx.rollback();//롤백
        }finally {
            em.close();

        }

        emf.close();
    }
}
