package siarhei.luskanau.sql.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends CrudRepository<String, Integer> {

    @Query("""
            SELECT * FROM model m
            """)
    List<String> reportModelForAge(@Param("age") int age);
}
